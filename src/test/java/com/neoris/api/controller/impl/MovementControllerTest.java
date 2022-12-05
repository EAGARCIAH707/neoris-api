package com.neoris.api.controller.impl;

import com.neoris.api.model.dto.MovementDto;
import com.neoris.api.service.movement.IMovementService;
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
class MovementControllerTest {
    @InjectMocks
    MovementController movementController;
    @Mock
    IMovementService movementService;

    @Test
    void createClient() {
        var movementMock = new MovementDto();
        movementMock.setMovementId(1);
        when(movementService.create(any())).thenReturn(movementMock);
        var result = movementController.createMovement(movementMock);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getMovementId());
    }

    @Test
    void updateAccount() {
        var movementMock = new MovementDto();
        movementMock.setMovementId(1);
        when(movementService.update(any(), any())).thenReturn(movementMock);
        var result = movementController.updateMovement(1, movementMock);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getMovementId());
    }

    @Test
    void deleteAccount() {
        var movementMock = new MovementDto();
        movementMock.setMovementId(1);
        doNothing().when(movementService).delete(any());
        var result = movementController.deleteMovement(1);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}