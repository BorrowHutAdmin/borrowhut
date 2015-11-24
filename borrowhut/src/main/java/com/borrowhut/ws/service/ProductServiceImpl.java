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
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@Service
@Validated
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private CustomProductListingRepository customProductListingRepository;
	
	@Autowired
	private ProductListingRepository productListingRepository;
	@Transactional
	@Override
	public JSONArray getSearchProduct(String searchCriteria,String searchValue, float latitude,
			float longitude, float distance) {
		
		JSONArray jsonArray = new JSONArray();
		List products;
	switch(searchCriteria) {
		case "PRD.PRD_NAME":
			 products =	customProductListingRepository.getProducts(latitude, longitude, distance, "PRD.PRD_NAME", "'"+"%"+searchValue+"%"+"'");
		jsonArray =	buildResult(products);
			break;
		case "PL.PRD_ID":
			
		 products =	customProductListingRepository.getProducts(latitude, longitude, distance, "PL.PRD_ID", searchValue);
		 jsonArray =	buildResult(products);
			break;
		case "PRD.CAT_NAME":
			 products =	customProductListingRepository.getProducts(latitude, longitude, distance, "PRD.CAT_NAME", "'"+searchValue+"'");
			 jsonArray =	buildResult(products);
			
			break;
		default:
			break;
	}
		
				return jsonArray;
	}

	@Transactional
	private JSONArray buildResult(List products){
		JSONArray productising = new JSONArray();
		Map<String, Object> recrod;
		JSONObject object  = new JSONObject();
		int plsid =0;
		String productlistingstr="";
		String featurelist ="";
		String productdesc ="";
		String prdphotolink="";
		System.out.println("length of records"+products.size());
		
								if(products!=null){
										for(Iterator itr = products.iterator(); itr.hasNext();){
											
											object = new JSONObject();
											recrod = (Map) itr.next();
											
											
											
											plsid =	Integer.parseInt(recrod.get("PLS_ID").toString())  ;
											
											object.put("partyid",Integer.parseInt(recrod.get("PTY_ID").toString()) );
											
											productdesc= recrod.get("PRD_DESCRIPTION")!=null?recrod.get("PRD_DESCRIPTION").toString():"null";
													
													 prdphotolink = recrod.get("PRD_PHOTO_LINK")!=null?recrod.get("PRD_PHOTO_LINK").toString():"null";
											
											productlistingstr =plsid+","+recrod.get("PTY_ID").toString()+","+recrod.get("CAT_NAME").toString()+","+recrod.get("PRD_NAME").toString()+
													","+productdesc+","+prdphotolink;
											 featurelist =		getProductlsiting(plsid,productListingRepository);
											
											productlistingstr=	featurelist.equals("") ? productlistingstr : productlistingstr + "," + featurelist;
											
									
											object.put("productlisting", productlistingstr);
											productising.add(object);
										}
								
								
								
							}
			return productising;
		
	}
	@Transactional
	private String getProductlsiting(int plsid,ProductListingRepository productListingRepository) {
		
		
		System.out.println("getting features");
		ProductListing productList =	productListingRepository.getOne(plsid);
		String featurelist = "";
		for (ListedProductFeature feature : productList.getListedProductFeatures()) {
			featurelist = featurelist.equals("")
					? (featurelist + feature.getId().getFtrName() + "," + feature.getLpfFtrValue())
					:  featurelist+","  + feature.getId().getFtrName() + "," + feature.getLpfFtrValue();

		}
		
		System.out.println("returning features"+featurelist);
		return featurelist;
	}
}