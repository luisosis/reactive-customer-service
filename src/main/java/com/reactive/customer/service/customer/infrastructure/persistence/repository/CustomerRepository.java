package com.reactive.customer.service.customer.infrastructure.persistence.repository;

import com.reactive.customer.service.customer.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
