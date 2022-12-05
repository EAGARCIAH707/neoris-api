package com.neoris.api.repository.account;

import com.neoris.api.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountIdAndIsDeleted(Integer accountId, Boolean isDelete);
}
