package com.borrowhut.ws.exception;


import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;



@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		HashMap<String, String> errormsg= new HashMap<String,String>();
		if(exception instanceof ListedProductNotFoundException)
		{		    
			errormsg.put("statuscode", "800");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().getName());
			errormsg.put("servicename", "ProductListingServiceImpl");					    
	    }
		if(exception instanceof PartyNotFoundException)
		{		    
			errormsg.put("statuscode", "800");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().getName());
			errormsg.put("servicename", "ProductListingServiceImpl");					    
	    }
		return Response.status(Status.BAD_REQUEST).entity(errormsg).build();
	}
	

}
