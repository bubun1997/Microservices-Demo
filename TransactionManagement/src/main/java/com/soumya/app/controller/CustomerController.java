package com.soumya.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.app.entity.Customer;
import com.soumya.app.responsedto.ResponseDto;
import com.soumya.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
@Scope("request")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.addCustomer(c));
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseDto>> getCustomersByNames(@RequestBody List<String> names){
		
		return ResponseEntity.ok(this.customerService.getCustomersByNames(names));
	}

}
