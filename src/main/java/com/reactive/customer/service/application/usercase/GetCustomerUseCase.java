package com.reactive.customer.service.application.usercase;

import com.reactive.customer.service.domain.model.Customer;
import reactor.core.publisher.Flux;

public interface GetCustomerUseCase {
    Flux<Customer> findAll();
}
