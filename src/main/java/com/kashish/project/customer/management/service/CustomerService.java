package com.kashish.project.customer.management.service;

import java.util.List;

import com.kashish.project.customer.management.model.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public Void createCustomer(Customer customer);

	public boolean deleteCustomer(String customerId);

	public Void updateCustomer(Customer customer);

}
