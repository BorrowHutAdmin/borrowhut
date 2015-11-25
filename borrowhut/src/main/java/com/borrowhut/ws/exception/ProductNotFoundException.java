package com.borrowhut.ws.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	
	public ProductNotFoundException(){
		
		super();
	}
public ProductNotFoundException(String message){
		
		super(message);
	}
}
