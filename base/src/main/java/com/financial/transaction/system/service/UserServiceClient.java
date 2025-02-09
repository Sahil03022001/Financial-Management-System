package com.financial.transaction.system.service;

import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU1IyMTI0MDU4OSIsImlhdCI6MTczOTEyMjIxNSwiZXhwIjoxNzM5MTU4MjE1fQ.fLG8Tv2adCQLDkmCqXOics58lXW_T7dr7q3i4F_dJ2M");

        UserResponseDto userResponseDto = genericApiService.getForEntity(
                url, new ParameterizedTypeReference<UserResponseDto>() {}, uriMap, headers
        );

        return userResponseDto;
    }
}
