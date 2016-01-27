package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCT_REQUEST database table.
 * 
 */
@Entity
@Table(name="PRODUCT_REQUEST")
@NamedQuery(name="ProductRequest.findAll", query="SELECT p FROM ProductRequest p")
@IdClass(ProductRequestPK.class)
public class ProductRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private ProductRequestPK id;*/
	
	/*@Column(name="REQUEST_ID")
	private int requestId;

	@Column(name="PARTY_PTY_ID", insertable=false, updatable=false)
	private int partyPtyId;*/
	
	@Id
	private int requestId;

	@Id
	private int partyPtyId;

	@Column(name="PRODUCT_CAT_NAME")
	private String productCatName;

	@Column(name="PRODUCT_DESC")
	private String productDesc;

	@Column(name="PRODUCT_PRD_ID")
	private int productPrdId;

	public ProductRequest() {
	}

	/*public ProductRequestPK getId() {
		return this.id;
	}

	public void setId(ProductRequestPK id) {
		this.id = id;
	}*/
	
	public int getRequestId() {
		return this.requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getPartyPtyId() {
		return this.partyPtyId;
	}
	public void setPartyPtyId(int partyPtyId) {
		this.partyPtyId = partyPtyId;
	}

	public String getProductCatName() {
		return this.productCatName;
	}

	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getProductPrdId() {
		return this.productPrdId;
	}

	public void setProductPrdId(int productPrdId) {
		this.productPrdId = productPrdId;
	}

}