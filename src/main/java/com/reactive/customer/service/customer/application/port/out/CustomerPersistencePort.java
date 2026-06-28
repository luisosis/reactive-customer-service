package com.reactive.customer.service.customer.application.port.out;

import com.reactive.customer.service.customer.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerPersistencePort {

    Mono<Customer> findById(Long id);
}
