package com.reactive.customer.service.customer.infrastructure.persistence.mapper;

import com.reactive.customer.service.customer.domain.model.Customer;
import com.reactive.customer.service.customer.infrastructure.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CustomerEntity toEntity(Customer customer);
    Customer toDto(CustomerEntity customer);

}
