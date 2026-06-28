package com.reactive.customer.service.customer.infrastructure.persistence.adapter;

import com.reactive.customer.service.customer.application.port.out.CustomerPersistencePort;
import com.reactive.customer.service.customer.domain.model.Customer;
import com.reactive.customer.service.customer.infrastructure.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPersistencePort {

    private final CustomerRepository repository;

    @Override
    public Mono<Customer> findById(Long id) {
        return Mono.fromCallable(() -> repository.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(entity ->
                        Mono.justOrEmpty(entity))
                .map(entity ->
                        new Customer(entity.getId(),entity.getFirstName(),entity.getLastName(),
                                entity.getEmail(),entity.getPhone(),entity.getStatus()));
    }
}
