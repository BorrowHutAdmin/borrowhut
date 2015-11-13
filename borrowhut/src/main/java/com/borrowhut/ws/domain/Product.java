package com.borrowhut.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




@Entity
@Table(name="PRODUCT")
public class Product {
	/*PRD_IDint(11) NOT NULL
	CAT_NAMEvarchar(45) NOT NULL
	PRD_NAMEvarchar(45) NULL
	PRD_DESCRIPTIONvarchar(45) NULL
	PRD_PHOTO_LINKvarchar(45) NULL*/
	    @Id
	    @NotNull
	    @Column(name = "PRD_ID", nullable = false)
	    private Long productId;

	    @NotNull
	    @Column(name = "CAT_NAME", nullable = false)
	    @ManyToOne(fetch=FetchType.EAGER)
	    @JoinColumn(name="categoryName")
	    private Category category;

	    @Column(name = "PRD_NAME")
	    private String productName;
	    
	    @Column(name = "PRD_DESCRIPTION")
	    private String productDescription;
	    
	    @Column(name = "PRD_PHOTO_LINK")
	    private String productPhotoLink;
	
}
