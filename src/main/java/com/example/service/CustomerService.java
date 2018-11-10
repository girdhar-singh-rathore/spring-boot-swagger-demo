package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.Customer;

public interface CustomerService {

	List<Customer> listAll();

	Optional<Customer> getById(String id);

	Customer saveOrUpdate(Customer customr);

	void delete(String id);

}