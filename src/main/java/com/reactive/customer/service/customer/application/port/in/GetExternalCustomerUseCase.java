package com.reactive.customer.service.customer.application.port.in;

import com.reactive.customer.service.customer.domain.model.UserResponse;
import reactor.core.publisher.Flux;

public interface GetExternalCustomerUseCase {

    Flux<UserResponse> callThirdClean();
}
