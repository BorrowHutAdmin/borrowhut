package com.borrowhut.ws.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.exception.UiCardNotFoundException;
import com.borrowhut.ws.handler.CallToAction;
import com.borrowhut.ws.handler.Inspiration;
import com.borrowhut.ws.handler.Product;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@Service
@Validated
public class UicardServiceImpl implements UicardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UicardServiceImpl.class);
	private static String FORNT_TOKENS = "CardFronttokens";
	private static String BACK_TOKENS = "CardReversetokens";
	@Autowired
	private CustomProductListingRepository customProductListingRepository;
	
	@Autowired
	private ProductListingRepository productListingRepository;

	private final Inspiration inspirartion;
	private final Product product;
	private final CallToAction callToAction;

	@Inject
	public UicardServiceImpl(final Inspiration inspirartion,final Product product,final CallToAction callToAction) {

		this.inspirartion = inspirartion;
		this.product =product;
		this.callToAction =callToAction;
	}
	@Override
	@Transactional
	public JSONArray getUicard(int partyid,  float latitude,float longitude) throws UiCardNotFoundException {

		JSONArray jrray = new JSONArray();
		JSONObject job;
		Map<String, Object> recrod;
		// get all records with USER_SPECIFIC is equal to "N"
		List listofuicard = customProductListingRepository.getUiCards();
		if (listofuicard!=null && listofuicard.size()>0  ) {
				for (Iterator itr = listofuicard.iterator(); itr.hasNext();) {
		
					recrod = (Map) itr.next();
					job = new JSONObject();
					LOGGER.debug("ucid id" + recrod.get("ID").toString());
					
		
					switch (recrod.get("HANDLER_CLASS").toString()) {
		
					case "com.borrowhut.controller.inspiration":
						LOGGER.debug("firing inspiration handler");
						/*job.put("UIC.ID", recrod.get("ID"));
						job.put("UIC.NAME", recrod.get("NAME"));*/
						job.put("UIC_ID", recrod.get("ID"));
						job.put("UIC_NAME", recrod.get("NAME"));
						JSONArray fronttokenscollection = inspirartion.getFronttokens(Integer.parseInt(recrod.get("ID").toString()), customProductListingRepository,latitude,longitude);
								
						job.put(FORNT_TOKENS, fronttokenscollection);
						JSONArray backtokenscollection = inspirartion.getBacktokens(Integer.parseInt(recrod.get("ID").toString()), customProductListingRepository,productListingRepository,latitude,longitude);
								
						job.put(BACK_TOKENS, backtokenscollection);
						break;
					case "com.borrowhut.controller.product":
						LOGGER.debug("firing product handler");
						/*job.put("UIC.ID", recrod.get("ID"));
						job.put("UIC.NAME", recrod.get("NAME"));*/	
						job.put("UIC_ID", recrod.get("ID"));
						job.put("UIC_NAME", recrod.get("NAME"));
						JSONArray productlisting = product.getProductListingBasedOnLocation(Integer.parseInt(recrod.get("ID").toString()), latitude, longitude, partyid, customProductListingRepository, productListingRepository);
						job.put(FORNT_TOKENS, productlisting);
						break;
					case "com.borrowhut.controller.callToAction":
							LOGGER.debug("firing callToAction handler");
							/*job.put("UIC.ID", recrod.get("ID"));
							job.put("UIC.NAME", recrod.get("NAME"));*/
							job.put("UIC_ID", recrod.get("ID"));
							job.put("UIC_NAME", recrod.get("NAME"));
							JSONArray frontokens =	callToAction.getFronttokens(Integer.parseInt(recrod.get("ID").toString()), recrod.get("USER_SPECIFIC").toString(),partyid);
							job.put(FORNT_TOKENS, frontokens);
						break;
					default:
		
						break; 
					}
					jrray.add(job);
				}
		}
		else
		{
			throw new UiCardNotFoundException("Ui cards record(s) not found ");
		}
	
		return jrray;
	}
	
	
	

	
}
	