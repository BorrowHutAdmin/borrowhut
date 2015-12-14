package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.service.BorrowhutLookupService;

@Component
@Path("/getRefData")
public class BorrowhutLookupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowhutLookupController.class);
	private final BorrowhutLookupService borrowhutLookupService;
	
	@Inject
	public BorrowhutLookupController(final BorrowhutLookupService borrowhutLookupService)
	{
		this.borrowhutLookupService=borrowhutLookupService;		
	}
	@GET
	@Path("/tablename/{tablename}/filtercond/{filtercond}")
		@Produces("application/json")
	public JSONArray getLookupdatawithCond(@PathParam("tablename")String tablename,@PathParam("filtercond")String filtercond)
	{
		return borrowhutLookupService.getLookupdatawithCond(tablename,filtercond);
	}
	
	@GET
	@Path("/tablename/{tablename}")
		@Produces("application/json")
	public JSONArray getLookupdata(@PathParam("tablename")String tablename)
	{
		return borrowhutLookupService.getLookupdata(tablename.toUpperCase());
	}
	
}


