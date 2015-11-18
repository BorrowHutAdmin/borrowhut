package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LISTED_PRODUCT_FEATURE database table.
 * 
 */
@Entity
@Table(name="LISTED_PRODUCT_FEATURE")
@NamedQuery(name="ListedProductFeature.findAll", query="SELECT l FROM ListedProductFeature l")
public class ListedProductFeature implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ListedProductFeaturePK id;

	@Column(name="LPF_FTR_VALUE", length=45)
	private String lpfFtrValue;

	//bi-directional many-to-one association to ProductFeature
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CAT_NAME", referencedColumnName="CAT_NAME", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="FTR_NAME", referencedColumnName="FTR_NAME", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PRD_ID", referencedColumnName="PRD_ID", nullable=false, insertable=false, updatable=false)
		})
	private ProductFeature productFeature;

	//bi-directional many-to-one association to ProductListing
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="PLS_ID", referencedColumnName="PLS_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTY_ID", referencedColumnName="PTY_ID", nullable=false, insertable=false, updatable=false)
		})
	private ProductListing productListing;

	public ListedProductFeature() {
	}

	public ListedProductFeaturePK getId() {
		return this.id;
	}

	public void setId(ListedProductFeaturePK id) {
		this.id = id;
	}

	public String getLpfFtrValue() {
		return this.lpfFtrValue;
	}

	public void setLpfFtrValue(String lpfFtrValue) {
		this.lpfFtrValue = lpfFtrValue;
	}

	public ProductFeature getProductFeature() {
		return this.productFeature;
	}

	public void setProductFeature(ProductFeature productFeature) {
		this.productFeature = productFeature;
	}

	public ProductListing getProductListing() {
		return this.productListing;
	}

	public void setProductListing(ProductListing productListing) {
		this.productListing = productListing;
	}

}