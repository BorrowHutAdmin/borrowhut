package com.borrowhut.ws.contoller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

@Component
@Path("/product")
public class ProductController {
  
	@GET
	@Path("/productname/{productname}/location{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductByname(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		
		
		return null;
	}
	@GET
	@Path("/productid/{productid}/location{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductById(@PathParam("productid")int productid,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		
		
		return null;
	}
	@GET
	@Path("/category/{category}/location{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductCategory(@PathParam("category")String category,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		
		
		return null;
	}
}
