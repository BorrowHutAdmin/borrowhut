package com.borrowhut.ws.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Validator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.domain.ProductListingPK;
import com.borrowhut.ws.repository.ProductListingRepository;
@Service
@Validated
public class ProductListingServiceImpl implements ProductListingService{
private final ProductListingRepository productListingRepository;

@Inject
public  ProductListingServiceImpl(final ProductListingRepository productListingRepository) {
	this.productListingRepository = productListingRepository;
}

public long getCount(){
	
return	productListingRepository.count();
	
}

@Override
	public ProductListing getProductBylistingid(int prdid,int partyid) {
	
	ProductListing p =	productListingRepository.findOne(prdid);
	System.out.println("Party id"+p.getParty().getPtyId());
	System.out.println("Product listing id"+p.getPlsId());
	System.out.println("Category name"+p.getProduct().getCategory().getCatName());
	System.out.println("Product Name"+p.getProduct().getPrdName());
	System.out.println("Product Desc"+p.getProduct().getPrdDescription());
	System.out.println("Product PhotoLink"+p.getProduct().getPrdPhotoLink());
	for(ListedProductFeature plf:p.getListedProductFeatures()){
		
		System.out.println("feature name"+plf.getId().getFtrName());
		System.out.println("feature value"+plf.getLpfFtrValue());
	}
	
	/*p.getParty().getPtyId();
	p.getId().getPlsId();
	
	p.getProduct().getCategory().getCatName();
	p.getProduct().getPrdName();
	p.getProduct().getPrdDescription();
	p.getProduct().getPrdPhotoLink();
	p.getProduct().getFeatureValues();
	*/
	
	List<ProductListing> ppp =productListingRepository.findByptyId(partyid);
	
	System.out.println("fecting custom query");
	System.out.println("fecting By size"+ppp.size());
/*List<ProductListing> pdlist =	productListingRepository.find(partyid);
	System.out.println("pdlist size"+pdlist.size());*/
		return productListingRepository.findOne(prdid);
	}

@Override
public JSONArray getProductListingByPartyid(int partyid) {
	
	
	List<ProductListing> prdlisting = productListingRepository.findByptyId(partyid);
	JSONArray jsonrecords = new JSONArray();
	JSONObject object ;
	if(prdlisting!=null){
		
		for(ProductListing prdlist:prdlisting){
			object = new JSONObject();
			object.put("productlisting",getProductlsiting(prdlist));
			object.put("partyid", prdlist.getParty().getPtyId());
			
			
			jsonrecords.add(object);
			
		}
		
	}
	
	// TODO Auto-generated method stub
	return jsonrecords;
}

private String getProductlsiting(ProductListing prdlist){
	
	String productlisting = "";
	Product pdt = prdlist.getProduct();
	String featurelist="";
	productlisting = prdlist.getPlsId()+","+pdt.getCategory().getCatName()+","+pdt.getPrdName()+","+pdt.getPrdDescription()+","+pdt.getPrdPhotoLink();
	for(ListedProductFeature feature:prdlist.getListedProductFeatures()){
		featurelist =featurelist.equals("")?(featurelist+feature.getId().getFtrName()+","+feature.getLpfFtrValue()):","+featurelist+feature.getId().getFtrName()+","+feature.getLpfFtrValue();
		
	}
	return featurelist.equals("")?productlisting:productlisting+","+featurelist;
}
}
