package com.example.demo.client;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    private final RestTemplate restTemplate;
    private final String externalUserApiUrl;

    public UserClient(RestTemplate restTemplate,
                      @Value("${EXTERNAL_USER_API_URL}") String externalBookApiUrl) {
        this.restTemplate = restTemplate;
        this.externalUserApiUrl = externalBookApiUrl;
    }

    public UserDTO getUserById(Long userId, String authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                externalUserApiUrl + userId,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserDTO.class
        );

        return response.getBody();
    }
}