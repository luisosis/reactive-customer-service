package com.reactive.customer.service.application.usercase;

import com.reactive.customer.service.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetCustomerUseCase {
    Flux<Customer> findAll();
    Mono<Customer> findById(Long id);
}
