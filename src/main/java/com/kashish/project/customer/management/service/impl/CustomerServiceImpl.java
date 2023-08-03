package com.kashish.project.customer.management.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kashish.project.customer.management.model.Customer;
import com.kashish.project.customer.management.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	

	private RestTemplate restTemplate = new RestTemplate(); 
	
	@Override
	public List<Customer> getCustomers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");
		
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
        		"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Customer>>() {});

		return response.getBody();
	}

	@Override
	public Void createCustomer(Customer customer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");
		
		// Create a HttpEntity with headers
        HttpEntity<Object> httpEntity = new HttpEntity<>(customer, headers);
		
     // Make the POST request and specify the response type using ParameterizedTypeReference
        restTemplate.exchange(
        		"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create",
                HttpMethod.POST,
                httpEntity,
                Void.class);
		return null;

	}

	@Override
	public boolean deleteCustomer(String customerId) {
		
		long count = getCustomers().stream().filter(c -> c.getUuid().equals(customerId)).count();
		
		
		if(count >= 1) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");
		
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		
        ResponseEntity<Void> response = restTemplate.exchange(
        		"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+customerId,
                HttpMethod.POST,
                httpEntity,
                Void.class);
        
		return response.getStatusCode() == HttpStatusCode.valueOf(200)? true : false;
		}
		return false;

	}


	@Override
	public Void updateCustomer(Customer customer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=");
		
        HttpEntity<Object> httpEntity = new HttpEntity<>(customer, headers);
		
        restTemplate.exchange(
        		"https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="+customer.getUuid(),
                HttpMethod.POST,
                httpEntity,
                Void.class);
		return null;

	}

}
