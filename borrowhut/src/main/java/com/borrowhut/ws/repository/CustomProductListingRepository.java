package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomProductListingRepository {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/*public List getStream() {

	
		List listofuicadrs = jdbcTemplate.queryForList("SELECT X.UCID,X.NAME,X.TOKEN_NAME FROM ("
				+ "SELECT UC.ID AS UCID,UC.NAME,DIC.ID,DIC.UIC_ID,DIC.TOKEN_NAME,DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC "
				+ "LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' ) AS X WHERE X.TOKEN_VALUE='CALCULATED'");

		return listofuicadrs;
	}*/

	public List getProductListeByCategoryAndCountBasedonTokenName(int ucid, String tokenName) {
	
		List listofcatwithcount = jdbcTemplate
				.queryForList(" SELECT COUNT(*) AS CAT_COUNT,CAT_NAME FROM PRODUCT_LISTING WHERE CAT_NAME IN ("
						+ "SELECT X.TOKEN_VALUE FROM ("
						+ "SELECT UC.ID AS UCID,UC.NAME,DIC.ID,DIC.UIC_ID,DIC.TOKEN_NAME,DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC "
						+ "LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' WHERE DIC.UIC_ID="
						+ ucid + " ) AS X WHERE X.TOKEN_NAME='" + tokenName + "')  GROUP BY CAT_NAME;");

		return listofcatwithcount;
	}

	public List getUiCardswithUserSpecific(String userspecific) {

		return jdbcTemplate.queryForList("SELECT * FROM BHD.UI_CARDS where USER_SPECIFIC ='" + userspecific + "'");
	}

	public Boolean checkIsExistInDispUiCard(int ucid, String tokenName, String tokenValue, String cardFace) {
		List listofrecord = jdbcTemplate
				.queryForList("SELECT * FROM BHD.DISPLAYED_UI_CARDS where UIC_ID=" + ucid + " AND TOKEN_NAME ='"
						+ tokenName + "' AND CARD_FACE='" + cardFace + "' AND TOKEN_VALUE='" + tokenValue + "';");

		return (listofrecord != null && listofrecord.size() > 0) ? true : false;
	}

}
