package com.financial.transaction.accountsystem.service;

import com.financial.transaction.system.requests.AccountRequestDto;
import com.financial.transaction.system.response.AccountResponseDto;
import com.financial.transaction.accountsystem.entity.Account;
import com.financial.transaction.system.enums.AccountStatus;
import com.financial.transaction.accountsystem.repository.AccountRepository;
import com.financial.transaction.accountsystem.utils.AccountUtilsManager;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.system.service.UserServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            account.setAccountStatus(AccountStatus.ACTIVE);
        }
        account.setBalance(request.getBalance());
        account.setUpdated(new Date());

        accountRepository.save(account);

        return new AccountResponseDto(account.getAccountNumber(), account.getUserId(), account.getBalance(),
                account.getCurrencyType(), account.getAccountStatus(), account.getCreated(), account.getUpdated());
    }

    public AccountResponseDto getAccountById(String accountNumber) throws Exception {

        if (accountNumber == null) {
            throw new Exception("Provided Account number is null");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new Exception("Account does not exists with provided account number");
        }

        return new AccountResponseDto(account.getAccountNumber(), account.getUserId(), account.getBalance(),
                account.getCurrencyType(), account.getAccountStatus(), account.getCreated(), account.getUpdated());
    }

    public List<AccountResponseDto> getAccountsByUserId(String userId) {

        if (userId == null) {
            throw new RuntimeException("user id is null");
        }

        UserResponseDto userResponseDto = userServiceClient.getUserByUserId(userId);
        if (userResponseDto == null) {
            throw new RuntimeException("user not found");
        }

        List<Account> accounts = accountRepository.findByUserId(userId);
        if (accounts == null || accounts.size() == 0) {
            throw new RuntimeException("no accounts exists for given user");
        }

        List<AccountResponseDto> accountResponseDtoList = accounts.stream().map(account -> new AccountResponseDto(account.getAccountNumber(), account.getUserId(), account.getBalance(),
                account.getCurrencyType(), account.getAccountStatus(), account.getCreated(), account.getUpdated())).collect(Collectors.toList());

        return accountResponseDtoList;
    }
}
