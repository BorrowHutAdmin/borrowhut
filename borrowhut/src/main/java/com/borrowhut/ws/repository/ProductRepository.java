package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByprdId(int productid);

}
