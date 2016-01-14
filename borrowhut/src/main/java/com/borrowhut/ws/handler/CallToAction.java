package com.borrowhut.ws.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.repository.CustomDispalyedUiCardRepository;
import com.borrowhut.ws.repository.CustomProductListingRepository;

@Component
public class CallToAction {
	@Autowired
	private CustomDispalyedUiCardRepository customDispalyedUiCardRepository;
	
	@Autowired
	private CustomProductListingRepository customProductListingRepository;

	public JSONArray getFronttokens(int uicId, String userSpecific, int partyId) {
		JSONArray fronttokencollection = new JSONArray();

		JSONObject object = new JSONObject();
		Map<String, Object> recrod;
		switch (userSpecific.toLowerCase()) {

		case "y":
			if (customDispalyedUiCardRepository.checkPersonalisedcardForParty(partyId, uicId)) 
			{
				
				 if(customProductListingRepository.checkIsExistInDispUiCard(uicId, "COINS", "CALCULATED", "FRONT") && customDispalyedUiCardRepository.checkPersonalisedTokenByTokenname(uicId, partyId, "COINS").size()>0) {
				
					
					 
					 
				List tokens = customDispalyedUiCardRepository.getTokenforCalltoAction(partyId, uicId, userSpecific);

				if (tokens != null && tokens.size() > 0) {
					
					for(Iterator itr = tokens.iterator(); itr.hasNext();){
						
						recrod = (Map) itr.next();
						object.put( recrod.get("TOKEN_NAME"),recrod.get("TOKEN_VALUE"));
						/*object.put("UIC_TOKEN_VALUE",recrod.get("TOKEN_VALUE"));*/
						
					}
					fronttokencollection.add(object);
				}
				}

			}
			
			break;
		case "n":
			List tokens = customDispalyedUiCardRepository.getTokenforCalltoAction(partyId, uicId, userSpecific);

			if (tokens != null && tokens.size() > 0) {
				
				for(Iterator itr = tokens.iterator(); itr.hasNext();){
					
					recrod = (Map) itr.next();
					object.put( recrod.get("TOKEN_NAME"),recrod.get("TOKEN_VALUE"));
					
				}
				fronttokencollection.add(object);
			}
			

			break;
		default:
			break;
		}
		

		return fronttokencollection;

	}
}
