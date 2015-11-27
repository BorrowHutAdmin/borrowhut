package com.borrowhut.ws.service;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.borrowhut.ws.exception.ListedProductNotFoundException;
import com.borrowhut.ws.exception.PartyNotFoundException;



public interface ProductListingService {

	public JSONObject getProductListingByPlsid(int plsid) throws  ListedProductNotFoundException;

	public JSONArray getProductListingByPartyid(int partyid) throws PartyNotFoundException;

}
