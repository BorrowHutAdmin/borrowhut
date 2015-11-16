package com.borrowhut.ws.service;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public interface ProductListingService {

public JSONObject getProductListingByPlsid(int plsid);

public JSONArray getProductListingByPartyid(int partyid);

}
