package com.neoris.api.service.client.impl;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.client.impl.IClientRepositoryFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @InjectMocks
    ClientService clientService;

    @Mock
    private IClientRepositoryFacade clientRepository;

    @Test
    void create() {
        var clientMock = new Client();
        clientMock.setClientCode(1);

        var request = new ClientDto();
        request.setPassword("abadaba");
        when(clientRepository.create(any())).thenReturn(clientMock);
        var result = clientService.create(request);

        assertEquals(1, result.getClientCode());
    }

    @Test
    void update() {
        var clientMock = new Client();
        clientMock.setClientCode(1);
        when(clientRepository.update(any())).thenReturn(clientMock);
        when(clientRepository.findById(any())).thenReturn(clientMock);
        var result = clientService.update(new ClientDto(), 1);

        assertEquals(1, result.getClientCode());
    }

    @Test
    void delete() {
        var clientMock = new Client();
        clientMock.setClientCode(1);
        when(clientRepository.update(any())).thenReturn(clientMock);
        when(clientRepository.findById(any())).thenReturn(clientMock);
        assertDoesNotThrow(() -> clientService.delete(1));
    }

    @Test
    void deleteNotFound() {
        var clientMock = new Client();
        clientMock.setClientCode(1);
        when(clientRepository.findById(any())).thenReturn(null);
        try {
            clientService.delete(1);
        } catch (Exception e) {
            assertEquals("Client not found", e.getMessage());

        }
    }
}