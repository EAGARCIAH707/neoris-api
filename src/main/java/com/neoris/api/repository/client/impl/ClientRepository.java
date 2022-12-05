package com.neoris.api.repository.client.impl;

import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.client.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRepository implements IClientRepositoryFacade {
    private final IClientRepository repository;

    @Override
    public Client create(Client client) {
        return repository.save(client);
    }

    @Override
    public Client findById(Integer clientId) {
        return repository.findByPersonIdAndIsDeleted(clientId, false);
    }

    @Override
    public Client update(Client client) {
        return repository.save(client);
    }
}
