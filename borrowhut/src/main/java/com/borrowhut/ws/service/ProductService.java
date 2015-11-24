package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

public interface ProductService {
public JSONArray  getSearchProduct(String searchCriteria,String searchValue, float latitude,
		float longitude, float distance);
}
