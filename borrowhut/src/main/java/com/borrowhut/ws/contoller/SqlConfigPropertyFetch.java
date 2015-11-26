package com.borrowhut.ws.contoller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "query", locations="classpath:sqlconfig.properties")
public class SqlConfigPropertyFetch {

	private String backtokenList;

	public String getBacktokenList() {
		return backtokenList;
	}

	public void setBacktokenList(String backtokenList) {
		this.backtokenList = backtokenList;
	}

	
	
}