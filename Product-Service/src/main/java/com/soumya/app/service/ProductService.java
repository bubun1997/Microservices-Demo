package com.soumya.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.soumya.app.entity.Product;
import com.soumya.app.repository.ProductRepository;

@Service
@Scope(scopeName = "singleton")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public String saveAllProducts(List<Product> products) {
		
		this.productRepository.saveAll(products);
		
		return "products added !!";
	}
	public Product saveProduct(Product p) {
		
		return this.productRepository.save(p);
	}
	
	public List<Product> getAllProducts(){
		
		return this.productRepository.findAll();
	}
	public List<Product> getProductsByCustomerId(Long id){
		
		return this.productRepository.findByCustomerid(id);
	}
}
