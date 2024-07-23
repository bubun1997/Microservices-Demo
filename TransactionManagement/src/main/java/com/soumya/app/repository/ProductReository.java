package com.soumya.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.app.entity.Product;

public interface ProductReository extends JpaRepository<Product, Long> {

}
