package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@Table(name="PRODUCT")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@IdClass(ProductPK.class)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	 @Id private Integer prdId;
	  
	 @Id private String catName;
	

	@Column(name="PRD_DESCRIPTION", length=45)
	private String prdDescription;

	@Column(name="PRD_NAME", length=45)
	private String prdName;

	@Column(name="PRD_PHOTO_LINK", length=45)
	private String prdPhotoLink;

	//bi-directional many-to-one association to FeatureValue
	@OneToMany(mappedBy="product")
	private List<FeatureValue> featureValues;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CAT_NAME", nullable=false, insertable=false, updatable=false)
	private Category category;

	//bi-directional many-to-many association to Feature
	@ManyToMany
	@JoinTable(
		name="PRODUCT_FEATURE"
		, joinColumns={
			@JoinColumn(name="CAT_NAME", referencedColumnName="CAT_NAME", nullable=false),
			@JoinColumn(name="PRD_ID", referencedColumnName="PRD_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="FTR_NAME", nullable=false)
			}
		)
	private List<Feature> features;

	//bi-directional many-to-one association to ProductListing
	@OneToMany(mappedBy="product")
	private List<ProductListing> productListings;

	public Product() {
	}

	/*public ProductPK getId() {
		return this.id;
	}

	public void setId(ProductPK id) {
		this.id = id;
	}*/

	public String getPrdDescription() {
		return this.prdDescription;
	}

	public Integer getPrdId() {
		return prdId;
	}

	public void setPrdId(Integer prdId) {
		this.prdId = prdId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public void setPrdDescription(String prdDescription) {
		this.prdDescription = prdDescription;
	}

	public String getPrdName() {
		return this.prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getPrdPhotoLink() {
		return this.prdPhotoLink;
	}

	public void setPrdPhotoLink(String prdPhotoLink) {
		this.prdPhotoLink = prdPhotoLink;
	}

	public List<FeatureValue> getFeatureValues() {
		return this.featureValues;
	}

	public void setFeatureValues(List<FeatureValue> featureValues) {
		this.featureValues = featureValues;
	}

	public FeatureValue addFeatureValue(FeatureValue featureValue) {
		getFeatureValues().add(featureValue);
		featureValue.setProduct(this);

		return featureValue;
	}

	public FeatureValue removeFeatureValue(FeatureValue featureValue) {
		getFeatureValues().remove(featureValue);
		featureValue.setProduct(null);

		return featureValue;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Feature> getFeatures() {
		return this.features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<ProductListing> getProductListings() {
		return this.productListings;
	}

	public void setProductListings(List<ProductListing> productListings) {
		this.productListings = productListings;
	}

	public ProductListing addProductListing(ProductListing productListing) {
		getProductListings().add(productListing);
		productListing.setProduct(this);

		return productListing;
	}

	public ProductListing removeProductListing(ProductListing productListing) {
		getProductListings().remove(productListing);
		productListing.setProduct(null);

		return productListing;
	}

}