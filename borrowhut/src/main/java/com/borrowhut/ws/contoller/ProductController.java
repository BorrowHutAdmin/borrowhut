package com.borrowhut.ws.contoller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.helper.ProductHelper;
import com.borrowhut.ws.service.ProductService;

@Component
@Path("/product")
public class ProductController {
  private final ProductService productService;
  @Inject
 public ProductController(final ProductService productService) {
	this.productService = productService;
}
	@GET
	@Path("/productname/{productname}/location/{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductByname(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ) throws IllegalArgumentException, ProductNotFoundException{
		if(!ProductHelper.validateProductSearch(productName, location, distanceinmiles))
		throw new IllegalArgumentException("one of the input parameter is invalid");
		String[] lanlong =location.split(",");
		System.out.println("latitude"+Float.parseFloat(lanlong[0].toString()));
		System.out.println("longitude"+lanlong[1].toString());
		System.out.println("distance in miles"+distanceinmiles);
		int prdId=0;
		String catName="";		
		
		return productService.getSearchProduct( productName, prdId,catName,Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
	}
	
	/*@ExceptionHandler({IllegalArgumentException.class,NullPointerException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and with a non empty string as 'name'");
	}*/
	
	
}
