package com.borrowhut.ws.contoller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
@Component
@Path("/home")
public class Home {
    @GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getWelcomemessage()
	{
		
		return "service is up and running";
	}
}
