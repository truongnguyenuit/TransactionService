package com.example.demo.client;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
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

    public UserDTO getUserById(Long userId) {
        return restTemplate.getForObject(externalUserApiUrl + userId, UserDTO.class);
    }
}