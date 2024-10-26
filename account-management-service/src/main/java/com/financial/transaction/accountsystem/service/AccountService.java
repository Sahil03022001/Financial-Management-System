package com.financial.transaction.accountsystem.service;

import com.financial.transaction.accountsystem.dtos.request.AccountRequestDto;
import com.financial.transaction.accountsystem.dtos.response.AccountResponseDto;
import com.financial.transaction.accountsystem.entity.Account;
import com.financial.transaction.accountsystem.repository.AccountRepository;
import com.financial.transaction.accountsystem.utils.AccountUtilsManager;
import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserServiceClient userServiceClient;

    public AccountResponseDto create(AccountRequestDto request) {
        UserResponseDto userResponseDTO = userServiceClient.getUserByUserId(request.getUserId());
        if (userResponseDTO.getUserId() == null) {
            throw new RuntimeException("User does not exists for userId: " + request.getUserId());
        }

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
        if (account == null) {
            account = new Account();
            String accountId = AccountUtilsManager.createAccountId();
            account.setAccountId(accountId);
            account.setUserId(userResponseDTO.getUserId());
            account.setAccountNumber(request.getAccountNumber());
            account.setAccountType(request.getAccountType());
            account.setCurrencyType(request.getCurrencyType());
            account.setCreated(new Date());
        }
        account.setBalance(request.getBalance());
        account.setUpdated(new Date());

        return new AccountResponseDto(account.getAccountNumber(), account.getUserId(), account.getBalance(),
                account.getCurrencyType(), account.getAccountStatus(), account.getCreated(), account.getUpdated());
    }
}
