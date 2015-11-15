package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FEATURE_VALUE database table.
 * 
 */
@Entity
@Table(name="FEATURE_VALUE")
@NamedQuery(name="FeatureValue.findAll", query="SELECT f FROM FeatureValue f")
public class FeatureValue  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FeatureValuePK id;

	//bi-directional many-to-one association to Feature
	@ManyToOne
	@JoinColumn(name="FTR_NAME", nullable=false, insertable=false, updatable=false)
	private Feature feature;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="PRODUCT_CAT_NAME", referencedColumnName="CAT_NAME", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PRODUCT_PRD_ID", referencedColumnName="PRD_ID", nullable=false, insertable=false, updatable=false)
		})
	private Product product;

	public FeatureValue() {
	}

	public FeatureValuePK getId() {
		return this.id;
	}

	public void setId(FeatureValuePK id) {
		this.id = id;
	}

	public Feature getFeature() {
		return this.feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}