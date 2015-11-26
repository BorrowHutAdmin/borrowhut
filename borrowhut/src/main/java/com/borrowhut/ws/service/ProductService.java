package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

import com.borrowhut.ws.exception.ProductNotFoundException;

public interface ProductService {
public JSONArray  getSearchProduct(String productName,int prdId,String catName,float latitude,
		float longitude, float distance) throws ProductNotFoundException;
}
