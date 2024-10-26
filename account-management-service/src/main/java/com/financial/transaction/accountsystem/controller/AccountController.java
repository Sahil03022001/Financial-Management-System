package com.financial.transaction.accountsystem.controller;

import com.financial.transaction.accountsystem.dtos.request.AccountRequestDto;
import com.financial.transaction.accountsystem.dtos.response.AccountResponseDto;
import com.financial.transaction.accountsystem.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/account")
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDto> create(@Valid @RequestBody AccountRequestDto request) {
        AccountResponseDto response = new AccountResponseDto();
        try {
            response = accountService.create(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Exception while creating/updating account with userId: {} and accountNumber: {}. Exception: {}", request.getUserId(), request.getAccountNumber(), e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
