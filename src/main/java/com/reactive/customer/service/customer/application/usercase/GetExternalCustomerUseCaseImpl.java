package com.reactive.customer.service.customer.application.usercase;

import com.reactive.customer.service.customer.application.port.out.CustomerApiPort;
import com.reactive.customer.service.customer.application.port.in.GetExternalCustomerUseCase;
import com.reactive.customer.service.customer.domain.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class GetExternalCustomerUseCaseImpl implements GetExternalCustomerUseCase {

    private final CustomerApiPort customerApiPort;

    @Override
    public Flux<UserResponse> callThirdClean() {
        return customerApiPort.getAllCustomers();
    }
}
