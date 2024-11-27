package com.example.demo.dto;

import java.util.Date;

public record BookDTO(
        Long id,
        String title,
        String author,
        String category,
        Boolean isAvailable,
        Date createdAt,
        Date updatedAt) {
}
