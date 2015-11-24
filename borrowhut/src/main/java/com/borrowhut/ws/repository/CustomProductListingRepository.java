package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.borrowhut.ws.contoller.SqlConfigPropertyFetch;

@Repository
public class CustomProductListingRepository {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected SqlConfigPropertyFetch sqlConfigPropertyfetch;
	
	public List getProductListeByCategoryAndCountBasedonTokenName(int ucid, String tokenName,float latitude,float longitude) {
		
		List listofcatwithcount = jdbcTemplate
				.queryForList(" SELECT COUNT(*) AS CAT_COUNT,CAT_NAME FROM PRODUCT_LISTING AS PL LEFT JOIN  PARTY AS P ON PL.PTY_ID = P.PTY_ID "
						+ "WHERE PL.CAT_NAME IN ("
						+ "SELECT X.TOKEN_VALUE FROM ("
						+ "SELECT UC.ID AS UCID,UC.NAME,DIC.ID,DIC.UIC_ID,DIC.TOKEN_NAME,DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC "
						+ "LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' WHERE DIC.UIC_ID="
						+ ucid + " ) AS X WHERE X.TOKEN_NAME='" + tokenName + "')  AND IFNULL(PL.PRODUCT_AVAILABLE,'N') ='Y'"
						+ " AND  (3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS("+longitude+") ) + SIN ( RADIANS("+latitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) <= (SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID="+ucid+")"
						+ " GROUP BY CAT_NAME;");

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
	/*SELECT PL.PLS_ID,PL.PTY_ID,P.PTY_PHOTO,PRD.PRD_NAME,PRD.PRD_PHOTO_LINK,( 3959 * ACOS ( COS ( RADIANS(48.854437) ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS(2.313564) ) + SIN ( RADIANS(48.854437) ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) AS DISTANCE FROM PRODUCT_LISTING AS PL
	LEFT JOIN PARTY AS P ON PL.PTY_ID=P.PTY_ID 
	LEFT JOIN PRODUCT AS PRD ON PL.PRD_ID=PRD.PRD_ID 
	HAVING (DISTANCE < (SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID=1))  ORDER BY PLS_ID DESC LIMIT 3*/
	
	public List getProductListingBasedOnLocation(int ucid,float latitude,float longitude,String tokenName,int numberofrecords) {
																														/*3959 * acos( cos( radians(lat1) ) 
																															      * cos( radians(lat2) ) 
																															      * cos( radians(lon2) - radians(lon1)) + sin(radians(lat1))
																															      * sin( radians(lat2) )*/
		
		List listofrecord = jdbcTemplate.queryForList("SELECT PL.PLS_ID,PL.PTY_ID,P.PTY_PHOTO,PRD.PRD_NAME,PRD.PRD_PHOTO_LINK,( 3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS("+longitude+") ) + SIN ( RADIANS("+latitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) AS DISTANCE FROM PRODUCT_LISTING AS PL "
	+"	LEFT JOIN PARTY AS P ON PL.PTY_ID=P.PTY_ID "
	+"	LEFT JOIN PRODUCT AS PRD ON PL.PRD_ID=PRD.PRD_ID WHERE IFNULL(PL.PRODUCT_AVAILABLE,'Y') ='Y'"
	+"	HAVING (DISTANCE < (SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='"+tokenName+"' AND UIC_ID="+ucid+"))  ORDER BY PLS_ID DESC LIMIT "+numberofrecords+" ;  ");
				
		
		
		
		return listofrecord;
	}
	
	
	public List getProducts(float latitude,float longitude,float distance,String searchby,String searchByvalue){
		
		
		
	String	operator = searchby.equals("PRD.PRD_NAME")?" LIKE ":" = ";
		
		List listofrecord = jdbcTemplate.queryForList("SELECT PL.PLS_ID,PL.PTY_ID,P.PTY_PHOTO,PRD.CAT_NAME,PRD.PRD_DESCRIPTION,PRD.PRD_NAME,PRD.PRD_PHOTO_LINK,( 3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS("+longitude+") ) + SIN ( RADIANS("+latitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) AS DISTANCE FROM PRODUCT_LISTING AS PL "
				+"	LEFT JOIN PARTY AS P ON PL.PTY_ID=P.PTY_ID "
				+"	LEFT JOIN PRODUCT AS PRD ON PL.PRD_ID=PRD.PRD_ID WHERE IFNULL(PL.PRODUCT_AVAILABLE,'Y') ='Y' AND "+searchby+operator+ searchByvalue 
				+" 	HAVING (DISTANCE < "+distance+")   ORDER BY PLS_ID DESC ;  ");
							
					
					
					
					return listofrecord;
	}
	/// vishwanath
	public List getProductListForBackToken(int ucid,float latitude, float longitude){		
		System.out.println("qury fetch-------------"+sqlConfigPropertyfetch.getBacktokenList());
		String sql=sqlConfigPropertyfetch.getBacktokenList() + " AND PL.CAT_NAME IN"
				+ "(SELECT DIC.TOKEN_VALUE FROM DISPLAYED_UI_CARDS AS DIC LEFT JOIN UI_CARDS AS UC ON UC.ID=DIC.UIC_ID AND UC.USER_SPECIFIC='N' WHERE DIC.UIC_ID="+ucid 
				+" AND DIC.TOKEN_NAME='CATEGORY' )"
				+ " AND  (3959 * ACOS ( COS ( RADIANS("+latitude+") ) * COS( RADIANS( P.PTY_LATITUDE ) ) * COS( RADIANS( P.PTY_LONGITUDE ) - RADIANS("+longitude+") ) + SIN ( RADIANS("+latitude+") ) * SIN( RADIANS( P.PTY_LATITUDE ) ) ) ) <= (SELECT TOKEN_VALUE FROM DISPLAYED_UI_CARDS WHERE TOKEN_NAME='DISTANCE' AND UIC_ID="+ucid+")"
				+ " ORDER BY PL.PLS_ID ";
		List listofBackToken = jdbcTemplate
				.queryForList(sql);
		return listofBackToken;
	}
}
