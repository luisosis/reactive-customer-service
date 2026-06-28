package com.reactive.customer.service.customer.presentation;

import com.reactive.customer.service.customer.application.port.in.GetCustomerUseCase;
import com.reactive.customer.service.customer.application.port.in.GetExternalCustomerUseCase;
import com.reactive.customer.service.customer.domain.model.Customer;
import com.reactive.customer.service.customer.domain.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final GetCustomerUseCase useCase;
    private final GetExternalCustomerUseCase getExternalCustomerUseCase;

    @GetMapping
    public Flux<Customer> findAll() {
        return useCase.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Customer> findById(@PathVariable Long id) {
        return useCase.findById(id);
    }

    @PostMapping(value = "/customer")
    public Mono<Customer> saveCustomer(@RequestBody Customer customer) {
        return useCase.saveCustomer(customer);
    }

    @GetMapping(value = "/third")
    public Flux<UserResponse> callThird() {
        return useCase.callThird();
    }

    @GetMapping(value = "/external")
    public Flux<UserResponse> callThirdClean() {
        return getExternalCustomerUseCase.callThirdClean();
    }
}
