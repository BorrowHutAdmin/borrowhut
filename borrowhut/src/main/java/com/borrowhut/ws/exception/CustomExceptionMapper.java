package com.borrowhut.ws.exception;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.ParamException.QueryParamException;
import org.springframework.dao.DataIntegrityViolationException;

import com.borrowhut.ws.helper.BorrowhutConstant;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {

		HashMap<String, String> errormsg = new HashMap<String, String>();
		int statuscode = 0;
		if (exception instanceof UiCardNotFoundException) {
			errormsg = getErrorResponse(BorrowhutConstant.RECORD_NOT_FOUND, exception.getMessage(),
					exception.getClass().toString(), "getStream");
			statuscode = BorrowhutConstant.RECORD_NOT_FOUND;
		} else if (exception instanceof ProductNotFoundException) {
			errormsg = getErrorResponse(BorrowhutConstant.RECORD_NOT_FOUND, exception.getMessage(),
					exception.getClass().toString(), "searchProduct");
			statuscode = BorrowhutConstant.RECORD_NOT_FOUND;
		} else if (exception instanceof ListedProductNotFoundException ) {
			errormsg = getErrorResponse(BorrowhutConstant.RECORD_NOT_FOUND, exception.getMessage(),
					exception.getClass().toString(), "getListedProduct");
			statuscode = BorrowhutConstant.RECORD_NOT_FOUND;
		}else if ( exception instanceof PartyNotFoundException) {
			errormsg = getErrorResponse(BorrowhutConstant.RECORD_NOT_FOUND, exception.getMessage(),
					exception.getClass().toString(), "update/retriveCustomerDetails");
			statuscode = BorrowhutConstant.RECORD_NOT_FOUND;
			} else if (exception instanceof NotFoundException) {

			errormsg = getErrorResponse(BorrowhutConstant.INVALID_URL,
					"there is no resource available for given url, wrong url", exception.getClass().toString(),
					"borrowhut/*");

			statuscode = BorrowhutConstant.INVALID_URL;
		} else if (exception instanceof IllegalArgumentException || exception instanceof NumberFormatException
				|| exception instanceof QueryParamException || exception instanceof ParamException
				|| exception instanceof ParamException) {

			errormsg = getErrorResponse(BorrowhutConstant.ILLEGAL_ARGUMENTS, exception.getMessage(),
					exception.getClass().toString(), "borrowhut/*");

			statuscode = BorrowhutConstant.ILLEGAL_ARGUMENTS;
		} else if (exception instanceof NullPointerException) {
			errormsg = getErrorResponse(BorrowhutConstant.NULL_VALUES_IN_DB, exception.getMessage(),
					exception.getClass().toString(), "borrowhut/*");
			statuscode = BorrowhutConstant.NULL_VALUES_IN_DB;

		} else if (exception instanceof SQLException) {
			errormsg = getErrorResponse(BorrowhutConstant.DB_EXPECTIONS, exception.getMessage(),
					exception.getClass().toString(), "borrowhut/*");
			statuscode = BorrowhutConstant.DB_EXPECTIONS;

		} else if (exception instanceof DataIntegrityViolationException) {
			errormsg = getErrorResponse(BorrowhutConstant.DB_EXPECTIONS, exception.getMessage(),
					exception.getClass().toString(), "borrowhut/*");

		} else {
			errormsg = getErrorResponse(404, exception.getMessage(), exception.getClass().toString(), "borrowhut/*");
			statuscode = 404;
		}
		return Response.status(statuscode).entity(errormsg).build();
	}

	private HashMap<String, String> getErrorResponse(int statuscode, String message, String exceptionType,
			String servicename) {

		HashMap<String, String> errormsg = new HashMap<String, String>();
		errormsg.put("statuscode", String.valueOf(statuscode));
		errormsg.put("message", message);
		errormsg.put("exceptiontype", exceptionType);
		errormsg.put("servicename", servicename);
		return errormsg;

	}
}
