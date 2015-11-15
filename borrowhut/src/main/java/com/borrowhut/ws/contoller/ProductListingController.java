package com.borrowhut.ws.contoller;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.service.ProductListingService;

@RestController
public class ProductListingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingController.class);
	private final ProductListingService productListingService;

	@Inject
	public ProductListingController(final ProductListingService productListingService) {
		LOGGER.info("intialized");
		this.productListingService = productListingService;
	}

	@RequestMapping(value = "/getlistedproductcount", method = RequestMethod.GET)
	public String listUsers() {
		LOGGER.debug("Received request to list all users");
		long l = productListingService.getCount();
	
		return "yes";
	}

	@RequestMapping(value = "/getproductlisted", method = RequestMethod.GET)
	public ProductListing getAllListedProducts() {
		LOGGER.debug("Received request to list all users");

		return productListingService.getProductBylistingid(111, 1);
	}

	@RequestMapping(value = "/getListedProduct/partyid/{partyid}", method = RequestMethod.GET)
		public JSONArray getAllListedProductsBypartyid(@PathVariable("partyid") int partyid) {
		LOGGER.debug("Received request for product listing for party " + partyid);
		
		return productListingService.getProductListingByPartyid(partyid);
	}

	@RequestMapping(value = "/getListedProduct/plsid/{plsid}", method = RequestMethod.GET)
	public String getAllListedProductsByplsid(@PathVariable("plsid") int plsid) {
		LOGGER.debug("Received request for product listing for party " + plsid);

		return "yes";
	}
}
