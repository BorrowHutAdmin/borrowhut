package com.borrowhut.ws.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@Component
public class Inspiration {

	private static String TOKEN_NAME_TOTAL_ITEMS = "TOTAL_ITEMS";

	private static String TOKEN_VALUE = "CALCULATED";
	private static String CARD_FACE = "FRONT";
	private static String TOKEN_NAME_CATEGORY = "CATEGORY";
	
	
	@Autowired
	private ProductListingRepository productListingRepository;

	public JSONArray getFronttokens(int ucid, CustomProductListingRepository customProductListingRepository,float latitude,float longitude) {

		JSONArray fronttokenscollection = new JSONArray();
		JSONObject object;
		Map<String, Object> recrod;
		List listofcategorywithcont = customProductListingRepository
				.getProductListeByCategoryAndCountBasedonTokenName(ucid, TOKEN_NAME_CATEGORY,latitude,longitude);
		int totalitemscount = 0;
		for (Iterator itr = listofcategorywithcont.iterator(); itr.hasNext();) {
			object = new JSONObject();
			recrod = (Map) itr.next();
			totalitemscount = totalitemscount + Integer.parseInt(recrod.get("CAT_COUNT").toString());
			object.put("token_name", TOKEN_NAME_CATEGORY);
			object.put("token_value", recrod.get("CAT_NAME"));
			fronttokenscollection.add(object);
		}
		Boolean isExist = customProductListingRepository.checkIsExistInDispUiCard(ucid, TOKEN_NAME_TOTAL_ITEMS,
				TOKEN_VALUE, CARD_FACE);
		if (isExist) {
			object = new JSONObject();
			object.put("token_name", TOKEN_NAME_TOTAL_ITEMS);

			object.put("token_value", totalitemscount);
			fronttokenscollection.add(object);
		}

		return fronttokenscollection;

	}
public JSONArray getBacktokens(int ucid, CustomProductListingRepository customProductListingRepository,ProductListingRepository productListingRepository,float latitude,float longitude) {
		
		JSONArray backtokencollection = new JSONArray();
		JSONObject obj;
		Map<String, Object> record;
		int plsid = 0;
		List listofbacktoken = customProductListingRepository.getProductListForBackToken(ucid,latitude,longitude);
		for (Iterator itr = listofbacktoken.iterator(); itr.hasNext();) {
			record = (Map) itr.next();
			obj= new JSONObject();
			for (Map.Entry<String, Object> entry : record.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			    obj.put(entry.getKey(), entry.getValue());
			    if(entry.getKey().equalsIgnoreCase("PLS_ID"))
			    {
			    	plsid=(int)entry.getValue();
			    }
			}
			String listedProductFeature = getProductlsiting(plsid,productListingRepository);
			obj.put("ListedProductFeature", listedProductFeature);
			backtokencollection.add(obj);
			
			
		
		}
		
		return backtokencollection;

	}
	
	@Transactional
	private String getProductlsiting(int plsid) {		
		
		System.out.println("getting features");
		ProductListing productList =	productListingRepository.getOne(plsid);
		String featurelist = "";
		for (ListedProductFeature feature : productList.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					:  featurelist+","  + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}
		
		System.out.println("returning features"+featurelist);
		return featurelist;
	}
}
