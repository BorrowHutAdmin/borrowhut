package com.borrowhut.ws.service;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.ProductListingRepository;

@Service
@Validated
public class ProductListingServiceImpl implements ProductListingService {
	private final ProductListingRepository productListingRepository;

	@Inject
	public ProductListingServiceImpl(final ProductListingRepository productListingRepository) {
		this.productListingRepository = productListingRepository;
	}

	@Override
	public JSONObject getProductListingByPlsid(int plsid) {
		ProductListing prod = productListingRepository.findOne(plsid);
		JSONObject object = new JSONObject();
		if(prod!=null){
		
		object.put("partyid", prod.getParty().getPtyId());
		object.put("productlisting", getProductlsiting(prod));
		}
		return object;
	}

	@Override
	@Transactional
	public JSONArray getProductListingByPartyid(int partyid) {
		List<ProductListing> prdlisting = productListingRepository.findByptyId(partyid);
		JSONArray jsonrecords = new JSONArray();
		JSONObject object;
		if (prdlisting != null) {
			for (ProductListing prdlist : prdlisting) {
				object = new JSONObject();
				object.put("productlisting", getProductlsiting(prdlist));
				object.put("partyid", prdlist.getParty().getPtyId());
				jsonrecords.add(object);
			}
		}
		return jsonrecords;
	}
	@Transactional
	private String getProductlsiting(ProductListing prdlist) {
		String productlisting = "";
		Product pdt = prdlist.getProduct();
		String featurelist = "";
		productlisting = prdlist.getPlsId() + "," + pdt.getCategory().getCatName() + "," + pdt.getPrdName() + ","
				+ pdt.getPrdDescription() + "," + pdt.getPrdPhotoLink();
		for (ListedProductFeature feature : prdlist.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					:  featurelist+","  + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}
		return featurelist.equals("") ? productlisting : productlisting + "," + featurelist;
	}
}
