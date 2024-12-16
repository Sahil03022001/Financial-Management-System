package com.financial.transaction.system.service;

import com.financial.transaction.system.requests.AccountRequestDto;
import com.financial.transaction.system.response.AccountResponseDto;
import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceClient.class);

    private final String accountServiceBaseUrl = "http://account-management-service:8080/account";

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
}
