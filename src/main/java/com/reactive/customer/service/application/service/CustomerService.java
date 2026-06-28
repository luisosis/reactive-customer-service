package com.reactive.customer.service.application.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.reactive.customer.service.application.usercase.GetCustomerUseCase;
import com.reactive.customer.service.domain.model.Customer;
import com.reactive.customer.service.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CustomerService implements GetCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final Cache<Long, Customer> customerCache;

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerRepository.findAll())
                .map(entity ->
                        new Customer(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getEmail(),entity.getPhone(),entity.getStatus()));
    }

    @Override
    public Mono<Customer> findById(Long id) {
        Customer cached = customerCache.getIfPresent(id);

        if (cached != null) {
            return Mono.just(cached);
        }
        return Mono.fromCallable(() -> customerRepository.findById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(Mono::justOrEmpty)
                .map(entity ->
                        new Customer(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getEmail(),entity.getPhone(),entity.getStatus()))
                .doOnNext(customer -> customerCache.put(id,customer));

        //put: para actualizar
        //invalidate: para eliminar
    }
}
