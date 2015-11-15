package com.borrowhut.ws.service;



import org.json.simple.JSONArray;

import com.borrowhut.ws.domain.ProductListing;

public interface ProductListingService {
public long getCount();

public ProductListing getProductBylistingid(int prdid,int partyid );

public JSONArray getProductListingByPartyid(int partyid);

}
