package com.borrowhut.ws.service;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

	@Override
	public JSONArray getSearchProduct(String productname, int productid, String category, float latitude,
			float longitude, float distance) {
		
	
		
				return null;
	}

}
