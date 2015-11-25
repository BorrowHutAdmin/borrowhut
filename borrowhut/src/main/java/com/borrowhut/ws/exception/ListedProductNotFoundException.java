package com.borrowhut.ws.exception;

import java.io.Serializable;

public class ListedProductNotFoundException extends Exception implements Serializable{

	public ListedProductNotFoundException()
	{
		super();
	}
	
	public ListedProductNotFoundException(String msg)
	{
		super(msg);
	}
	
	public ListedProductNotFoundException(String msg,Exception exp)
	{
		super(msg,exp);
	}
	
}
