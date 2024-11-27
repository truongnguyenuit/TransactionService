package com.example.demo.client;

import com.example.demo.dto.BookDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class BookClient {

    private final RestTemplate restTemplate;
    private final String externalBookApiUrl;

    public BookClient(RestTemplate restTemplate,
                      @Value("${EXTERNAL_BOOK_API_URL}") String externalBookApiUrl) {

        this.restTemplate = restTemplate;
        this.externalBookApiUrl = externalBookApiUrl;
    }

    public BookDTO getBookById(Long bookId) throws RestClientException {
        return restTemplate.getForObject(externalBookApiUrl + bookId, BookDTO.class);
    }

    public BookDTO updateBookIsAvailable(Long bookId, Boolean available) {
        String url = externalBookApiUrl + bookId;
        HttpEntity<Boolean> requestBody = new HttpEntity<>(available);

        ResponseEntity<BookDTO> response = restTemplate.exchange(url, HttpMethod.PUT, requestBody, BookDTO.class);

        return response.getBody();
    }
}
