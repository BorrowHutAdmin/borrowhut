package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.ProductListing;

public interface ProductListingRepository extends JpaRepository<ProductListing, Integer> {


	public List<ProductListing> findByptyId(int partyid);

	
}
