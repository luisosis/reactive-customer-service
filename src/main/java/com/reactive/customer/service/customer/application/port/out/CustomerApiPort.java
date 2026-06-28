package com.reactive.customer.service.customer.application.port.out;

import com.reactive.customer.service.customer.domain.model.UserResponse;
import reactor.core.publisher.Flux;

public interface CustomerApiPort {

    Flux<UserResponse> getAllCustomers();
}
