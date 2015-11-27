package com.borrowhut.ws.helper;

public class ProductHelper {
public static boolean validateProductSearch(String productnameoridorcat,String location, float distance){
	
	
	if(productnameoridorcat==null ||  location == null ||  distance  == 0.0f){
		System.out.println("throwing illegal argument exception");
		
		return false;
	}else if(productnameoridorcat.trim().equals("") || location.trim().equals("") || !location.contains(",")){
		System.out.println("throwing illegal argument exception");
		return false;
		
	}
	return true;
}
}
