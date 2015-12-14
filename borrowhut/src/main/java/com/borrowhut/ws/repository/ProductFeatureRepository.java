package com.borrowhut.ws.repository;

import java.util.List;

import com.borrowhut.ws.domain.ProductFeature;

public interface ProductFeatureRepository {
	
	public List<ProductFeature> findByprdId(int productId);

}
