package com.reactive.customer.service.application.service;

import com.reactive.customer.service.application.usercase.GetCustomerUseCase;
import com.reactive.customer.service.domain.model.Customer;
import com.reactive.customer.service.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CustomerService implements GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerRepository.findAll())
                .map(customerEntity ->
                        new Customer(1L,"name","last", "email","phone","status"));
    }
}
