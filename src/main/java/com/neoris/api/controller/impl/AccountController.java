package com.neoris.api.controller.impl;

import com.neoris.api.controller.IAccountController;
import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.service.account.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Log4j2
@RestController
@RequestMapping(value = "/v0")
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto account) {
        var result = accountService.create(account);
        log.info("create account response: {}", result);

        return new ResponseEntity<>(result, CREATED);

    }

    @Override
    @PatchMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Integer accountId, @RequestBody AccountDto account) {
        var result = accountService.update(account, accountId);
        log.info("update account response: {}", result);

        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        var result = accountService.delete(accountId);
        log.info("successfully delete account");

        return new ResponseEntity<>(result, NO_CONTENT);
    }

}
