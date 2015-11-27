package com.borrowhut.ws.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.repository.CustomDispalyedUiCardRepository;

@Component
public class CallToAction {
	@Autowired
	private CustomDispalyedUiCardRepository customDispalyedUiCardRepository;

	public JSONArray getFronttokens(int uicId, String userSpecific, int partyId) {
		JSONArray fronttokencollection = new JSONArray();

		JSONObject object;
		Map<String, Object> recrod;
		switch (userSpecific.toLowerCase()) {

		case "y":
			if (customDispalyedUiCardRepository.checkPersonalisedcardForParty(partyId, uicId)) {

				List tokens = customDispalyedUiCardRepository.getTokenforCalltoAction(partyId, uicId, userSpecific);

				if (tokens != null && tokens.size() > 0) {
					
					for(Iterator itr = tokens.iterator(); itr.hasNext();){
						object = new JSONObject();
						recrod = (Map) itr.next();
						object.put("UIC_TOKEN", recrod.get("TOKEN_NAME"));
						object.put("UIC_TOKEN_VALUE",recrod.get("TOKEN_VALUE"));
						fronttokencollection.add(object);
					}
				}

			}
			
			break;
		case "n":
			List tokens = customDispalyedUiCardRepository.getTokenforCalltoAction(partyId, uicId, userSpecific);

			if (tokens != null && tokens.size() > 0) {
				
				for(Iterator itr = tokens.iterator(); itr.hasNext();){
					object = new JSONObject();
					recrod = (Map) itr.next();
					object.put("UIC_TOKEN", recrod.get("TOKEN_NAME"));
					object.put("UIC_TOKEN_VALUE",recrod.get("TOKEN_VALUE"));
					fronttokencollection.add(object);
				}
			}
			

			break;
		default:
			break;
		}
		

		return fronttokencollection;

	}
}
