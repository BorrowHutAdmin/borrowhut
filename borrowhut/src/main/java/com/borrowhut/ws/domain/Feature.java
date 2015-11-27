package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FEATURE database table.
 * 
 */
@Entity
@Table(name="FEATURE")
@NamedQuery(name="Feature.findAll", query="SELECT f FROM Feature f")
public class Feature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FTR_NAME", unique=true, nullable=false, length=45)
	private String ftrName;

	@Column(name="FTR_DESC", length=80)
	private String ftrDesc;

	//bi-directional many-to-one association to FeatureValue
	@OneToMany(mappedBy="feature")
	private List<FeatureValue> featureValues;

	//bi-directional many-to-many association to Product
	@ManyToMany(mappedBy="features")
	private List<Product> products;

	public Feature() {
	}

	public String getFtrName() {
		return this.ftrName;
	}

	public void setFtrName(String ftrName) {
		this.ftrName = ftrName;
	}

	public String getFtrDesc() {
		return this.ftrDesc;
	}

	public void setFtrDesc(String ftrDesc) {
		this.ftrDesc = ftrDesc;
	}

	public List<FeatureValue> getFeatureValues() {
		return this.featureValues;
	}

	public void setFeatureValues(List<FeatureValue> featureValues) {
		this.featureValues = featureValues;
	}

	public FeatureValue addFeatureValue(FeatureValue featureValue) {
		getFeatureValues().add(featureValue);
		featureValue.setFeature(this);

		return featureValue;
	}

	public FeatureValue removeFeatureValue(FeatureValue featureValue) {
		getFeatureValues().remove(featureValue);
		featureValue.setFeature(null);

		return featureValue;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}