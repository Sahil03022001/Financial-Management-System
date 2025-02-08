package com.financial.transaction.system.service;

import com.financial.transaction.system.requests.AccountRequestDto;
import com.financial.transaction.system.requests.TransactionRequestByAccountNumber;
import com.financial.transaction.system.response.AccountResponseDto;
import com.financial.transaction.system.response.TransactionResponseDto;
import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceClient.class);

    private final String accountServiceBaseUrl = "http://account-management-service:8080/account";
    private final String transactionManagementBaseUrl = "http://account-management-service:8080/transaction";

    @Autowired
    GenericApiService genericApiService;

    public AccountResponseDto createAccount(AccountRequestDto request) {
        String url = accountServiceBaseUrl + "/create";
        AccountResponseDto response = genericApiService.postForEntity(url, request, AccountResponseDto.class, new HttpHeaders());
        return response;
    }

    public AccountResponseDto getAccountByNumber(String accountNumber) {
        String url = accountServiceBaseUrl + "/get/" + accountNumber;
        Map<String, Object> uriMap = new HashMap<>();
        return genericApiService.getForEntity(url, AccountResponseDto.class, uriMap, new HttpHeaders());
    }

    public List<AccountResponseDto> getAccountsByUserId(String userId) {
        String url = accountServiceBaseUrl + "/getAccountsByUserId?userId=" + userId;
        return genericApiService.getForEntity(url, new ParameterizedTypeReference<>() {}, new HashMap<>(), new HttpHeaders());
    }

    public AccountResponseDto getAccountByMobileNumber(String mobileNumber) {
        String url = accountServiceBaseUrl + "/getAccountByMobileNumber?mobileNumber=" + mobileNumber;
        Map<String, Object> uriMap = new HashMap<>();
        return genericApiService.getForEntity(url, AccountResponseDto.class, uriMap, new HttpHeaders());
    }

    public TransactionResponseDto transferMoney(TransactionRequestByAccountNumber request) {
        String url = transactionManagementBaseUrl + "transact";
        return genericApiService.postForEntity(url, request, TransactionResponseDto.class, new HttpHeaders());
    }
}
