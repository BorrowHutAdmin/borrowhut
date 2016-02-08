package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.exception.PartyNotFoundException;
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
		  String authToken= custreg.get("AUTH_TOKEN").toString();
		  
		  if(ptyName==null || ptyMobile==null || ptyName=="" || ptyMobile=="" || authToken==null || authToken=="" ){
			  throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
		  }
		  
		  Boolean b=partyService.registerParty(ptyName,ptyMobile,authToken);
		  
		  JSONObject result = new JSONObject();
			result.put("result", b == true ? "success" : "failure");
			return result;
	 
	  }
	  @Path("/updateCustomerDetails")
		@POST
		@Produces("application/json")
		public JSONObject updatePartyDetailsById(@Valid JSONObject custdetails) throws IllegalAccessException, PartyNotFoundException{
		  
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
				throw new PartyNotFoundException("Party id "+ptyid+" is not active user ");
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
				throw new PartyNotFoundException("Party id "+partyid+" is not active user ");
			}
		  
			return partyService.retrievePartyDetailsById(partyid);	  
	  }
	  
	  @Path("/createRequest")
		@POST
		@Produces("application/json")
		public JSONObject getCreateRequest(@Valid JSONObject createreq) throws IllegalAccessException, PartyNotFoundException{
			
			int ptyid=Integer.parseInt(createreq.get("PTY_ID").toString());
			int prdid=Integer.parseInt(createreq.get("PRD_ID").toString());
			String catname=createreq.get("CAT_NAME").toString();
			String proddesc=createreq.get("PROD_DESC").toString();
			
			System.out.println("ptyid "+ ptyid);
			System.out.println("prdid "+ prdid);
			System.out.println("catname "+ catname);
			System.out.println("proddesc "+ proddesc);
			
			
			if(ptyid == 0){
				throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
			}else if(!partyService.isParty(ptyid)){
				throw new PartyNotFoundException("Party id "+ptyid+" is not active user ");
			}/*else if(!productService.isProductBelongsToCategory(prdid,catname)){
				throw new IllegalArgumentException("Product id "+prdid+" is not blongs to Category id "+catname);
			}*/
			
			Boolean b =partyService.createRequest(ptyid,prdid,catname,proddesc);
			JSONObject result = new JSONObject();
			result.put("result", b == true ? "success" : "failure");
			return result;
		}

}
