package com.reactive.customer.service.customer.infrastructure.client;

import com.reactive.customer.service.customer.application.port.out.CustomerApiPort;
import com.reactive.customer.service.customer.domain.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CustomerApiAdapter implements CustomerApiPort {

    private final UserApiClient userApiClient;

    @Override
    public Flux<UserResponse> getAllCustomers() {
        return userApiClient.getUsers();
    }
}
