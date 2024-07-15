package com.soumya.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
