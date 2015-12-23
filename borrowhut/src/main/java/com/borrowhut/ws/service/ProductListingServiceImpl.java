package com.borrowhut.ws.service;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.exception.ListedProductNotFoundException;
import com.borrowhut.ws.exception.PartyNotFoundException;
import com.borrowhut.ws.repository.ProductListingRepository;

@Service
@Validated
public class ProductListingServiceImpl implements ProductListingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingServiceImpl.class);
	private final ProductListingRepository productListingRepository;

	@Inject
	public ProductListingServiceImpl(final ProductListingRepository productListingRepository) {
		this.productListingRepository = productListingRepository;
	}

	@Override
	@Transactional
	public JSONObject getProductListingByPlsid(int plsid) throws ListedProductNotFoundException {

		ProductListing prod = productListingRepository.findByplsId(plsid);

		if (prod == null) {
			throw new ListedProductNotFoundException("Listed Product Id Not Found");
		}
		JSONObject object = new JSONObject();
		object.put("partyid", prod.getParty().getPtyId());
		object.put("productlisting", getProductlsiting(prod));

		return object;
	}

	@Override
	@Transactional
	public JSONArray getProductListingByPartyid(int partyid) throws PartyNotFoundException {
		List<ProductListing> prdlisting = productListingRepository.findByptyId(partyid);
		JSONArray jsonrecords = new JSONArray();
		JSONObject object;
		if (prdlisting != null && prdlisting.size() > 0) {
			for (ProductListing prdlist : prdlisting) {
				object = new JSONObject();
				object.put("productlisting", getProductlsiting(prdlist));
				object.put("partyid", prdlist.getParty().getPtyId());
				jsonrecords.add(object);
			}
		} else {
			throw new PartyNotFoundException("No Listed Product For This Party");
		}
		return jsonrecords;
	}

	@Transactional
	private String getProductlsiting(ProductListing prdlist) {
		String productlisting = "";
		Product pdt = prdlist.getProduct();
		Party pty = prdlist.getParty();
		String featurelist = "";
		productlisting = prdlist.getPlsId() + "," + pty.getPtyName() + "," + pty.getPtyPhoto() + ","
				+ pdt.getCategory().getCatName() + "," + pdt.getPrdName() + "," + pdt.getPrdDescription() + ","
				+ pdt.getPrdPhotoLink();
		for (ListedProductFeature feature : prdlist.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					: featurelist + "," + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}
		return featurelist.equals("") ? productlisting : productlisting + "," + featurelist;
	}
}
