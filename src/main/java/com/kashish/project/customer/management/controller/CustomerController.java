package com.kashish.project.customer.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kashish.project.customer.management.model.Customer;
import com.kashish.project.customer.management.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
		
		return new ResponseEntity<Void>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/customers/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
		boolean deleted = customerService.deleteCustomer(customerId);
		if (deleted) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping(value = "/customers")
	public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) {
		
		return new ResponseEntity<Void>(customerService.updateCustomer(customer), HttpStatus.OK);
	}
}


