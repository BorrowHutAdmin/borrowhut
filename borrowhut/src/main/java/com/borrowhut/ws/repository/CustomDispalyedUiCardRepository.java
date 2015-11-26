package com.borrowhut.ws.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDispalyedUiCardRepository {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public float gettDistanceByUicId(int uicid) {
		// TODO Auto-generated method stub		
		float distance=0;
		Map<String, Object> scalerValue;
		List distList = jdbcTemplate.queryForList("SELECT TOKEN_VALUE FROM BHD.DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID="+ uicid);
		System.out.println("distList:::::::"+distList);
		if(distList!=null && distList.size()>0)
		{
			for(Iterator litr=distList.iterator();litr.hasNext();)
			{				
				scalerValue = (Map) litr.next();
				distance = Integer.parseInt(scalerValue.get("TOKEN_VALUE").toString());
				
			}
		}
		
		return distance;
		
	}
	

}