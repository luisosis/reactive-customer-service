package com.reactive.customer.service.customer.infrastructure.exception;

public class ExchangeApiException extends RuntimeException{

    public ExchangeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
