package com.borrowhut.ws.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.DisplayedUiCard;
import com.borrowhut.ws.domain.UiCard;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.PartyRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.repository.UiCardRepository;


@Service
@Validated
public class UicardServiceImpl implements UicardService {

	private final PartyRepository partyRepository;
	private final UiCardRepository uiCardRepository;
	private final ProductListingRepository productListingRepository;
	
	@Autowired
	private CustomProductListingRepository customProductListingRepository;
	
@Inject
	public UicardServiceImpl(final PartyRepository partyRepository, final UiCardRepository uiCardRepository,
			final ProductListingRepository productListingRepository) {
		this.partyRepository = partyRepository;
		this.uiCardRepository = uiCardRepository;
		this.productListingRepository = productListingRepository;
	}



	/*@Transactional
	public JSONArray getUicard(int paryId, String pamAuthId, String userLocation) {

		List<UiCard> listuicard = uiCardRepository.findByUserSpecific("N");
		JSONArray jarry = new JSONArray();
		JSONObject json;
		String categories ="";
		for(UiCard ui:listuicard){
			
			json = new JSONObject();
			json.put("UIC_ID",ui.getId());
			json.put("UIC_NAME", ui.getName());
			List<DisplayedUiCard> displayeduicardslist = ui.getDisplayedUiCards();
			if (displayeduicardslist != null) {
				switch (ui.getHandlerClass()) {
						
				case "com.borrowhut.controller.inspiration":
					
					categories=	getCategories(displayeduicardslist);
					for (DisplayedUiCard d : displayeduicardslist) {
						if (d.getTokenValue().equals("CALCULATED")) {
							
							if (d.getTokenName().equals("TOTAL_ITEMS")) {
								json.put("UIC_TOKEN", d.getTokenName());
																						
							json.put("UIC_TOKEN_VALUE", productListingRepository.getByCategories(categories).size());
								
							}
							
							
						}
						
				
					}
					break;
				case "com.borrowhut.controller.category":

					break;
				case "com.borrowhut.controller.callToAction":

					break;
				case "com.borrowhut.controller.messages":

					break;
				case "com.borrowhut.controller.advertisement":

					break;
				default:

					break;
			}
			
			
		}
			jarry.add(json);
		}
	

		return jarry;
	}
	
	private String getCategories(List<DisplayedUiCard> displayeduicards){
		
		String cat ="";
		for(DisplayedUiCard d:displayeduicards){
			
			if(d.getTokenName().equals("CATEGORY")){
				
				cat = cat +"'"+d.getTokenValue()+"',";
			}
			
			
		}
		if(cat.length()>3)
		cat=cat.substring(0, cat.length()-1);
		
		return cat;
	}

	*/
@Transactional
	 public JSONArray getUicard(int paryId, String pamAuthId, String userLocation){
		
		
		//  get all records with USER_SPECIFIC is equal to "N"
		JSONArray jrray = new JSONArray();
		
		JSONObject job ;
		
	List  listofuicard =	customProductListingRepository.getStream();
	System.out.println("list size"+listofuicard);
		for(Iterator itr=listofuicard.iterator();itr.hasNext();){
			
			Map<String, Object> recrod = (Map) itr.next();
			job = new JSONObject();
			System.out.println("ucid id"+recrod.get("UCID").toString());
			
		job.put("UIC.ID", recrod.get("UCID")); 
		
		job.put("UIC.NAME", recrod.get("NAME"));
		
		if(recrod.get("TOKEN_NAME").equals("TOTAL_ITEMS"))
		job.put("TOKENS", getTokenJsonarray(Integer.parseInt(recrod.get("UCID").toString())));
		jrray.add(job);
		}
		
		return jrray;
	}
	
	private JSONArray getTokenJsonarray(int ucid){
		JSONArray jrray = new JSONArray();
		JSONObject job ;
		List  listofcategorywithcont =	customProductListingRepository.getTotalProductListedCountByCategory(ucid);
		int i =0;
		for(Iterator itr=listofcategorywithcont.iterator();itr.hasNext();){
			job = new JSONObject();
			Map<String, Object> recrod = (Map) itr.next();
			i = i+Integer.parseInt(recrod.get("CAT_COUNT").toString());
		job.put("token_name", "CATEGORY");
		job.put("token_value", recrod.get("CAT_NAME"));
		jrray.add(job);
		}
		job = new JSONObject();
		job.put("token_name", "TOTAL_ITEMS");
		
		job.put("token_value", i);
		jrray.add(job);
		return jrray;
	}
}
