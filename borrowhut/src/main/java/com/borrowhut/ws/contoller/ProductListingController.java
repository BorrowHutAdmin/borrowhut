package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.service.ProductListingService;


@Component
@Path("/getListedProduct")
public class ProductListingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingController.class);

	private final ProductListingService productListingService;

	@Inject
	public ProductListingController(final ProductListingService productListingService) {
		LOGGER.info("intialized");
		this.productListingService = productListingService;
	}

	@GET
	@Path("/partyid/{partyid}")
	@Produces("application/json")
		public JSONArray getAllListedProductsBypartyid(@PathParam("partyid") int partyid) {
		LOGGER.debug("Received request for product listing for party " + partyid);

		return productListingService.getProductListingByPartyid(partyid);
	}

	@GET
	@Path("/plsid/{plsid}")
	@Produces("application/json")
	public JSONObject getAllListedProductsByplsid(@PathParam("plsid") int plsid) {
		LOGGER.debug("Received request for product listing for party " + plsid);

		return productListingService.getProductListingByPlsid(plsid);
	}
}
