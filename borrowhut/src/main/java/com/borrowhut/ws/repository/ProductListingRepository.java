package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.domain.ProductListingPK;

public interface ProductListingRepository extends JpaRepository<ProductListing, Integer> {

	/* @Query("SELECT p FROM ProductListing p WHERE p.ptyId = :partyid")
	    public List<ProductListing> find(@Param("partyid") int partyid);*/
	public List<ProductListing> findByptyId(int partyid);
}
