package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PRODUCT_LISTING database table.
 * 
 */
@Entity
@Table(name="PRODUCT_LISTING")
@NamedQuery(name="ProductListing.findAll", query="SELECT p FROM ProductListing p")
public class ProductListing implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private ProductListingPK id;*/
	
	@Id
	@Column(name="PLS_ID", unique=true, nullable=false)
	private int plsId;

	@Column(name="PTY_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int ptyId;

	
	public int getPlsId() {
		return this.plsId;
	}
	public void setPlsId(int plsId) {
		this.plsId = plsId;
	}
	public int getPtyId() {
		return this.ptyId;
	}
	public void setPtyId(int ptyId) {
		this.ptyId = ptyId;
	}
	@Column(name="PLS_AGREEMENT_SIGNED", length=45)
	private String plsAgreementSigned;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PLS_EXPIRY")
	private Date plsExpiry;

	@Column(name="PLS_FIRST_CIRCLE_PRICE", length=45)
	private String plsFirstCirclePrice;

	@Column(name="PLS_PRODUCT_CONDITION", length=45)
	private String plsProductCondition;

	@Column(name="PLS_SECOND_CIRCLE_PRICE", length=45)
	private String plsSecondCirclePrice;

	//bi-directional many-to-one association to BorrowLog
	@OneToMany(mappedBy="productListing")
	private List<BorrowLog> borrowLogs;

	//bi-directional many-to-one association to ListedProductFeature
	@OneToMany(mappedBy="productListing")
	private List<ListedProductFeature> listedProductFeatures;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="PTY_ID", nullable=false, insertable=false, updatable=false)
	private Party party;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CAT_NAME", referencedColumnName="CAT_NAME", nullable=false),
		@JoinColumn(name="PRD_ID", referencedColumnName="PRD_ID", nullable=false)
		})
	private Product product;

	public ProductListing() {
	}

	/*public ProductListingPK getId() {
		return this.id;
	}

	public void setId(ProductListingPK id) {
		this.id = id;
	}*/

	public String getPlsAgreementSigned() {
		return this.plsAgreementSigned;
	}

	public void setPlsAgreementSigned(String plsAgreementSigned) {
		this.plsAgreementSigned = plsAgreementSigned;
	}

	public Date getPlsExpiry() {
		return this.plsExpiry;
	}

	public void setPlsExpiry(Date plsExpiry) {
		this.plsExpiry = plsExpiry;
	}

	public String getPlsFirstCirclePrice() {
		return this.plsFirstCirclePrice;
	}

	public void setPlsFirstCirclePrice(String plsFirstCirclePrice) {
		this.plsFirstCirclePrice = plsFirstCirclePrice;
	}

	public String getPlsProductCondition() {
		return this.plsProductCondition;
	}

	public void setPlsProductCondition(String plsProductCondition) {
		this.plsProductCondition = plsProductCondition;
	}

	public String getPlsSecondCirclePrice() {
		return this.plsSecondCirclePrice;
	}

	public void setPlsSecondCirclePrice(String plsSecondCirclePrice) {
		this.plsSecondCirclePrice = plsSecondCirclePrice;
	}

	public List<BorrowLog> getBorrowLogs() {
		return this.borrowLogs;
	}

	public void setBorrowLogs(List<BorrowLog> borrowLogs) {
		this.borrowLogs = borrowLogs;
	}

	public BorrowLog addBorrowLog(BorrowLog borrowLog) {
		getBorrowLogs().add(borrowLog);
		borrowLog.setProductListing(this);

		return borrowLog;
	}

	public BorrowLog removeBorrowLog(BorrowLog borrowLog) {
		getBorrowLogs().remove(borrowLog);
		borrowLog.setProductListing(null);

		return borrowLog;
	}

	public List<ListedProductFeature> getListedProductFeatures() {
		return this.listedProductFeatures;
	}

	public void setListedProductFeatures(List<ListedProductFeature> listedProductFeatures) {
		this.listedProductFeatures = listedProductFeatures;
	}

	public ListedProductFeature addListedProductFeature(ListedProductFeature listedProductFeature) {
		getListedProductFeatures().add(listedProductFeature);
		listedProductFeature.setProductListing(this);

		return listedProductFeature;
	}

	public ListedProductFeature removeListedProductFeature(ListedProductFeature listedProductFeature) {
		getListedProductFeatures().remove(listedProductFeature);
		listedProductFeature.setProductListing(null);

		return listedProductFeature;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}