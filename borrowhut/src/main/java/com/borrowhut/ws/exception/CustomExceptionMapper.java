package com.borrowhut.ws.exception;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;



@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		// TODO Auto-generated method stub
		System.out.println("mapper is fired");
		HashMap<String, String> errormsg = new HashMap<String, String>();
		int statuscode  =0;
		if(exception instanceof IllegalArgumentException){
			
	
	
				errormsg.put("statuscode", "801");
				errormsg.put("message", exception.getMessage());
				errormsg.put("exceptiontype", exception.getClass().toString());
				errormsg.put("servicename", "searchProdduct");
				statuscode =801;
		}else if (exception instanceof ProductNotFoundException){
			
			errormsg.put("statuscode", "800");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().toString());
			errormsg.put("servicename", "searchProdduct");
			statuscode = 800;
		}else if(exception instanceof NullPointerException){
			errormsg.put("statuscode", "802");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().toString());
			errormsg.put("servicename", "searchProdduct");
			statuscode = 802;
			
			
		}else if(exception instanceof SQLException){
			
			errormsg.put("statuscode", "803");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().toString());
			errormsg.put("servicename", "searchProdduct");
			statuscode = 803;
			
		}else if(exception instanceof ListedProductNotFoundException)
		{		    
			errormsg.put("statuscode", "800");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().getName());
			errormsg.put("servicename", "ProductListingServiceImpl");	
			statuscode = 800;
			
	    }
		else if(exception instanceof PartyNotFoundException)
		{		    
			errormsg.put("statuscode", "800");
			errormsg.put("message", exception.getMessage());
			errormsg.put("exceptiontype", exception.getClass().getName());
			errormsg.put("servicename", "ProductListingServiceImpl");	
			statuscode = 800;
	    }
		 return Response.status(statuscode).entity(errormsg).build();
	}

}
