package com.financial.transaction.system.service;

import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserServiceClient {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceClient.class);

    private final String userServiceBaseUrl = "http://user-management-service:8080/user";

    @Autowired
    GenericApiService genericApiService;

    public UserResponseDto getUserByUserId(String userId) {
        String url = userServiceBaseUrl + "/getByUserId?userId={id}";
        Map<String, Object> uriMap = Map.of("id", userId);
        return genericApiService.getForEntity(url, UserResponseDto.class, uriMap, new HttpHeaders());
    }
}
