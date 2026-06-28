package com.reactive.customer.service.infrastructure.client;

import com.reactive.customer.service.domain.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class UserApiClient {

    private final WebClient webClient;
    public UserApiClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Flux<UserResponse> getUsers() {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .doOnNext(userResponse -> log.info("usuario: {}", userResponse.name()))
                .doOnError(throwable -> log.error("Error consumiendo API", throwable));
    }
}
