package com.reactive.customer.service.customer.domain.model;

public record Customer(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String status
) {
}
