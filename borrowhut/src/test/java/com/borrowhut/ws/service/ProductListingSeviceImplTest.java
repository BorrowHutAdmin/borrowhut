package com.borrowhut.ws.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;


import com.borrowhut.ws.domain.Category;
import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.Product;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.exception.ListedProductNotFoundException;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.service.ProductListingService;
import com.borrowhut.ws.service.ProductListingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductListingSeviceImplTest {
	
	@Mock 
	private ProductListingRepository productListingRepository;
	
	private ProductListingServiceImpl productListingServiceImpl;
	
	
	
	
	@Before
	public void setUp()  throws MockitoException
	{	
		
		productListingServiceImpl = new ProductListingServiceImpl(productListingRepository);
		
	}
	
	@Test
	public void testshouldgetProductListingByPlsid() throws ListedProductNotFoundException, ParseException
	{
		
		ListedProductFeature listedPrdfeature = mock(ListedProductFeature.class);
	
		 List<ListedProductFeature> li = new ArrayList<ListedProductFeature>();
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = sdf.parse("02/02/2016");
		 
		 
		 System.out.println("date is  "+date);
		
	ProductListing prdlist = mock(ProductListing.class);
	//ProductListing prdlist = new ProductListing();
	
			Category category = mock(Category.class);
			category.setCatName("Cars");
			
	Product prd	=mock(Product.class);
	prd.setPrdId(1);
	prd.setCategory(category);
	prd.setPrdName("Hammer");
	prd.setPrdDescription("This amazing hammer");
	prd.setPrdPhotoLink("link");
	
	Party	party=mock(Party.class);
	party.setPtyId(1);
	party.setPtyName("ismail");
	party.setPtyPhoto("link");
	party.setPtyMobile("837465902");
	
	prdlist.setPlsId(2);
	prdlist.setParty(party);
	prdlist.setProduct(prd);
	prdlist.setPtyId(1);
	prdlist.setPlsFirstCirclePrice("20");
	prdlist.setPlsSecondCirclePrice("40");
	prdlist.setListedProductFeatures(li);
	
	when(prdlist.getParty()).thenReturn(party);	
	when(prdlist.getListedProductFeatures()).thenReturn(li);
	when(prdlist.getProduct()).thenReturn(prd);	
	when(prd.getCategory()).thenReturn(category);
	
		when(productListingRepository.findByplsId(2)).thenReturn(prdlist);	
		System.out.println("the value is   "+party.getPtyId());
		JSONObject pl=productListingServiceImpl.getProductListingByPlsid(2);
		System.out.println(pl.toJSONString());
		assertNotNull(pl);
		  verify(productListingRepository, times(1)).findByplsId(2);
	
	}
	
	 
}
