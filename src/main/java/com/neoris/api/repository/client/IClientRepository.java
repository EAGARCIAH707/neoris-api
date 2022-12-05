package com.neoris.api.repository.client;

import com.neoris.api.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    Client findByPersonIdAndIsDeleted(Integer clientId, boolean isDeleted);

}
