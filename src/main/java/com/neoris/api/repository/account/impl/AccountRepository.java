package com.neoris.api.repository.account.impl;

import com.neoris.api.model.entity.Account;
import com.neoris.api.repository.account.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepository implements IAccountRepositoryFacade {
    private final IAccountRepository repository;

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    public Account findById(Integer accountId) {
        return repository.findByAccountIdAndIsDeleted(accountId, false);
    }

    @Override
    public Account update(Account account) {
        return repository.save(account);
    }
}
