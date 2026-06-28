package com.reactive.customer.service.customer.application.port.in;

import com.reactive.customer.service.customer.domain.model.Customer;
import com.reactive.customer.service.customer.domain.model.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetCustomerUseCase {
    Flux<Customer> findAll();
    Mono<Customer> findById(Long id);

    Mono<Customer> saveCustomer(Customer customer);
    Mono<Customer> findByIdClean(Long id);

    Flux<UserResponse> callThird();
}
