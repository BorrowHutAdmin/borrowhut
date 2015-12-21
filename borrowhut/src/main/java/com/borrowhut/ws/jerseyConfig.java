package com.borrowhut.ws;

import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.context.annotation.Configuration;

import com.borrowhut.ws.contoller.BorrowLogController;
import com.borrowhut.ws.contoller.BorrowhutLookupController;
import com.borrowhut.ws.contoller.Home;
import com.borrowhut.ws.contoller.ProductController;
import com.borrowhut.ws.contoller.ProductListingController;
import com.borrowhut.ws.contoller.UiCardController;
import com.borrowhut.ws.domain.BorrowLog;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


@Configuration
public class jerseyConfig extends ResourceConfig {
	
	
	public jerseyConfig()
	{
		register(new JacksonJsonProvider(ObjectMapperFactory.create()));
		packages("com.borrowhut.ws.exception");
		register(Home.class);
		register(ProductListingController.class);
		register(UiCardController.class);
		register(ProductController.class);
		register(BorrowLogController.class);
		register(BorrowhutLookupController.class);
		
		
	}
	
	
}
