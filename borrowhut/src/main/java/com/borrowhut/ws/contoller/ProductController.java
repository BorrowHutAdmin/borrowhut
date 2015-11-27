package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.helper.ProductHelper;
import com.borrowhut.ws.service.ProductService;

@Component
@Path("/searchProduct")
public class ProductController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
  private final ProductService productService;
  @Inject
 public ProductController(final ProductService productService) {
	this.productService = productService;
}
	@GET
	@Path("/productname/{productname}/location/{location}/distance/{distance}")
		@Produces("application/json")
	public JSONArray searchProductByname(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("distance")float distanceinmiles) throws NumberFormatException, IllegalArgumentException, ProductNotFoundException{
		if(!ProductHelper.validateProductSearch(productName, location, distanceinmiles))
		throw new IllegalArgumentException("one of the input parameter is invalid");
		String[] lanlong =location.split(",");
		LOGGER.debug("latitude"+Float.parseFloat(lanlong[0].toString()));
		LOGGER.debug("longitude"+lanlong[1].toString());
		LOGGER.debug("distance in miles"+distanceinmiles);
		
		return productService.getSearchProduct( productName, 0, null, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
		
		
	}
	@GET
	@Path("/productname/{productname}/productid/{productid}/location/{location}/distance/{distance}")
		@Produces("application/json")
	public JSONArray searchByProductnameAndProductid(@PathParam("productname")String productName,@PathParam("productid")int productid,@PathParam("location")String location,@PathParam("distance")float distanceinmiles) throws NumberFormatException, IllegalArgumentException, ProductNotFoundException{
		if(!ProductHelper.validateProductSearch(productName, location, distanceinmiles))
		throw new IllegalArgumentException("one of the input parameter is invalid");
		String[] lanlong =location.split(",");
		LOGGER.debug("latitude"+Float.parseFloat(lanlong[0].toString()));
		LOGGER.debug("longitude"+lanlong[1].toString());
		LOGGER.debug("distance in miles"+distanceinmiles);
		LOGGER.debug("product id"+productid);
	
		return productService.getSearchProduct( productName, productid, null, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
		
		
	}
	
		@GET
		@Path("/productname/{productname}/category/{category}/location/{location}/distance/{distance}")
			@Produces("application/json")
		public JSONArray searchByProductnameAndCategory(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("distance")float distanceinmiles,@PathParam("category")String category) throws NumberFormatException, IllegalArgumentException, ProductNotFoundException{
			if(!ProductHelper.validateProductSearch(productName, location, distanceinmiles))
			throw new IllegalArgumentException("one of the input parameter is invalid");
			String[] lanlong =location.split(",");
			LOGGER.debug("latitude"+Float.parseFloat(lanlong[0].toString()));
			LOGGER.debug("longitude"+lanlong[1].toString());
			LOGGER.debug("distance in miles"+distanceinmiles);
			LOGGER.debug("Category id"+category);
		
			return productService.getSearchProduct(productName, 0, category, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
			
			
		}
	
		@GET
		@Path("/productname/{productname}/productid/{productid}/category/{category}/location/{location}/distance/{distance}")
			@Produces("application/json")
		public JSONArray searchByProductnameAndProductidAndCategory(@PathParam("productname")String productName,@PathParam("location")String location,@PathParam("productid")int productid,@PathParam("category")String categoryName,@PathParam("distance")float distanceinmiles) throws NumberFormatException, IllegalArgumentException, ProductNotFoundException{
			if(!ProductHelper.validateProductSearch(productName, location, distanceinmiles))
			throw new IllegalArgumentException("one of the input parameter is invalid");
			String[] lanlong =location.split(",");
			System.out.println("latitude"+Float.parseFloat(lanlong[0].toString()));
			System.out.println("longitude"+lanlong[1].toString());
			System.out.println("distance in miles"+distanceinmiles);
			System.out.println("productid id"+productid);
			System.out.println("Category id"+categoryName);
			return productService.getSearchProduct(productName, productid, categoryName, Float.parseFloat(lanlong[0].toString()), Float.parseFloat(lanlong[1].toString()), distanceinmiles);
			
			
		}
			
}
