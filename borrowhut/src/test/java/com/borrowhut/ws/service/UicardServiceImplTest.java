package com.borrowhut.ws.service;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.borrowhut.ws.domain.Category;
import com.borrowhut.ws.domain.ListedProductFeature;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.ProductListing;

import com.borrowhut.ws.exception.UiCardNotFoundException;
import com.borrowhut.ws.handler.CallToAction;
import com.borrowhut.ws.handler.Inspiration;
import com.borrowhut.ws.handler.Product;
import com.borrowhut.ws.repository.CustomDispalyedUiCardRepository;
import com.borrowhut.ws.repository.CustomProductListingRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@RunWith(MockitoJUnitRunner.class)
public class UicardServiceImplTest {
	@Mock
	private CustomProductListingRepository customProductListingRepository;
	@Mock
	private ProductListingRepository productListingRepository;
	@Mock
	private CustomDispalyedUiCardRepository customDispalyedUiCardRepository;
	
	private  Inspiration inspirartion;
	

	private  Product product;

	private  CallToAction callToAction;
	
	private UicardServiceImpl uicardServiceImpl;
	
	@Before
	public void setUp()  throws MockitoException
	{		
		inspirartion = new Inspiration();
		inspirartion.setCustomDispalyedUiCardRepository(customDispalyedUiCardRepository);
		uicardServiceImpl = new UicardServiceImpl(inspirartion, new Product(), new CallToAction());
		uicardServiceImpl.setCustomProductListingRepository(customProductListingRepository);
		uicardServiceImpl.setProductListingRepository(productListingRepository);
	}
	
	@Test
	public void testShouldGetUicard() throws UiCardNotFoundException{
		Map uicard = new HashMap();
		uicard.put("ID","1");
		uicard.put("NAME","INSPIRATIONAL");
		uicard.put("USER_SPECIFIC","N");
		uicard.put("HANDLER_CLASS","com.borrowhut.controller.inspiration");
		List uiCardList = new ArrayList();
		uiCardList.add(uicard);
		
		
		Map fronttoken = new HashMap();
		fronttoken.put("TOKEN_NAME", "CATEGORY");
		fronttoken.put("TOKEN_VALUE", "GARDENING");
		List listfronttokens = new ArrayList();
		listfronttokens.add(fronttoken);
		
		Map backtokensrecords = new HashMap();
		backtokensrecords.put("PLS_ID", 1);
		backtokensrecords.put("PTY_ID", 1);
		backtokensrecords.put("PRD_ID", 1);
		backtokensrecords.put("PRD_NAME", "Hammer");
		backtokensrecords.put("PTY_NAME", "SACHIN");
		backtokensrecords.put("PRD_DESCRIPTION", "This amazing hammer");
		backtokensrecords.put("PRD_PHOTO_LINK", "link of product photo");
		List listbacktokensrecords = new ArrayList();
		listbacktokensrecords.add(backtokensrecords);
		
		
		
			ListedProductFeature listedPrdfeature = mock(ListedProductFeature.class);
			
			 List<ListedProductFeature> li = new ArrayList<ListedProductFeature>();
			
			 	ProductListing prdlist = mock(ProductListing.class);
			 		Category category = mock(Category.class);
			 		category.setCatName("GARDENING");
			 		com.borrowhut.ws.domain.Product prd	= mock(com.borrowhut.ws.domain.Product.class);
					prd.setPrdId(1);
					prd.setCategory(category);
					prd.setPrdName("Hammer");
					prd.setPrdDescription("This amazing hammer");
					prd.setPrdPhotoLink("link of product photo");
					Party	party=mock(Party.class);
					party.setPtyId(1);
					party.setPtyName("SACHIN");
					party.setPtyPhoto("link");
					prdlist.setPlsId(1);
					prdlist.setParty(party);
					prdlist.setProduct(prd);
					prdlist.setPtyId(1);
					prdlist.setListedProductFeatures(li);
	
		when(customProductListingRepository.getUiCards()).thenReturn(uiCardList);
		when(customDispalyedUiCardRepository.getTokenNameAndValueByUcidandCardface(1,"FRONT")).thenReturn(listfronttokens);
		when(customDispalyedUiCardRepository.getDynamicColumn(1)).thenReturn("PRODUCT_LISTING.PLS_ID,PRODUCT_LISTING.PTY_ID,PARTY.PTY_NAME,PRODUCT_LISTING.PRD_ID,PRODUCT.PRD_NAME,PRODUCT.PRD_DESCRIPTION,PRODUCT.PRD_PHOTO_LINK");
		when(customDispalyedUiCardRepository.gettDistanceByUicId(1)).thenReturn(10f);
		when(productListingRepository.findByplsId(1)).thenReturn(prdlist);	
		when(customProductListingRepository.getProductListForBackToken(1,  48.858093f, 2.294694f, 10f, "PRODUCT_LISTING.PLS_ID,PRODUCT_LISTING.PTY_ID,PARTY.PTY_NAME,PRODUCT_LISTING.PRD_ID,PRODUCT.PRD_NAME,PRODUCT.PRD_DESCRIPTION,PRODUCT.PRD_PHOTO_LINK")).thenReturn(listbacktokensrecords);
		
		JSONArray jsonArray=uicardServiceImpl.getUicard(1, 48.858093f, 2.294694f);
		System.out.println("out put"+jsonArray.toString());
		
	}
	
	
	
}
