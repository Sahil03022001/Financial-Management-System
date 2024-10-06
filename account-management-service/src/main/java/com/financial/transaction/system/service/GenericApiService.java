package com.financial.transaction.system.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class GenericApiService {

    private final RestTemplate restTemplate;

    public GenericApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables, HttpHeaders headers) {
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType, uriVariables);
        return response.getBody();
    }

    public <T, R> T postForEntity(String url, R requestBody, Class<T> responseType, HttpHeaders headers) {
        HttpEntity<R> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        return response.getBody();
    }

    public <T, R> T putForEntity(String url, R requestBody, Class<T> responseType, HttpHeaders headers) {
        HttpEntity<R> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        return response.getBody();
    }

    public <T> T deleteForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables, HttpHeaders headers) {
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, responseType, uriVariables);
        return response.getBody();
    }
}
