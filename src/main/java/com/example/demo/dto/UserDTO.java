package com.example.demo.dto;

import java.util.Date;

public record UserDTO(
        Long id,
        String name,
        String email,
        String password,
        USER_ROLE role,
        Date createdAt,
        Date updatedAt
) {
}
