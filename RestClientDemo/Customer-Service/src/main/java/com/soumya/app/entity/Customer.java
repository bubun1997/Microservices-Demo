package com.soumya.app.entity;

import java.util.List;

import com.soumya.app.dto.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long customerId;
	
	@Column(name = "name")
	private String customerName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "PhNo")
	private String phno;
	
	@Transient
	private List<Product> products;
}
