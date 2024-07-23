package com.soumya.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soumya.app.entity.Customer;
import com.soumya.app.entity.Product;
import com.soumya.app.exception.MyException;
import com.soumya.app.repository.CustomerRepository;
import com.soumya.app.responsedto.ResponseDto;


@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;
	
	private ProductService productService;

	public CustomerService(CustomerRepository customerRepository, ProductService productService) {
		this.customerRepository = customerRepository;
		this.productService = productService;
	}
	
	
	@Transactional
	public Customer addCustomer(Customer customer) {
		
		Customer savedCustomer = customerRepository.save(customer);
		
		List<Product> products = savedCustomer.getProducts();
		
		if(products == null) {
			
			throw MyException.
				  builder().
				  mssg("Products not found !! Transaction is rolling back...").
				  build();
		}
		
		products.forEach( product -> product.setCustomerid(customer.getCustomerId()));
		
		this.productService.addAllProducts(products);
		
		return savedCustomer;
	}
	public List<ResponseDto> getCustomersByNames(List<String> names){
		
		List<ResponseDto> response = this.customerRepository.getCustomersByNames(names);
		
		response.forEach( res -> System.err.println(res.getName()));
		
		return response;
	}
	
	

}
