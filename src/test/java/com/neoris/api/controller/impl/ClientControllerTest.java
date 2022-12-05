package com.neoris.api.controller.impl;

import com.neoris.api.model.dto.ClientDto;
import com.neoris.api.service.client.IClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @InjectMocks
    ClientController clientController;
    @Mock
    IClientService clientService;

    @Test
    void createClient() {
        var clientMock = new ClientDto();
        clientMock.setClientCode(1);
        when(clientService.create(any())).thenReturn(clientMock);
        var result = clientController.createClient(clientMock);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getClientCode());
    }

    @Test
    void updateAccount() {
        var clientMock = new ClientDto();
        clientMock.setClientCode(1);
        when(clientService.update(any(), any())).thenReturn(clientMock);
        var result = clientController.updateClient(1, clientMock);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getClientCode());
    }

    @Test
    void deleteAccount() {
        var clientMock = new ClientDto();
        clientMock.setClientCode(1);
        doNothing().when(clientService).delete(any());
        var result = clientController.deleteClient(1);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}