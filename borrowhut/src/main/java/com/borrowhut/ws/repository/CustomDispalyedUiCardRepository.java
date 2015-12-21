package com.borrowhut.ws.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDispalyedUiCardRepository {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public float gettDistanceByUicId(int uicid) {

		float distance = 0;
		
		 Map<String, Object> scalerValue; List distList =
		  jdbcTemplate.queryForList(
		  "SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID="
		  + uicid); System.out.println("distList:::::::"+distList);
		 if(distList!=null && distList.size()>0) { for(Iterator
		  litr=distList.iterator();litr.hasNext();) { scalerValue = (Map)
		 litr.next(); distance =
		  Float.parseFloat(scalerValue.get("TOKEN_VALUE").toString());
		
		 } }
		 
	/*	String tokenvalue = jdbcTemplate.queryForObject(
				"SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID=" + uicid,
				String.class);
		distance = (float) (tokenvalue == null ? 0.0 : Float.parseFloat(tokenvalue));*/
		return distance;

	}

	public boolean checkPersonalisedcardForParty(int partyId, int uicId) {
		boolean uicardExist = false;
		List uiCardList = jdbcTemplate.queryForList("SELECT * FROM PERSONALISED_UI_CARDS WHERE PTY_ID=" + partyId
				+ " AND  UIC_ID=" + uicId + " AND CURDATE() BETWEEN PUC_VALIDFROM AND PUC_EXPIRED");
		if (uiCardList != null && uiCardList.size() > 0) {
			uicardExist = true;
		}
		return uicardExist;

	}

	public List getTokenforCalltoAction(int partyId, int uicId, String userspecific) {
		String qry = " SELECT TOKEN_NAME,TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC WHERE CARD_FACE='FRONT' AND UIC_ID="
				+ uicId + " AND TOKEN_VALUE NOT IN ('CALCULATED') ";
		if (userspecific.equalsIgnoreCase("Y")) {
			qry += " UNION SELECT TOKEN_NAME,TOKEN_VALUE FROM PERSONALISED_TOKENS AS PT WHERE PERSONALISED_UI_CARDS_UIC_ID="
					+ uicId + " AND PERSONALISED_UI_CARDS_PTY_ID=" + partyId;
		}
		List tokenList = jdbcTemplate.queryForList(qry);

		return tokenList;
	}

	public List getTokenNameAndValueByUcidandCardface(int ucid, String cardface) {
		
		return jdbcTemplate.queryForList("SELECT TOKEN_NAME, GROUP_CONCAT(TOKEN_VALUE) AS TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE UIC_ID=" + ucid
				+ " AND CARD_FACE='" + cardface + "' AND TOKEN_VALUE NOT IN ('CALCULATED') GROUP BY UIC_ID,TOKEN_NAME");
	}

	public String getDynamicColumn(int ucid) {
		// TODO Auto-generated method stub
		String dynamicColumn = "";
		Map<String, Object> scalerValue;
		List dynamicColList = jdbcTemplate.queryForList(
				"SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DYNAMIC_COLUMN' AND UIC_ID=" + ucid);
		System.out.println("distList:::::::" + dynamicColList);
		if (dynamicColList != null && dynamicColList.size() > 0) {
			for (Iterator litr = dynamicColList.iterator(); litr.hasNext();) {
				scalerValue = (Map) litr.next();
				dynamicColumn = scalerValue.get("TOKEN_VALUE").toString();

			}
		}
		return dynamicColumn;
	}

	public List checkPersonalisedTokenByTokenname(int uicid, int partyid, String tokenName) {

		List chkTokenList = jdbcTemplate
				.queryForList("SELECT * FROM PERSONALISED_TOKENS  WHERE TOKEN_NAME='" + tokenName + "'");
		return chkTokenList;

	}
}