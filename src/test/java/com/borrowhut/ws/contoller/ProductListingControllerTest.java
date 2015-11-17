package com.borrowhut.ws.contoller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.borrowhut.ws.service.ProductListingService;

@RunWith(MockitoJUnitRunner.class)
public class ProductListingControllerTest {
	@Mock
	private ProductListingService productListingService;

	private ProductListingController productListingController;

	@Before
	public void setUp() throws Exception {
		productListingController = new ProductListingController(productListingService);
	}

	@Test
	public void shouldgetAllListedProductsByplsid() throws Exception {
		stubServiceToReturnProductBylistingid(111);
		JSONObject listedProduct = productListingController.getAllListedProductsByplsid(111);
		assertNotNull(listedProduct);

		// verify plsid was passed to productListingService
		verify(productListingService, times(1)).getProductListingByPlsid(111);
	}

	private void stubServiceToReturnProductBylistingid(int plsid) {

		JSONObject j = new JSONObject();
		j.put("partid", "111");
		j.put("productlisting", ",PLS.PLS_ID,PRD.CATEGORY_NAME,PRD.PRODUCT_NAME,PRD.PRODUCT_DESC,PRD.PRODUCT_PHOTO,LPF.FEATURE1,LPF.FEATURE1_VALUE,LPF.FEATURE2,LPF.FEATURE2_VALUE,LPF.FEATURE3,LPF.FEATURE3_VALUE");
		when(productListingService.getProductListingByPlsid(plsid)).thenReturn(j);
	}
	@Test
	public void shouldgetAllListedProductsBypartyid(){
		stubServiceToReturnProductListingBypartyid(123);
	JSONArray	jsonarray = productListingController.getAllListedProductsBypartyid(123);
	assertNotNull(jsonarray);
	verify(productListingService,times(1)).getProductListingByPartyid(123);	
		
	}
		private void stubServiceToReturnProductListingBypartyid(int partyid){
		JSONArray jsonArray = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("partid", partyid);
		j.put("productlisting",  ",PLS.PLS_ID,PRD.CATEGORY_NAME,PRD.PRODUCT_NAME,PRD.PRODUCT_DESC,PRD.PRODUCT_PHOTO,LPF.FEATURE1,LPF.FEATURE1_VALUE,LPF.FEATURE2,LPF.FEATURE2_VALUE,LPF.FEATURE3,LPF.FEATURE3_VALUE");
		jsonArray.add(j);
		j =new JSONObject();
		j.put("partid", partyid);
		j.put("productlisting",  ",PLS.PLS_ID,PRD.CATEGORY_NAME,PRD.PRODUCT_NAME,PRD.PRODUCT_DESC,PRD.PRODUCT_PHOTO,LPF.FEATURE1,LPF.FEATURE1_VALUE,LPF.FEATURE2,LPF.FEATURE2_VALUE,LPF.FEATURE3,LPF.FEATURE3_VALUE");
		jsonArray.add(j);
		
			when(productListingService.getProductListingByPartyid(partyid)).thenReturn(jsonArray);
	}
	
	

}
