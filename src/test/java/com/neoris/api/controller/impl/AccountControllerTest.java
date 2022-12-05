package com.neoris.api.controller.impl;

import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.service.account.IAccountService;
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
class AccountControllerTest {


    @InjectMocks
    AccountController accountController;

    @Mock
    IAccountService accountService;

    @Test
    void createAccount() {
        var accountMock = new AccountDto();
        accountMock.setAccountId(1);
        when(accountService.create(any())).thenReturn(accountMock);
        var result = accountController.createAccount(accountMock);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getAccountId());
    }

    @Test
    void updateAccount() {
        var accountMock = new AccountDto();
        accountMock.setAccountId(1);
        when(accountService.update(any(), any())).thenReturn(accountMock);
        var result = accountController.updateAccount(1, accountMock);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, Objects.requireNonNull(result.getBody()).getAccountId());
    }

    @Test
    void deleteAccount() {
        var accountMock = new AccountDto();
        accountMock.setAccountId(1);
        doNothing().when(accountService).delete(any());
        var result = accountController.deleteAccount(1);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}