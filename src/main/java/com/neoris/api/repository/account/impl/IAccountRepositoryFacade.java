package com.neoris.api.repository.account.impl;

import com.neoris.api.model.entity.Account;

public interface IAccountRepositoryFacade {
    Account create(Account account);

    Account findById(Integer accountId);

    Account update(Account account);
}
