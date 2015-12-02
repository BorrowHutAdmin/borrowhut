package com.borrowhut.ws.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomBorrowRepository {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public List getBorrowStatusBybolidAndEnddate(int bloid,Date date){
	return	jdbcTemplate.queryForList("SELECT * FROM BORROW_STATUS WHERE BOL_ID="+bloid+"  AND BST_ENDDATE IS NULL");
	}
}
