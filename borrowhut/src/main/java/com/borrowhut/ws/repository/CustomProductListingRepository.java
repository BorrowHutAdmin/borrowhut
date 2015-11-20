package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductListingRepository {

	
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public List getStream(){
		
		System.out.println("in stream");
	List listofuicadrs =	jdbcTemplate.queryForList("SELECT X.UCID,X.NAME,X.TOKEN_NAME FROM ("+
"SELECT UC.ID AS UCID,UC.NAME,DIC.ID,DIC.UIC_ID,DIC.TOKEN_NAME,DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC "+
 "LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' ) AS X WHERE X.TOKEN_VALUE='CALCULATED'");
	
	return listofuicadrs;
	}
	
	public List getTotalProductListedCountByCategory(int ucid){
		System.out.println("getting counts");
		
		List listofcatwithcount = 		jdbcTemplate.queryForList(" SELECT COUNT(*) AS CAT_COUNT,CAT_NAME FROM PRODUCT_LISTING WHERE CAT_NAME IN ("+ 
"SELECT X.TOKEN_VALUE FROM ("+
"SELECT UC.ID AS UCID,UC.NAME,DIC.ID,DIC.UIC_ID,DIC.TOKEN_NAME,DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC "+
 "LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' WHERE DIC.UIC_ID="+ucid+" ) AS X WHERE X.TOKEN_NAME='CATEGORY')  GROUP BY CAT_NAME;");
		
		return listofcatwithcount;
	}
}
