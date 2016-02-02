package com.borrowhut.ws.service;



import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.AuthMech;
import com.borrowhut.ws.domain.MyTrustCircle;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.PartyAuthMech;
import com.borrowhut.ws.domain.PartyAuthMechPK;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductRequest;
import com.borrowhut.ws.exception.PartyNotFoundException;
import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.repository.MyTrustCircleRepository;
import com.borrowhut.ws.repository.PartyAuthMechRepository;
import com.borrowhut.ws.repository.PartyRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.repository.ProductRepository;

@Service
@Validated
public class PartyServiceImpl implements PartyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UicardServiceImpl.class);
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private MyTrustCircleRepository myTrustCircleRepository;
	
	@Autowired
	private PartyAuthMechRepository partyAuthMechRepository;
	
	public void setPartyRepository(PartyRepository partyRepository) {
		this.partyRepository = partyRepository;
	}

	public PartyRepository getPartyRepository() {
		return partyRepository;
	}
	public void setMyTrustCircleRepository(MyTrustCircleRepository myTrustCircleRepository) {
		this.myTrustCircleRepository = myTrustCircleRepository;
	}

	public MyTrustCircleRepository getMyTrustCircleRepository() {
		return myTrustCircleRepository;
	}
	

	@Override
	public boolean isParty(int ptyid) {
		Party party=partyRepository.findOne(ptyid);
		if(!(party== null)){
			return true;
		}

		return false;
	}
	
	
	@Override
	public Boolean registerParty(String ptyName, String ptyMobile, String authToken) {
		
		if(!(ptyName==null || ptyMobile==null || authToken==null)){
			LOGGER.debug("registering party ");
			Party party=new Party();
			party.setPtyName(ptyName);
			party.setPtyMobile(ptyMobile);
			partyRepository.saveAndFlush(party);		
			
			PartyAuthMech pam =new PartyAuthMech();
			
			pam.setPtyId(party.getPtyId());
			pam.setAmhId("TW");
			pam.setPamAuthId("null");
			pam.setPamAuthToken(authToken);
			partyAuthMechRepository.saveAndFlush(pam);
			
			LOGGER.debug("registering party is done ");
			
			System.out.println("party "+party.getPtyId());
			
			
			return true;
		}
		return false;
		
	}

	@Override
	@Transactional
	public Boolean updatePartyDetailsById(int ptyId, String ptyName, String ptyAddressLine1, String ptyAddressLine2,
			String ptyAddressLine3, String ptyTown, String ptyCounty, String ptyPostCode, String ptyCountry,
			float ptyLatitude,float ptyLongitude, String ptyTel, String ptyMobile, String ptyEmail, String ptyPhoto,
			String ptyTrustScore) throws PartyNotFoundException {
		if(!(ptyId==0)){
			LOGGER.debug("updating party details of party id "+ptyId );
			Party party=partyRepository.findOne(ptyId);
			party.setPtyName(ptyName);
			party.setPtyAddressLine1(ptyAddressLine1);
			party.setPtyAddressLine2(ptyAddressLine2);
			party.setPtyAddressLine3(ptyAddressLine3);
			party.setPtyTown(ptyTown);
			party.setPtyCounty(ptyCounty);
			party.setPtyPostCode(ptyPostCode);
			party.setPtyCountry(ptyCountry);
			party.setPtyLatitude(ptyLatitude);
			party.setPtyLongitude(ptyLongitude);
			party.setPtyTel(ptyTel);
			party.setPtyMobile(ptyMobile);
			party.setPtyEmail(ptyEmail);
			party.setPtyPhoto(ptyPhoto);
			party.setPtyTrustScore(ptyTrustScore);
			partyRepository.saveAndFlush(party);
			LOGGER.debug("updating party details is done");
			
			return true;
		}else
			throw new PartyNotFoundException("Party with name not found");
			
	}


	@Override
	@Transactional
	public JSONObject retrievePartyDetailsById(int partyid ) throws PartyNotFoundException {
		
		Party party = partyRepository.findByptyId(partyid);
		
		if (party == null ) {
			throw new PartyNotFoundException("No Product Found");
		}
		LOGGER.debug("Retrieving  party details for paty id "+partyid);
		JSONObject obj1=new JSONObject();
		JSONObject 	ptyobj = new JSONObject();
		ptyobj.put("PTY_ID", party.getPtyId());
		ptyobj.put("PTY_NAME", party.getPtyName());
		ptyobj.put("PTY_ADDRESS_LINE_1", party.getPtyAddressLine1());
		ptyobj.put("PTY_ADDRESS_LINE_2", party.getPtyAddressLine2());
		ptyobj.put("PTY_ADDRESS_LINE_3", party.getPtyAddressLine3());
		ptyobj.put("PTY_TOWN", party.getPtyTown());
		ptyobj.put("PTY_COUNTY", party.getPtyCounty());
		ptyobj.put("PTY_POST_CODE", party.getPtyPostCode());
		ptyobj.put("PTY_COUNTRY", party.getPtyCountry());
		ptyobj.put("PTY_LATITUDE", party.getPtyLatitude());
		ptyobj.put("PTY_LONGITUDE", party.getPtyLongitude());
		ptyobj.put("PTY_TEL", party.getPtyTel());
		ptyobj.put("PTY_MOBILE", party.getPtyMobile());
		ptyobj.put("PTY_EMAIL", party.getPtyEmail());
		ptyobj.put("PTY_PHOTO", party.getPtyPhoto());
		ptyobj.put("PTY_TRUST_SCORE", party.getPtyTrustScore());
		
		LOGGER.debug("Retrieving  party details is done and details is "+ptyobj);
		LOGGER.debug("Retrieving  party "+party.getPtyId());
		obj1.put("PARTY", ptyobj);
		JSONArray trustarray=new JSONArray();
		
		List<MyTrustCircle> mtclist=myTrustCircleRepository.findByPtyId(partyid);
		if (mtclist!=null && mtclist.size()>0  ) {
			for(MyTrustCircle mtclist1: mtclist){
				JSONObject 	myobj = new JSONObject();
				myobj.put("PARTY_ID",mtclist1.getMtcContactPtyId());
				myobj.put("TRUST_CIRCLE",mtclist1.getMtcCircle());
				trustarray.add(myobj);
			}
		
		}
		obj1.put("TRUST_TABLE", trustarray);
		LOGGER.debug("Retrieving  party details is done and details is " +trustarray);
		return obj1;
		
		
		}

	

}
