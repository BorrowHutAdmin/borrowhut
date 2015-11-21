package com.borrowhut.ws.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.repository.CustomProductListingRepository;

@Component
public class Inspiration {

	private static String TOKEN_NAME_TOTAL_ITEMS = "TOTAL_ITEMS";

	private static String TOKEN_VALUE = "CALCULATED";
	private static String CARD_FACE = "FRONT";
	private static String TOKEN_NAME_CATEGORY = "CATEGORY";

	public JSONArray getFronttokens(int ucid, CustomProductListingRepository customProductListingRepository) {

		JSONArray fronttokenscollection = new JSONArray();
		JSONObject object;
		Map<String, Object> recrod;
		List listofcategorywithcont = customProductListingRepository
				.getProductListeByCategoryAndCountBasedonTokenName(ucid, TOKEN_NAME_CATEGORY);
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

	public JSONArray getBacktokens(int ucid, CustomProductListingRepository customProductListingRepository) {

		return new JSONArray();

	}
}
