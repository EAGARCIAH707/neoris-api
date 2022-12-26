package com.neoris.api.service.client.impl;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.dto.ReportDto;
import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.client.impl.IClientRepositoryFacade;
import com.neoris.api.repository.movement.impl.IMovementRepositoryFacade;
import com.neoris.api.service.client.IClientService;
import com.neoris.api.util.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

import static com.neoris.api.model.enums.StateEnum.DELETED;
import static com.neoris.api.util.Util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepositoryFacade clientRepository;

    private final IMovementRepositoryFacade movementRepository;

    @Transactional
    @Override
    public ClientDto create(ClientDto client) {
        log.info("create client: {}", client);
        var clientEntity = converterObject(client, Client.class);
        clientEntity.setPassword(hashPassword(client.getPassword()));
        clientEntity = clientRepository.create(clientEntity);

        return converterObject(clientEntity, ClientDto.class);
    }

    @Transactional
    @Override
    public ClientDto update(ClientDto client, Integer clientId) {
        log.info("update client by id {}: {}", clientId, client);
        var clientById = getIfExists(clientId);
        mergeObjects(client, clientById);
        var result = clientRepository.update(clientById);

        return converterObject(result, ClientDto.class);
    }

    @Transactional
    @Override
    public Void delete(Integer clientId) {
        log.info("delete client by id {}", clientId);
        var client = getIfExists(clientId);
        client.setIsDeleted(true);
        client.setStatus(DELETED.name());
        clientRepository.update(client);

        return null;
    }

    @Override
    public List<ReportDto> generateReport(Integer clientId, Date from, Date to) {
        log.info("generate client report, client id : {}, from {} to {}", clientId, from, to);

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
