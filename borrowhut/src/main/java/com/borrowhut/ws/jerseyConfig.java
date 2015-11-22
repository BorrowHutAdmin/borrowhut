package com.borrowhut.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.borrowhut.ws.contoller.Home;
import com.borrowhut.ws.contoller.ProductListingController;
import com.borrowhut.ws.contoller.UiCardController;


@Configuration
public class jerseyConfig extends ResourceConfig {
	
	
	public jerseyConfig()
	{
		
		register(Home.class);
		register(ProductListingController.class);
		register(UiCardController.class);
		
	}
	

}
