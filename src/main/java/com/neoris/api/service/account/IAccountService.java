package com.neoris.api.service.account;

import com.neoris.api.model.dto.AccountDto;

public interface IAccountService {

    AccountDto create(AccountDto account);

    AccountDto update(AccountDto account, Integer accountId);

    Void delete(Integer accountId);


}
