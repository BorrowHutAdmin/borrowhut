package com.borrowhut.ws.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.repository.ProductRepository;

@Service
@Validated
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private CustomProductListingRepository customProductListingRepository;

	@Autowired
	private ProductListingRepository productListingRepository;
	@Autowired
	private ProductRepository productRepository;

	@Transactional
	@Override
	public JSONArray getSearchProduct(String productName, int prdId, String catName, float latitude, float longitude,
			float distance) throws ProductNotFoundException {

		JSONArray jsonArray = new JSONArray();

		List products = customProductListingRepository.getProducts(productName, prdId, catName, latitude, longitude,
				distance);

		if (products != null && products.size() > 0)

			jsonArray = buildResult(products);
		else
			throw new ProductNotFoundException("Product(s) with name not found ");

		return jsonArray;
	}

	@Transactional
	private JSONArray buildResult(List products) {
		JSONArray productising = new JSONArray();
		Map<String, Object> recrod;
		JSONObject object = new JSONObject();
		int plsid = 0;
		String productlistingstr = "";
		String featurelist = "";
		String productdesc = "";
		String prdphotolink = "";
		LOGGER.debug("length of records" + products.size());

		if (products != null) {
			for (Iterator itr = products.iterator(); itr.hasNext();) {

				object = new JSONObject();
				recrod = (Map) itr.next();

				plsid = Integer.parseInt(recrod.get("PLS_ID").toString());

				object.put("partyid", Integer.parseInt(recrod.get("PTY_ID").toString()));

				productdesc = recrod.get("PRD_DESCRIPTION") != null ? recrod.get("PRD_DESCRIPTION").toString() : "null";

				prdphotolink = recrod.get("PRD_PHOTO_LINK") != null ? recrod.get("PRD_PHOTO_LINK").toString() : "null";

				productlistingstr = plsid + "," + recrod.get("PTY_ID").toString() + ","
						+ recrod.get("CAT_NAME").toString() + "," + recrod.get("PRD_NAME").toString() + ","
						+ productdesc + "," + prdphotolink;
				featurelist = getProductlsiting(plsid, productListingRepository);

				productlistingstr = featurelist.equals("") ? productlistingstr : productlistingstr + "," + featurelist;

				object.put("productlisting", productlistingstr);
				productising.add(object);
			}

		}
		return productising;

	}

	@Transactional
	private String getProductlsiting(int plsid, ProductListingRepository productListingRepository) {

		LOGGER.debug("getting features");
		ProductListing productList =productListingRepository.findByplsId(plsid);
		String featurelist = "";
		for (ListedProductFeature feature : productList.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					: featurelist + "," + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}

		LOGGER.debug("returning features" + featurelist);
		return featurelist;
	}

	@Transactional
	@Override
	public JSONObject getProductRelatedData(int productid) throws ProductNotFoundException {

		

		Product prd = productRepository.findByprdId(productid);

		if (prd == null ) {
			throw new ProductNotFoundException("No Product Found");
		}
		JSONObject 	obj = new JSONObject();
				obj.put("PRD_ID", prd.getPrdId());

				obj.put("PRD_NAME", prd.getPrdName());

				obj.put("Feature_list", customProductListingRepository.getProductFeatures(productid));

		
		
		return obj;

	}

}
