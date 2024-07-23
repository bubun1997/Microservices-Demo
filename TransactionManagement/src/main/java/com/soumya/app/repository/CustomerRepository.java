package com.soumya.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soumya.app.entity.Customer;
import com.soumya.app.responsedto.ResponseDto;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	  @Query("SELECT new com.soumya.app.responsedto.ResponseDto(c.customerName,c.email) from Customer c WHERE c.customerName in ?1")
	  List<ResponseDto> getCustomersByNames(List<String> names);
}
