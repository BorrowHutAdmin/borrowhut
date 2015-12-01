package com.borrowhut.ws.exception;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.ParamException.QueryParamException;

import com.borrowhut.ws.helper.BorrwhutConstant;



@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		
		HashMap<String, String> errormsg = new HashMap<String, String>();
		int statuscode  =0;
		if(exception instanceof IllegalArgumentException){
			
			errormsg = getErrorResponse(BorrwhutConstant.ILLEGAL_ARGUMENTS,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			
			statuscode =BorrwhutConstant.ILLEGAL_ARGUMENTS;
		}else if(exception instanceof NullPointerException){
			errormsg = getErrorResponse(BorrwhutConstant.NULL_VALUES_IN_DB,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			statuscode = BorrwhutConstant.NULL_VALUES_IN_DB;
					
		}else if(exception instanceof NumberFormatException){
			
			errormsg = getErrorResponse(BorrwhutConstant.ILLEGAL_ARGUMENTS,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			
			statuscode = BorrwhutConstant.ILLEGAL_ARGUMENTS;
		}else if(exception instanceof SQLException){
			errormsg = getErrorResponse(BorrwhutConstant.DB_EXPECTIONS,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			statuscode = BorrwhutConstant.DB_EXPECTIONS;
			
		}else if (exception instanceof ProductNotFoundException){
			errormsg = getErrorResponse(BorrwhutConstant.RECORD_NOT_FOUND,exception.getMessage(),exception.getClass().toString(),"searchProdduct");
			statuscode = BorrwhutConstant.RECORD_NOT_FOUND;
		}else if(exception instanceof ListedProductNotFoundException)
		{		    
			errormsg = getErrorResponse(800,exception.getMessage(),exception.getClass().toString(),"getListedProduct");
			statuscode = BorrwhutConstant.RECORD_NOT_FOUND;
		 }
		else if(exception instanceof PartyNotFoundException)
		{		   
			errormsg = getErrorResponse(BorrwhutConstant.RECORD_NOT_FOUND,exception.getMessage(),exception.getClass().toString(),"getListedProduct");
				
			statuscode = BorrwhutConstant.RECORD_NOT_FOUND;
	    }else if(exception instanceof UiCardNotFoundException)
		{		   
			errormsg = getErrorResponse(BorrwhutConstant.RECORD_NOT_FOUND,exception.getMessage(),exception.getClass().toString(),"getStream");
				
			statuscode = BorrwhutConstant.RECORD_NOT_FOUND;
	    }
		else if(exception instanceof QueryParamException){
			
			errormsg = getErrorResponse(BorrwhutConstant.ILLEGAL_ARGUMENTS,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			
			statuscode = BorrwhutConstant.ILLEGAL_ARGUMENTS;
		}else if(exception instanceof ParamException){
			
			errormsg = getErrorResponse(BorrwhutConstant.ILLEGAL_ARGUMENTS,"invalid parameter(s) value",exception.getClass().toString(),"borrowhut/*");
			
			statuscode = BorrwhutConstant.ILLEGAL_ARGUMENTS;
		}else if(exception instanceof NotFoundException){
			
			errormsg = getErrorResponse(BorrwhutConstant.INVALID_URL,"there is no resource available for given url, wrong url",exception.getClass().toString(),"borrowhut/*");
			
			statuscode = BorrwhutConstant.INVALID_URL;
		}else{
			errormsg = getErrorResponse(404,exception.getMessage(),exception.getClass().toString(),"borrowhut/*");
			statuscode=404;
		}
		 return Response.status(statuscode).entity(errormsg).build();
	}
	private HashMap<String, String> getErrorResponse(int statuscode,String message, String exceptionType,String servicename){
		
		
		HashMap<String, String> errormsg = new HashMap<String, String>();
		errormsg.put("statuscode", String.valueOf(statuscode));
		errormsg.put("message",message );
		errormsg.put("exceptiontype", exceptionType);
		errormsg.put("servicename", servicename);	
		return errormsg;
		
	}
}
