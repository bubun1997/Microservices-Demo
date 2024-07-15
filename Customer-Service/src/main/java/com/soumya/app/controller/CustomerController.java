package com.soumya.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.app.entity.Customer;
import com.soumya.app.service.CustomerService;

@RestController
@RequestMapping("/customers")
@Scope(scopeName = "request")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			     produces = {MediaType.APPLICATION_JSON_VALUE}
				 )
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.addCustomer(customer));
	}
	
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Customer>> getAllCustomers(){
    	
    	return ResponseEntity.ok(this.customerService.getAllCustomers());
    	
    }
    
    @GetMapping( path = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
    	
    	return ResponseEntity.ok(this.customerService.getCustomerById(id));
    }
}
