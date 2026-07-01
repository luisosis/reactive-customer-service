package com.reactive.customer.service.customer.application.usercase;

import com.github.benmanes.caffeine.cache.Cache;
import com.reactive.customer.service.customer.application.port.out.CustomerPersistencePort;
import com.reactive.customer.service.customer.application.port.in.GetCustomerUseCase;
import com.reactive.customer.service.customer.domain.model.Customer;
import com.reactive.customer.service.customer.infrastructure.persistence.mapper.UserMapper;
import com.reactive.customer.service.customer.infrastructure.persistence.repository.CustomerRepository;
import com.reactive.customer.service.customer.domain.model.UserResponse;
import com.reactive.customer.service.customer.infrastructure.client.UserApiClient;
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
    private final UserApiClient userApiClient;

    private final UserMapper userMapper;

    private final CustomerPersistencePort customerPersistencePort;

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerRepository.findAll())
                .map(entity ->
                        new Customer(entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getEmail(),entity.getPhone(),entity.getStatus()));
    }

    @Override
    public Mono<Customer> findById(Long id) {
        Customer cached = customerCache.getIfPresent(id);

        Customer cached2 = customerCache.getIfPresent(id);

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

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return Mono.fromCallable(() -> customerRepository.save(userMapper.toEntity(customer)))
                .map(userMapper::toDto);
    }

    @Override
    public Mono<Customer> findByIdClean(Long id) {
        return customerPersistencePort.findById(id);
    }

    @Override
    public Flux<UserResponse>callThird() {
        return userApiClient.getUsers();
    }


}
