package com.borrowhut.ws.repository;

import java.util.ArrayList;
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

	public int gettDistanceByUicId(int uicid) {
		// TODO Auto-generated method stub		
		int distance=0;
		Map<String, Object> scalerValue;
		List distList = jdbcTemplate
				.queryForList("SELECT TOKEN_VALUE FROM BHD.DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID="+ uicid);
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
	
	public boolean checkPersonalisedcardForParty(int partyId,int uicId)
	{
		boolean uicardExist=false;		
		List uiCardList = jdbcTemplate
				.queryForList("SELECT * FROM PERSONALISED_UI_CARDS WHERE PTY_ID="+partyId+" AND UIC_ID="+ uicId );
		if(uiCardList!=null && uiCardList.size()>0)
		{
		   uicardExist=true;	
		}		
		return uicardExist;
		
	}
	
	
	public List getTokenforCalltoAction(int partyId,int uicId,String userspecific)
	{
		String qry=" SELECT TOKEN_NAME,TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC WHERE UIC_ID="+uicId;
		if(userspecific.equalsIgnoreCase("Y"))
		{
			qry +=" UNION SELECT TOKEN_NAME,TOKEN_VALUE FROM PERSONALISED_TOKENS AS PT WHERE PERSONALISED_UI_CARDS_UIC_ID="+uicId+ " AND PERSONALISED_UI_CARDS_PTY_ID="+partyId; 
		}		
		List tokenList = jdbcTemplate
				.queryForList(qry);
				
		return tokenList;
	}
	

}
