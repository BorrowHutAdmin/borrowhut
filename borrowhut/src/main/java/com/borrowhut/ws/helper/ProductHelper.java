package com.borrowhut.ws.helper;

import java.util.HashSet;
import java.util.Set;

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
public static Boolean validateEvents(String event) {
	if(event.trim().equals(""))
		throw new IllegalArgumentException("event cannot be empty");
	
	Set<String> events = new HashSet<String>();
	events.add("BORROWED");
	events.add("CHECKEDIN");
	events.add("CHECKEDOUT");
	
	if(events.contains(event))
		return true;
	
	return false;
	
	
}

}
