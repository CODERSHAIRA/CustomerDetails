package com.kashish.project.customer.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	private String uuid;
	private String first_name;
	private String last_name;
	private String street;
	private String address ;
	private String city;
	private String state;
	private String email;
	private String phone;

}
