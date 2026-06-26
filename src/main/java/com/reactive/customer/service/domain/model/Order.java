package com.reactive.customer.service.domain.model;

import java.math.BigDecimal;

public record Order(
        Long id,
        Long customerId,
        String orderNumber,
        BigDecimal amount,
        String status
) {
}
