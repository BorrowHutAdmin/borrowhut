package com.borrowhut.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.borrowhut.ws.contoller.ProductListingController;

@Configuration
public class jerseyConfig extends ResourceConfig {
	
	
	public jerseyConfig()
	{
		register(ProductListingController.class);
	}
	

}
