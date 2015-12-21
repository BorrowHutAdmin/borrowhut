package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findByprdId(int productid);

}