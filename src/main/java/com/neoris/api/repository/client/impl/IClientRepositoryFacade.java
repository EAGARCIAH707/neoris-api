package com.neoris.api.repository.client.impl;

import com.neoris.api.model.entity.Client;

public interface IClientRepositoryFacade {

    Client create(Client client);

    Client findById(Integer clientId);

    Client update(Client client);

}
