package com.borrowhut.ws.exception;

import java.io.Serializable;

public class PartyNotFoundException  extends Exception implements Serializable{
	public PartyNotFoundException()
	{
		super();
	}
	public PartyNotFoundException(String msg)
	{
		super(msg);
	}
	public PartyNotFoundException(String msg,Exception exp)
	{
		super(msg,exp);
	}
}
