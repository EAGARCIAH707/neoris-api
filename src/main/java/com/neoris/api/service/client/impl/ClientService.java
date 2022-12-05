package com.neoris.api.service.client.impl;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.client.impl.IClientRepositoryFacade;
import com.neoris.api.repository.movement.impl.IMovementRepositoryFacade;
import com.neoris.api.service.client.IClientService;
import com.neoris.api.util.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

import static com.neoris.api.model.enums.StateEnum.DELETED;
import static com.neoris.api.util.Util.*;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepositoryFacade clientRepository;

    private final IMovementRepositoryFacade movementRepository;

    @Override
    public ClientDto create(ClientDto client) {
        var clientEntity = converterObject(client, Client.class);
        clientEntity.setPassword(hashPassword(client.getPassword()));
        clientEntity = clientRepository.create(clientEntity);
        return converterObject(clientEntity, ClientDto.class);
    }

    @Override
    public ClientDto update(ClientDto client, Integer clientId) {
        var clientById = getIfExists(clientId);
        mergeObjects(client, clientById);
        var result = clientRepository.update(clientById);

        return converterObject(result, ClientDto.class);
    }

    @Override
    public Void delete(Integer clientId) {
        var client = getIfExists(clientId);
        client.setIsDeleted(true);
        client.setStatus(DELETED.name());
        clientRepository.update(client);

        return null;
    }

    @Override
    public List<ReportDto> generateReport(Integer clientId, Date from, Date to) {
        return movementRepository.findByClientAndDate(clientId, from, to);
    }

    private Client getIfExists(Integer clientId) {
        var client = clientRepository.findById(clientId);

        if (ObjectUtils.isEmpty(client)) {
            throw new NotFoundException("Client not found");
        }
        return client;
    }
}
