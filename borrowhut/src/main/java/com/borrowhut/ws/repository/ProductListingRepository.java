package com.borrowhut.ws.repository;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.borrowhut.ws.domain.ProductListing;

public interface ProductListingRepository extends JpaRepository<ProductListing, Integer> {


	public List<ProductListing> findByptyId(int partyid);
  /* @NamedNativeQuery( query="SELECT count(*) FROM PRODUCT_LISTING WHERE CAT_NAME IN ( :categories)", name = "ProductListing" );*/
	
	
	/*public int findByCatNameIn(Collection<String> categories);*/
	
	@Query(nativeQuery=true,value="SELECT * FROM PRODUCT_LISTING pl WHERE pl.CAT_NAME IN (:catnames) " )
	List<ProductListing> getByCategories(@Param("catnames") String catnames);
	
	
	
}
