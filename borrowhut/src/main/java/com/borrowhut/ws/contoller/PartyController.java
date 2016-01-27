package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.exception.PartyNotFoundException;
import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.service.PartyService;

@Component
@Path("/")
public class PartyController {
	
	private final PartyService partyService;
	  
	  @Inject
	 public PartyController(final PartyService partyService) {
		this.partyService = partyService;
	}
	  	@Path("/registerCustomer")
		@POST
		@Produces("application/json")
		public JSONObject registerParty(@Valid JSONObject custreg) throws IllegalAccessException{
		  String ptyName=custreg.get("PTY_NAME").toString();
		  String ptyMobile =custreg.get("PTY_MOBILE").toString();
		  
		  if(ptyName==null || ptyMobile==null || ptyName=="" || ptyMobile==""){
			  throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
		  }
		  
		  Boolean b=partyService.registerParty(ptyName,ptyMobile);
		  
		  JSONObject result = new JSONObject();
			result.put("result", b == true ? "success" : "failure");
			return result;
	 
	  }
	  @Path("/UpdateCustomerDetails")
		@POST
		@Produces("application/json")
		public JSONObject updatePartyDetailsById(@Valid JSONObject custdetails) throws IllegalAccessException{
		  
		  int ptyid=Integer.parseInt(custdetails.get("PTY_ID").toString());
		  String ptyname=custdetails.get("PTY_NAME").toString();
		  String ptyaddressline1=custdetails.get("PTY_ADDRESS_LINE_1").toString();
		  String ptyaddressline2=custdetails.get("PTY_ADDRESS_LINE_2").toString();
		  String ptyaddressline3=custdetails.get("PTY_ADDRESS_LINE_3").toString();
		  String ptytown=custdetails.get("PTY_TOWN").toString();
		  String ptycounty=custdetails.get("PTY_COUNTY").toString();
		  String ptypostcode=custdetails.get("PTY_POST_CODE").toString();
		  String ptycountry=custdetails.get("PTY_COUNTRY").toString();
		  float ptylatitude=Float.parseFloat(custdetails.get("PTY_LATITUDE").toString());
		  float ptylongitude=Float.parseFloat(custdetails.get("PTY_LONGITUDE").toString());
		  String ptytel=custdetails.get("PTY_TEL").toString();
		  String ptymobile =custdetails.get("PTY_MOBILE").toString();
		  String ptyemail=custdetails.get("PTY_EMAIL").toString();
		  String ptyphoto=custdetails.get("PTY_PHOTO").toString();
		  String ptytrustScore=custdetails.get("PTY_TRUST_SCORE").toString();
		  
		  if(ptyid==0){
			  throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
		  }else if(!partyService.isParty(ptyid)){
				throw new IllegalArgumentException("Party id "+ptyid+" is not active user ");
			}
		  
		  
		  Boolean b =partyService.updatePartyDetailsById(ptyid,ptyname,ptyaddressline1,ptyaddressline2,ptyaddressline3,ptytown,ptycounty,ptypostcode,ptycountry,ptylatitude,ptylongitude,ptytel,ptymobile,ptyemail,ptyphoto,ptytrustScore);
			JSONObject result = new JSONObject();
			result.put("result", b == true ? "success" : "failure");
			return result;
	  }
	  
	  
	  @GET
		@Path("/retrieveCustomerDetails/partyid/{partyid}")
			@Produces("application/json")
		public JSONObject retrievePartyDetailsById(@PathParam("partyid")int partyid) throws PartyNotFoundException {
		  System.out.println("party id"+partyid);
		  if(partyid==0){
			  throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
		  }else if(!partyService.isParty(partyid)){
				throw new IllegalArgumentException("Party id "+partyid+" is not active user ");
			}
		  
			return partyService.retrievePartyDetailsById(partyid);	  
	  }

}
