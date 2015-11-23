package com.borrowhut.ws.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.borrowhut.ws.domain.ListedProductFeature;

import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
@Component
public class Product {
	
	private static String TOKEN_NAME_DISTANCE = "DISTANCE"; 
public JSONArray getProductListingBasedOnLocation(int ucid,float latitude,float longitude,int partyid,CustomProductListingRepository customProductListingRepository,ProductListingRepository productListingRepository){
		
	
	List productlisting = 	customProductListingRepository.getProductListingBasedOnLocation(ucid, latitude, longitude, TOKEN_NAME_DISTANCE, 10);	
			JSONArray productising = new JSONArray();
			Map<String, Object> recrod;
			JSONObject object  = new JSONObject();
			int plsid =0;
			
									if(productlisting!=null){
											for(Iterator itr = productlisting.iterator(); itr.hasNext();){
												
												object = new JSONObject();
												recrod = (Map) itr.next();
												plsid =	Integer.parseInt(recrod.get("PLS_ID").toString())  ;
												object.put("PLS_ID",plsid);
												object.put("PTY_ID",Integer.parseInt(recrod.get("PTY_ID").toString()));
												object.put("PTY_PHOTO",recrod.get("PTY_PHOTO").toString());
												object.put("PRD_NAME",recrod.get("PRD_NAME").toString());
												object.put("PRD_PHOTO_LINK",recrod.get("PRD_PHOTO_LINK").toString());
										
												object.put("LISTED_PRODUCT_FEATURES", getProductlsiting(plsid,productListingRepository));
												productising.add(object);
											}
									
									
									
								}
				return productising;
} 

@Transactional
private String getProductlsiting(int plsid,ProductListingRepository productListingRepository) {
	
	
	
	ProductListing productList =	productListingRepository.getOne(plsid);
	String featurelist = "";
	for (ListedProductFeature feature : productList.getListedProductFeatures()) {
		featurelist = featurelist.equals("")
				? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
				:  featurelist+","  + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

	}
	return featurelist;
}
}
