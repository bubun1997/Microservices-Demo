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

import com.soumya.app.entity.Product;
import com.soumya.app.service.ProductService;

@RestController
@RequestMapping("/products")
@Scope(scopeName = "request")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addAllProducts(@RequestBody List<Product> products) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.saveAllProducts(products));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {
		
		return ResponseEntity.ok(this.productService.getAllProducts());
	}
	
	@GetMapping(path = "{customerId}",produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Product>> getProductsByCustomerId(@PathVariable Long customerId){
		
		return ResponseEntity.ok(this.productService.getProductsByCustomerId(customerId));
	}
}
