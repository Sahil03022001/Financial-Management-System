package com.financial.transaction.accountsystem.service;

import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceClient.class);

    private final String userServiceBaseUrl = "http://localhost:8081/user";

    @Autowired
    GenericApiService genericApiService;

    public UserResponseDto getUserByUserId(String userId) {
        String url = userServiceBaseUrl + "/getByUserId?userId={id}";
        Map<String, Object> uriMap = Map.of("id", userId);
        return genericApiService.getForEntity(url, UserResponseDto.class, uriMap, new HttpHeaders());
    }
}
