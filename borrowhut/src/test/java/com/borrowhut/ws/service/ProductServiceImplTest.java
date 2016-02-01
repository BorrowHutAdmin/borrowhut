package com.borrowhut.ws.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
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
import com.borrowhut.ws.exception.ProductNotFoundException;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	
	@Mock
	private CustomProductListingRepository customProductListingRepository;
	
	@Mock
	private ProductListingRepository productListingRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductServiceImpl productServiceImpl;
	
	 
	@Before
	public void setUp()  throws MockitoException
	{		
		productServiceImpl = new ProductServiceImpl();
		productServiceImpl.setCustomProductListingRepository(customProductListingRepository);
		productServiceImpl.setProductListingRepository(productListingRepository);
		productServiceImpl.setProductRepository(productRepository);
				
	}
	
	
	
	@Test
	public void testShouldgetSearchProduct() throws ProductNotFoundException
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
	party.setPtyName("visu");
	party.setPtyPhoto("link");
	prdlist.setPlsId(1);
	prdlist.setParty(party);
	prdlist.setProduct(prd);
	prdlist.setPtyId(1);
	prdlist.setListedProductFeatures(li);
		
		
		Map prodmap = new HashMap();
		prodmap.put("PLS_ID", 1);
		prodmap.put("PTY_ID", 1);
		prodmap.put("PRD_DESCRIPTION", "nice");
		prodmap.put("PRD_PHOTO_LINK", null);
		prodmap.put("CAT_NAME", "GARDENING");
		prodmap.put("PRD_NAME", "HAMMER");	
		prodmap.put("PRD_ID", 1);
		
		List products = new ArrayList();
		products.add(prodmap);
		
		when(productListingRepository.findByplsId(1)).thenReturn(prdlist);		
		when(customProductListingRepository.getProducts("HAMMER", 1, "GARDENING", 48.858093f, 2.294694f, 10)).thenReturn(products);
		JSONArray ps = productServiceImpl.getSearchProduct("HAMMER", 1, "GARDENING", 48.858093f, 2.294694f, 10);
		
		assertNotNull(ps);
		verify(customProductListingRepository,times(1)).getProducts("HAMMER", 1, "GARDENING", 48.858093f, 2.294694f, 10);
	}
	
	
	@Test
	public void testShouldgetProductRelatedData() throws ProductNotFoundException
	{
		Category category = mock(Category.class);
		category.setCatName("Cars");
		
		Product prd	=mock(Product.class);
		prd.setPrdId(1);
		prd.setCategory(category);
		prd.setPrdName("Hammer");
		prd.setPrdDescription("This amazing hammer");
		prd.setPrdPhotoLink("link");
		
		when(productRepository.findByprdId(1)).thenReturn(prd);
	}

}