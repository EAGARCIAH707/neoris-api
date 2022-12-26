package com.neoris.api.service.account.impl;

import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.model.entity.Account;
import com.neoris.api.repository.account.impl.IAccountRepositoryFacade;
import com.neoris.api.repository.client.impl.IClientRepositoryFacade;
import com.neoris.api.service.account.IAccountService;
import com.neoris.api.util.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.neoris.api.util.Util.converterObject;
import static com.neoris.api.util.Util.mergeObjects;

@Log4j2
@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepositoryFacade accountRepository;

    private final IClientRepositoryFacade clientRepository;


    @Transactional
    @Override
    public AccountDto create(AccountDto account) {
        log.info("create account: {}", account);
        var client = clientRepository.findById(account.getClientId());
        if (ObjectUtils.isEmpty(client)) {
            throw new NotFoundException("Client not found");
        }
        var accountEntity = converterObject(account, Account.class);
        accountEntity = accountRepository.create(accountEntity);

        return converterObject(accountEntity, AccountDto.class);
    }

    @Transactional
    @Override
    public AccountDto update(AccountDto account, Integer accountId) {
        log.info("update account by id {}: {}", accountId, account);
        var accountById = getIfExists(accountId);
        mergeObjects(account, accountById);

        var result = accountRepository.update(accountById);
        return converterObject(result, AccountDto.class);

    }

    @Transactional
    @Override
    public Void delete(Integer accountId) {
        log.info("delete account by id {}", accountId);
        var account = getIfExists(accountId);
        account.setIsDeleted(true);
        account.setStatus(false);
        accountRepository.update(account);

        return null;
    }

    private Account getIfExists(Integer accountId) {
        var account = accountRepository.findById(accountId);

        if (ObjectUtils.isEmpty(account)) {
            throw new NotFoundException("Account not found");
        }
        return account;
    }
}
