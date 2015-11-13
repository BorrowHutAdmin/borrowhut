package com.borrowhut.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="CATEGORY")
public class Category {
	/*CAT_NAMEvarchar(45) NOT NULL*/
	@Id
	@NotNull
	@Column(name="CAT_NAME")
	private String categoryName;
}
