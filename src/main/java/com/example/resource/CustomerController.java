package com.example.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.Customer;
import com.example.exceptions.CustomerNotFoundException;
import com.example.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "CustomerControllerAPI", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	@ApiOperation("get all customers")
	@ApiResponse(code = 200, response = Customer.class, responseContainer = "List", message = "all customers")
	public List<Customer> getAllCustomer() {
		log.info("getting all customers..");
		return customerService.listAll();
	}

	@PostMapping("/customers")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		if (customerService.getById(customer.getId()).isPresent())
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		log.info("Customer saved: {}", customer);
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCustomer.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customerDetails) {
		Optional<Customer> customer = customerService.getById(customerDetails.getId());
		if (!customer.isPresent()) {
			log.debug("customer is not available: {}", customerDetails);
			throw new CustomerNotFoundException("Customer is not available: " + customerDetails.getId());
		}
		Customer updatedCustomer = customerService.saveOrUpdate(customerDetails);
		log.debug("customer updated: {}", updatedCustomer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable(value = "id") String id) {
		Optional<Customer> customer = customerService.getById(id);
		log.debug("getting customer with id: {}", id);
		if (!customer.isPresent()) {
			log.debug("customer not available with id: {}", id);
			throw new CustomerNotFoundException("Customer is not available: " + id);
		}
		log.debug("getting customer by id: customer details is: {}", customer);
		return ResponseEntity.ok().body(customer);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") String id) {
		log.debug("deleting customer having id: {}", id);
		Optional<Customer> customer = customerService.getById(id);
		if (!customer.isPresent()) {
			log.debug("customer with id not avaible: {}", id);
			throw new CustomerNotFoundException("Customer is not available: " + id);
		}
		customerService.delete(id);
		log.debug("customer deleted successfully: {}", customer);
		return ResponseEntity.ok().build();
	}
}
