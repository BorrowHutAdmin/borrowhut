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

import com.borrowhut.ws.handler.Inspiration;
import com.borrowhut.ws.repository.CustomProductListingRepository;

@Service
@Validated
public class UicardServiceImpl implements UicardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UicardServiceImpl.class);
	@Autowired
	private CustomProductListingRepository customProductListingRepository;

	private final Inspiration inspirartion;

	@Inject
	public UicardServiceImpl(final Inspiration inspirartion) {

		this.inspirartion = inspirartion;
	}

	@Transactional
	public JSONArray getUicard(int paryId, String pamAuthId, String userLocation) {

		JSONArray jrray = new JSONArray();
		JSONObject job;
		Map<String, Object> recrod;
		// get all records with USER_SPECIFIC is equal to "N"
		List listofuicard = customProductListingRepository.getUiCardswithUserSpecific("N");

		for (Iterator itr = listofuicard.iterator(); itr.hasNext();) {

			recrod = (Map) itr.next();
			job = new JSONObject();
			LOGGER.debug("ucid id" + recrod.get("ID").toString());
			job.put("UIC.ID", recrod.get("ID"));

			job.put("UIC.NAME", recrod.get("NAME"));

			switch (recrod.get("HANDLER_CLASS").toString()) {

			case "com.borrowhut.controller.inspiration":
				LOGGER.debug("firing inspiration handler");
				JSONArray fronttokenscollection = inspirartion
						.getFronttokens(Integer.parseInt(recrod.get("ID").toString()), customProductListingRepository);
				job.put("CardFronttokens", fronttokenscollection);
				JSONArray backtokenscollection = inspirartion
						.getBacktokens(Integer.parseInt(recrod.get("ID").toString()), customProductListingRepository);
				job.put("CardReversetokens", backtokenscollection);
				break;
			default:

				break;
			}
			jrray.add(job);
		}

		return jrray;
	}

	
}
