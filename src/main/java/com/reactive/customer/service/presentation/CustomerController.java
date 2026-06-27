package com.reactive.customer.service.presentation;

import com.reactive.customer.service.application.usercase.GetCustomerUseCase;
import com.reactive.customer.service.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final GetCustomerUseCase useCase;

    @GetMapping
    public Flux<Customer> findAll(){
        return useCase.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Customer> findById(@PathVariable Long id){
        return useCase.findById(id);
    }
}
