package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FEATURE_VALUE database table.
 * 
 */
@Embeddable
public class FeatureValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FTR_NAME", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String ftrName;

	@Column(name="PRODUCT_PRD_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int productPrdId;

	@Column(name="PRODUCT_CAT_NAME", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String productCatName;

	@Column(name="FTR_ALLOWABLE_VALUES", unique=true, nullable=false, length=45)
	private String ftrAllowableValues;

	public FeatureValuePK() {
	}
	public String getFtrName() {
		return this.ftrName;
	}
	public void setFtrName(String ftrName) {
		this.ftrName = ftrName;
	}
	public int getProductPrdId() {
		return this.productPrdId;
	}
	public void setProductPrdId(int productPrdId) {
		this.productPrdId = productPrdId;
	}
	public String getProductCatName() {
		return this.productCatName;
	}
	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}
	public String getFtrAllowableValues() {
		return this.ftrAllowableValues;
	}
	public void setFtrAllowableValues(String ftrAllowableValues) {
		this.ftrAllowableValues = ftrAllowableValues;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FeatureValuePK)) {
			return false;
		}
		FeatureValuePK castOther = (FeatureValuePK)other;
		return 
			this.ftrName.equals(castOther.ftrName)
			&& (this.productPrdId == castOther.productPrdId)
			&& this.productCatName.equals(castOther.productCatName)
			&& this.ftrAllowableValues.equals(castOther.ftrAllowableValues);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ftrName.hashCode();
		hash = hash * prime + this.productPrdId;
		hash = hash * prime + this.productCatName.hashCode();
		hash = hash * prime + this.ftrAllowableValues.hashCode();
		
		return hash;
	}
}