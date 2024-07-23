package com.soumya.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soumya.app.entity.Product;
import com.soumya.app.repository.ProductReository;

@Service
public class ProductService {
	
	@Autowired
	private ProductReository productReository;

	@Transactional
	public List<Product> addAllProducts(List<Product> products) {
		
		return this.productReository.saveAll(products);
	}
}
