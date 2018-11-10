package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Customer;
import com.example.domain.CustomerId;

public interface CustomerRepository extends CrudRepository<Customer, CustomerId> {
}