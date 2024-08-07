package com.soumya.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import com.soumya.app.dto.Product;
import com.soumya.app.entity.Customer;
import com.soumya.app.exception.ResourceNotFoundException;
import com.soumya.app.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;


@Service
@Scope(scopeName = "singleton")
@Log4j2
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RestClient restClient;
	
	@Transactional
	public Customer addCustomer(Customer cust) {
		
		 Customer savedCustomer = this.customerRepository.save(cust);
		 System.err.println(savedCustomer.getProducts());
		 String response = restClient.
		   post().
		   uri("/products")
		  .body(cust.
				  getProducts().
				  stream().
				  map( product -> {
					    product.setCustomerid(savedCustomer.getCustomerId());
					    return product;
				  }).collect(Collectors.toList())
				  
			   ).retrieve().body(String.class);
		 
         log.info(response);
         
		return savedCustomer;
	}

	public List<Customer> getAllCustomers(){
		
		List<Customer> customers = this.customerRepository.findAll();
		
		
		List<Product> products = this.restClient.
								 get().
								 uri("/products").
								 retrieve().
								 body(new ParameterizedTypeReference<List<Product>>() {
			 							
								     });
		
		customers.forEach(customer ->{
			
			System.err.println(customer.getProducts());

			
			List<Product> productsByCustomer = products.
											   stream().
											   filter(product -> customer.getCustomerId().equals(product.getCustomerid())).
											   collect(Collectors.toList());
			
			 customer.setProducts(productsByCustomer);
			
		});
		
		return customers;
	}
	public Customer getCustomerById(Long id)  {
		
		Customer customer = this.customerRepository.findById(id).orElse(null);
		
		if(customer != null) {
			
			List<Product> products = this.restClient.
					                      get().
					                      uri("/products/{id}", id).
					                      retrieve().
					                      body(new ParameterizedTypeReference<List<Product>>() {
					                        
					                      });
			
			customer.setProducts(products);
			
			return customer;
		}
		
		throw ResourceNotFoundException.
			  builder().
			  message("Customer not found with id : "+id).
			  build();
	}
}
