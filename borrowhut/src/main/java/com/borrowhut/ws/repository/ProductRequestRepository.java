package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.ProductRequest;


	public interface ProductRequestRepository extends JpaRepository<ProductRequest, Integer> {
		
		//public ProductRequest findByprdId(int productid);

	

}
