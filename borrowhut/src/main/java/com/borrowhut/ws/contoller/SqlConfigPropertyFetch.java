package com.borrowhut.ws.contoller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "query", locations="classpath:sqlconfig.properties")
public class SqlConfigPropertyFetch {
	
	
	private String backtokenList;
	private String product;	
	private String authmech;
	private String borrowlifecycleevent;
	private String borrowlog;
	private String borrowstatus;
	private String category;
	private String displayeduicards;
	private String feature;
	private String featurevalue;
	private String mytrustcircle;
	private String party;
	private String partyauthmech;
	private String personalisedtokens;
	private String personaliseduicards;
	private String productfeature;
	private String productlisting;
	private String uicard;
	private String listedproductfeature;
	
	public String getListedproductfeature() {
		return listedproductfeature;
	}

	public void setListedproductfeature(String listedproductfeature) {
		this.listedproductfeature = listedproductfeature;
	}

	public String getAuthmech() {
		return authmech;
	}

	public void setAuthmech(String authmech) {
		this.authmech = authmech;
	}

	public String getBorrowlifecycleevent() {
		return borrowlifecycleevent;
	}

	public void setBorrowlifecycleevent(String borrowlifecycleevent) {
		this.borrowlifecycleevent = borrowlifecycleevent;
	}

	public String getBorrowlog() {
		return borrowlog;
	}

	public void setBorrowlog(String borrowlog) {
		this.borrowlog = borrowlog;
	}

	public String getBorrowstatus() {
		return borrowstatus;
	}

	public void setBorrowstatus(String borrowstatus) {
		this.borrowstatus = borrowstatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDisplayeduicards() {
		return displayeduicards;
	}

	public void setDisplayeduicards(String displayeduicards) {
		this.displayeduicards = displayeduicards;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getMytrustcircle() {
		return mytrustcircle;
	}

	public void setMytrustcircle(String mytrustcircle) {
		this.mytrustcircle = mytrustcircle;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPartyauthmech() {
		return partyauthmech;
	}

	public void setPartyauthmech(String partyauthmech) {
		this.partyauthmech = partyauthmech;
	}

	public String getPersonalisedtokens() {
		return personalisedtokens;
	}

	public void setPersonalisedtokens(String personalisedtokens) {
		this.personalisedtokens = personalisedtokens;
	}

	public String getPersonaliseduicards() {
		return personaliseduicards;
	}

	public void setPersonaliseduicards(String personaliseduicards) {
		this.personaliseduicards = personaliseduicards;
	}

	public String getProductfeature() {
		return productfeature;
	}

	public void setProductfeature(String productfeature) {
		this.productfeature = productfeature;
	}

	public String getProductlisting() {
		return productlisting;
	}

	public void setProductlisting(String productlisting) {
		this.productlisting = productlisting;
	}

	public String getUicard() {
		return uicard;
	}

	public void setUicard(String uicard) {
		this.uicard = uicard;
	}
	

	public String getBacktokenList() {
		return backtokenList;
	}

	public void setBacktokenList(String backtokenList) {
		this.backtokenList = backtokenList;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getFeaturevalue() {
		return featurevalue;
	}

	public void setFeaturevalue(String featurevalue) {
		this.featurevalue = featurevalue;
	}	
	
	public String getColumns(String tablename)
	{
		String columnlist="";
		
		switch (tablename) {
		case "AUTH_MECH":
			columnlist=getAuthmech();
			break;
		case "BORROW_LIFECYCLE_EVENT":
			columnlist=getBorrowlifecycleevent();
			break;
		case "BORROW_LOG":
			columnlist=getBorrowlog();
			break;
		case "BORROW_STATUS":
			columnlist=getBorrowstatus();
			break;
		case "CATEGORY":
			columnlist=getCategory();
			break;
		case "DISPLAYED_UI_CARDS":
			columnlist=getDisplayeduicards();
			break;
		case "FEATURE":
			columnlist=getFeature();
			break;
		case "FEATURE_VALUE":
			columnlist=getFeaturevalue();
			break;
		case "LISTED_PRODUCT_FEATURE":
			columnlist=getListedproductfeature();
			break;
		case "MY_TRUST_CIRCLE":
			columnlist=getMytrustcircle();
			break;
		case "PARTY":
			columnlist=getParty();
			break;
		case "PARTY_AUTH_MECH":
			columnlist=getPartyauthmech();
			break;
		case "PERSONALISED_TOKENS":
			columnlist=getPersonalisedtokens();
			break;
		case "PERSONALISED_UI_CARDS":
			columnlist=getPersonalisedtokens();
			break;
		case "PRODUCT":
			columnlist=getProduct();
			break;
		case "PRODUCT_FEATURE":
			columnlist=getProductfeature();
			break;
		case "PRODUCT_LISTING":
			columnlist=getProductlisting();
			break;
		case "UI_CARDS":
			columnlist=getUicard();
			break;		
		default:
			break;
		}	
		
		return columnlist;
		
	}
	

	
	
}