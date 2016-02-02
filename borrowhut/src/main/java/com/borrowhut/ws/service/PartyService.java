package com.borrowhut.ws.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.borrowhut.ws.exception.PartyNotFoundException;

public interface PartyService {

	public boolean isParty(int ptyid);

	public Boolean updatePartyDetailsById(int ptyId, String ptyName, String ptyAddressLine1, String ptyAddressLine2,
			String ptyAddressLine3, String ptyTown, String ptyCounty, String ptyPostCode, String ptyCountry,
			float ptyLatitude, float ptyLongitude, String ptyTel, String ptyMobile, String ptyEmail, String ptyPhoto,
			String ptyTrustScore) throws PartyNotFoundException;

	public Boolean registerParty(String ptyName, String ptyMobile, String authToken);

	public JSONObject retrievePartyDetailsById(int partyid) throws PartyNotFoundException;

}
