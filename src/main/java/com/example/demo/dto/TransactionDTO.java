package com.example.demo.dto;

import java.util.Date;

public record TransactionDTO(
        Long id,
        BookDTO book,
        UserDTO user,
        Boolean isReturned,
        Date createdAt,
        Date updatedAt
) {
}
