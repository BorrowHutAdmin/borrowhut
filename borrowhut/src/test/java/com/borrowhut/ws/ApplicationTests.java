package com.borrowhut.ws;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =Application.class)
@IntegrationTest
@WebAppConfiguration
@ActiveProfiles("test")
public class ApplicationTests {
	@Autowired
	private EmbeddedWebApplicationContext server;
	
	private RestTemplate restTemplate =new TestRestTemplate();
	
	@Test
	public void contextLoads() {
		
		System.out.println("test cases are runnnig");
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:"
				+ server.getEmbeddedServletContainer().getPort() +"/"+server.getApplicationName()+ "/home", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		 entity =	restTemplate.getForEntity("http://localhost:"
				+ server.getEmbeddedServletContainer().getPort() +"/"+server.getApplicationName()+"/getListedProduct/plsid/1", String.class);
		assertEquals(HttpStatus.OK,entity.getStatusCode());
		
		System.out.println("body"+entity.getBody());
		System.out.println("header"+entity.getHeaders().toString());
		System.out.println("classs"+entity.getClass().toString());
		System.out.println("status"+entity.getStatusCode().toString());
		
		
	}
}
