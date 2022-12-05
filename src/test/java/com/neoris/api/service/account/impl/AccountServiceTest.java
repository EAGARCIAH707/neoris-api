package com.neoris.api.service.account.impl;

import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.model.entity.Account;
import com.neoris.api.model.entity.Client;
import com.neoris.api.repository.account.impl.IAccountRepositoryFacade;
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
class AccountServiceTest {


    @InjectMocks
    AccountService accountService;

    @Mock
    IAccountRepositoryFacade accountRepository;

    @Mock
    IClientRepositoryFacade clientRepository;


    @Test
    void create() {
        var accountMock = new Account();
        accountMock.setAccountId(1);
        when(clientRepository.findById(any())).thenReturn(new Client());
        when(accountRepository.create(any())).thenReturn(accountMock);

        var response = accountService.create(new AccountDto());
        assertEquals(1, response.getAccountId());
    }

    @Test
    void createNotFound() {
        var accountMock = new Account();
        accountMock.setAccountId(1);
        when(clientRepository.findById(any())).thenReturn(null);

        try {
            accountService.create(new AccountDto());

        } catch (Exception e) {
            assertEquals("Client not found", e.getMessage());

        }

    }

    @Test
    void update() {
        var accountMock = new Account();
        accountMock.setAccountId(1);
        when(accountRepository.findById(any())).thenReturn(accountMock);
        when(accountRepository.update(any())).thenReturn(accountMock);

        var response = accountService.update(new AccountDto(), 1);
        assertEquals(1, response.getAccountId());
    }

    @Test
    void delete() {
        var accountMock = new Account();
        accountMock.setAccountId(1);
        when(accountRepository.findById(any())).thenReturn(accountMock);
        when(accountRepository.update(any())).thenReturn(accountMock);
        accountService.delete(1);
        assertDoesNotThrow(() -> accountService.delete(1));
    }

    @Test
    void deleteNotFound() {
        var accountMock = new Account();
        accountMock.setAccountId(1);
        when(accountRepository.findById(any())).thenReturn(null);
        try {
            accountService.delete(1);
        } catch (Exception e) {
            assertEquals("Account not found", e.getMessage());

        }

    }
}