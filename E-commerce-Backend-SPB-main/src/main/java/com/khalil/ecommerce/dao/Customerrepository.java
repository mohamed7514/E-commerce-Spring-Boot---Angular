package com.khalil.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.khalil.ecommerce.entity.Customer;

@CrossOrigin("http://localhost:4200")
public interface Customerrepository extends JpaRepository<Customer, Long>{

	Customer findByEmail(String email);
}
