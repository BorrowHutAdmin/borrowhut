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

	
	public List getProductListingBasedOnLocation(int ucid,float latitude,float longitude,String tokenName,int numberofrecords) {
		List listofrecord = jdbcTemplate.queryForList("SELECT PL.PLS_ID,PL.PTY_ID,P.PTY_PHOTO,PRD.PRD_NAME,PRD.PRD_PHOTO_LINK,( 3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS(2.313564) ) + SIN ( RADIANS("+longitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) AS DISTANCE FROM PRODUCT_LISTING AS PL "
	+"	LEFT JOIN PARTY AS P ON PL.PTY_ID=P.PTY_ID "
	+"	LEFT JOIN PRODUCT AS PRD ON PL.PRD_ID=PRD.PRD_ID WHERE IFNULL(PL.PRODUCT_AVAILABLE,'Y') ='Y'"
	+"	HAVING (DISTANCE < (SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='"+tokenName+"' AND UIC_ID="+ucid+"))  ORDER BY PLS_ID DESC LIMIT "+numberofrecords+" ;  ");
				
		
		
		
		return listofrecord;
	}
	
	
	public List getProducts(float latitude,float longitude,float distance,String searchby,String searchByvalue){
		
		List listofrecord = jdbcTemplate.queryForList("SELECT PL.PLS_ID,PL.PTY_ID,P.PTY_PHOTO,PRD.PRD_NAME,PRD.PRD_PHOTO_LINK,( 3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS(2.313564) ) + SIN ( RADIANS("+longitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) AS DISTANCE FROM PRODUCT_LISTING AS PL "
				+"	LEFT JOIN PARTY AS P ON PL.PTY_ID=P.PTY_ID "
				+"	LEFT JOIN PRODUCT AS PRD ON PL.PRD_ID=PRD.PRD_ID WHERE IFNULL(PL.PRODUCT_AVAILABLE,'Y') ='Y' AND "+searchby+"="+ searchByvalue 
				+" 	HAVING (DISTANCE < "+distance+")   ORDER BY PLS_ID DESC ;  ");
							
					
					
					
					return listofrecord;
	}
}
