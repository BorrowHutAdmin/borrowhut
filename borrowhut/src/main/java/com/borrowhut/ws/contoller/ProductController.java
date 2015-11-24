package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;

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
	public JSONArray searchProductByname(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		String[] lanlong =location.split(",");
		System.out.println("latitude"+Float.parseFloat(lanlong[0].toString()));
		System.out.println("longitude"+lanlong[1].toString());
		System.out.println("distance in miles"+distanceinmiles);
		
		
		return productService.getSearchProduct("PRD.PRD_NAME", productName, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
	}
	@GET
	@Path("/productid/{productid}/location/{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductById(@PathParam("productid")String productid,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		String[] lanlong =location.split(",");
		System.out.println("latitude"+Float.parseFloat(lanlong[0].toString()));
		System.out.println("longitude"+lanlong[1].toString());
		System.out.println("distance in miles"+distanceinmiles);
		
		return productService.getSearchProduct("PL.PRD_ID", productid, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
	}
	@GET
	@Path("/categoryname/{category}/location/{location}/distance/{distance}")
	@Produces("application/json")
	public JSONArray searchProductCategory(@PathParam("category")String category,@PathParam("location")String location,@PathParam("distance")float distanceinmiles ){
		
		String[] lanlong =location.split(",");
		System.out.println("latitude"+Float.parseFloat(lanlong[0].toString()));
		System.out.println("longitude"+lanlong[1].toString());
		System.out.println("distance in miles"+distanceinmiles);
		
		return productService.getSearchProduct("PRD.CAT_NAME", category, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
	}
}
