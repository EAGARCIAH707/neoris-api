package com.neoris.api.service.movement.impl;

import com.neoris.api.model.dto.MovementDto;
import com.neoris.api.model.entity.Account;
import com.neoris.api.model.entity.Movement;
import com.neoris.api.repository.account.impl.IAccountRepositoryFacade;
import com.neoris.api.repository.movement.impl.IMovementRepositoryFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {
    @InjectMocks
    MovementService movementService;
    @Mock
    IMovementRepositoryFacade movementRepository;

    @Mock
    IAccountRepositoryFacade accountRepository;

    @Test
    void create() {
        var movementMock = new Movement();
        movementMock.setMovementId(1);
        movementMock.setBalance(BigDecimal.valueOf(1000));
        when(accountRepository.findById(any())).thenReturn(new Account());
        when(movementRepository.findLast()).thenReturn(movementMock);
        when(movementRepository.create(any())).thenReturn(movementMock);

        var request = new MovementDto();
        request.setValue(BigDecimal.valueOf(100));
        var response = movementService.create(request);
        assertEquals(1, response.getMovementId());

    }

    @Test
    void createWithoutMovement() {
        var movementMock = new Movement();
        movementMock.setMovementId(1);
        movementMock.setBalance(BigDecimal.valueOf(1000));
        var accountMock = new Account();
        accountMock.setInitialAmount(BigDecimal.valueOf(1000));
        when(accountRepository.findById(any())).thenReturn(accountMock);
        when(movementRepository.findLast()).thenReturn(null);
        when(movementRepository.create(any())).thenReturn(movementMock);
        var request = new MovementDto();
        request.setValue(BigDecimal.valueOf(-100));
        var response = movementService.create(request);
        assertEquals(1, response.getMovementId());
    }

    @Test
    void createNotFound() {
        var movementMock = new Movement();
        movementMock.setMovementId(1);
        when(accountRepository.findById(any())).thenReturn(null);
        try {
            movementService.create(new MovementDto());
        } catch (Exception e) {
            assertEquals("Account not found", e.getMessage());
        }
    }

    @Test
    void update() {
        var movementMock = new Movement();
        movementMock.setMovementId(1);
        movementMock.setBalance(BigDecimal.valueOf(1000));
        movementMock.setValue(BigDecimal.valueOf(100));
        movementMock.setType("CREDITO");
        when(movementRepository.findLast()).thenReturn(movementMock);
        when(movementRepository.update(any())).thenReturn(movementMock);
        when(movementRepository.findById(any())).thenReturn(movementMock);
        var request = new MovementDto();
        request.setValue(BigDecimal.valueOf(100));
        var result = movementService.update(request, 1);
        assert result != null;

    }
}