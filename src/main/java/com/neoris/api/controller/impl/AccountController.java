package com.neoris.api.controller.impl;

import com.neoris.api.controller.IAccountController;
import com.neoris.api.model.dto.AccountDto;
import com.neoris.api.service.account.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "/v0")
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final IAccountService accountService;

    @Override
    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto account) {
        var result = accountService.create(account);
        return new ResponseEntity<>(result, CREATED);

    }

    @Override
    @PatchMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Integer accountId, @RequestBody AccountDto account) {
        var result = accountService.update(account, accountId);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        var result = accountService.delete(accountId);
        return new ResponseEntity<>(result, NO_CONTENT);
    }

}
