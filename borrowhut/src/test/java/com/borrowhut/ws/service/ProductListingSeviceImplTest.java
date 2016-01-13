package com.borrowhut.ws.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
	
	private ProductListingService productListingService;
	
	
	
	
	@Before
	public void setUp()  throws MockitoException
	{	
		
		productListingService = new ProductListingServiceImpl(productListingRepository);
		
	}
	
	@Test
	public void testshouldgetProductListingByPlsid() throws ListedProductNotFoundException
	{
		
		ListedProductFeature listedPrdfeature = mock(ListedProductFeature.class);
	
		 List<ListedProductFeature> li = new ArrayList<ListedProductFeature>();
		
	ProductListing prdlist = mock(ProductListing.class);
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
	prdlist.setPlsId(1);
	prdlist.setParty(party);
	prdlist.setProduct(prd);
	prdlist.setPtyId(1);
	prdlist.setListedProductFeatures(li);
	
	
	
	when(prdlist.getListedProductFeatures()).thenReturn(li);
	when(prdlist.getParty()).thenReturn(party);	
	when(prdlist.getProduct()).thenReturn(prd);	
	when(prd.getCategory()).thenReturn(category);
		when(productListingRepository.findByplsId(1)).thenReturn(prdlist);		
		JSONObject pl=productListingService.getProductListingByPlsid(1);
		System.out.println(pl.toJSONString());
		assertNotNull(pl);
		  verify(productListingRepository, times(1)).findByplsId(1);
	
		
	}
	
	 
}
