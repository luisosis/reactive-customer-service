package com.reactive.customer.service.domain.mapper;

import com.reactive.customer.service.domain.model.Customer;
import com.reactive.customer.service.infrastructure.persistence.entity.CustomerEntity;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity e) {
        return new Customer(
                e.getId(),
                e.getFirstName(),
                e.getLastName(),
                e.getEmail(),
                e.getPhone(),
                e.getStatus()
        );
    }

    public static CustomerEntity toEntity(Customer d) {
        CustomerEntity e = new CustomerEntity();
        e.setId(d.id());
        e.setFirstName(d.firstName());
        e.setLastName(d.lastName());
        e.setEmail(d.email());
        e.setPhone(d.phone());
        e.setStatus(d.status());
        return e;
    }
}
