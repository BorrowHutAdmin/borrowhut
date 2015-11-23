package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

public interface ProductService {
public JSONArray getSearchProduct(String productname,int productid,String category,float latitude,float longitude,float distance);
}
