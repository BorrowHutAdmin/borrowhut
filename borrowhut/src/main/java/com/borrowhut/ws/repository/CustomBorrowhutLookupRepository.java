package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.borrowhut.ws.contoller.SqlConfigPropertyFetch;

@Repository
public class CustomBorrowhutLookupRepository {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected SqlConfigPropertyFetch sqlConfigPropertyfetch;


	public List getLookupdatawithCond(String tablename, String filtercond) {
		// TODO Auto-generated method stub
		String condition="";
		if(!filtercond.equalsIgnoreCase("")|| filtercond.length()>1)
		{
			condition=" where "+filtercond;
		}
		
		String sql="Select "+sqlConfigPropertyfetch.getColumns(tablename)  +" from "+tablename+condition;
		System.out.println(sql);
		return jdbcTemplate.queryForList(sql);
	}

}