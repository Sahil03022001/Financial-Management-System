package com.financial.transaction.system.service;

import com.financial.transaction.system.dtos.request.AccountRequestDto;
import com.financial.transaction.system.dtos.response.AccountResponseDto;
import com.financial.transaction.system.entity.Account;
import com.financial.transaction.system.repository.AccountRepository;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import com.financial.transaction.system.utils.AccountUtilsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    private final String GET_USER_RESPONSE = "/user/getByUserId";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserServiceClient userServiceClient;

    public AccountResponseDto create(AccountRequestDto request) {
        UserResponseDTO userResponseDTO = userServiceClient.getUserByUserId(request.getUserId());
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
