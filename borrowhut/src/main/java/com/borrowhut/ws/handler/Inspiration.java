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
import com.borrowhut.ws.repository.CustomDispalyedUiCardRepository;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@Component
public class Inspiration {

	private static String TOKEN_NAME_TOTAL_ITEMS = "TOTAL_ITEMS";

	private static String TOKEN_VALUE = "CALCULATED";
	private static String CARD_FACE = "FRONT";
	private static String TOKEN_NAME_CATEGORY = "CATEGORY";

	@Autowired
	private CustomDispalyedUiCardRepository customDispalyedUiCardRepository;
	
	public JSONArray getFronttokens(int ucid, CustomProductListingRepository customProductListingRepository,
			float latitude, float longitude) {

		JSONArray fronttokenscollection = new JSONArray();
		JSONObject object=new JSONObject();
		
		Map<String, Object> recrod;
		// check if token_value = "CALCULATED" and token_name ="TOTAL_ITEMS" and
		// face_card ="FRONT" exists
		Boolean isExist = customProductListingRepository.checkIsExistInDispUiCard(ucid, TOKEN_NAME_TOTAL_ITEMS,
				TOKEN_VALUE, CARD_FACE);
		if (isExist) {

			// if exists calculate count based on category in(token_names) and
			// product available = 'y'
			float distance = customDispalyedUiCardRepository.gettDistanceByUicId(ucid);
			List listofcategorywithcont = customProductListingRepository
					.getProductListeByCategoryAndCountBasedonTokenName(ucid, TOKEN_NAME_CATEGORY, latitude, longitude,
							distance);
			int totalitemscount = 0;
			for (Iterator itr = listofcategorywithcont.iterator(); itr.hasNext();) {
				recrod = (Map) itr.next();
				totalitemscount = totalitemscount + Integer.parseInt(recrod.get("CAT_COUNT").toString());
				
			}
			
			object.put(TOKEN_NAME_TOTAL_ITEMS,totalitemscount);
			
		}

		// get all token_name and token values expect Token_values =
		// "CALCULATED"
		List listoftokens = customDispalyedUiCardRepository.getTokenNameAndValueByUcidandCardface(ucid, "FRONT");
		for (Iterator itr = listoftokens.iterator(); itr.hasNext();) {
			recrod = (Map) itr.next();
			object.put(recrod.get("TOKEN_NAME"),  recrod.get("TOKEN_VALUE"));
			
						
		}
		fronttokenscollection.add(object);
		return fronttokenscollection;

	}

	public JSONArray getBacktokens(int ucid, CustomProductListingRepository customProductListingRepository,
			ProductListingRepository productListingRepository, float latitude, float longitude) {

		JSONArray backtokencollection = new JSONArray();
		JSONObject obj;
		Map<String, Object> record;
		String dynamicColumn = customDispalyedUiCardRepository.getDynamicColumn(ucid);
		if (dynamicColumn.length() > 1) {
			float distance = customDispalyedUiCardRepository.gettDistanceByUicId(ucid);
			int plsid = 0;
			List listofbacktoken = customProductListingRepository.getProductListForBackToken(ucid, latitude, longitude,
					distance,dynamicColumn);
			for (Iterator itr = listofbacktoken.iterator(); itr.hasNext();) {
				record = (Map) itr.next();
				obj = new JSONObject();
				for (Map.Entry<String, Object> entry : record.entrySet()) {
					System.out.println(entry.getKey() + "/" + entry.getValue());
					obj.put(entry.getKey(), entry.getValue());
					if (entry.getKey().equalsIgnoreCase("PLS_ID")) {
						plsid = (int) entry.getValue();
					}
				}
				String listedProductFeature = getProductlsiting(plsid, productListingRepository);
				obj.put("ListedProductFeature", listedProductFeature);
				backtokencollection.add(obj);

			}
		}
		return backtokencollection;

	}

	@Transactional
	private String getProductlsiting(int plsid, ProductListingRepository productListingRepository) {

		System.out.println("getting features");
		ProductListing productList = productListingRepository.findByplsId(plsid);
		String featurelist = "";
		for (ListedProductFeature feature : productList.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					: featurelist + "," + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}

		System.out.println("returning features" + featurelist);
		return featurelist;
	}
}
