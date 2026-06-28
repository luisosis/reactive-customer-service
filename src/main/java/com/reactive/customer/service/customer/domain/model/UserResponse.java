package com.reactive.customer.service.customer.domain.model;

public record UserResponse(
        Integer id,
        String name,
        String username,
        String email
) {
}
