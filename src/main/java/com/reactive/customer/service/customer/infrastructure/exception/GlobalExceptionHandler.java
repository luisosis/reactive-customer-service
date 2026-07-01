package com.reactive.customer.service.customer.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    @ExceptionHandler(ExchangeApiException.class)
    public ResponseEntity<Map<String, Object>> handleExchangeApiException(ExchangeApiException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("cause", ex.getCause() != null ? ex.getCause().getMessage() : null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
