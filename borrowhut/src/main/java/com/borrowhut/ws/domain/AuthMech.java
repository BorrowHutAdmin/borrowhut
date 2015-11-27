package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AUTH_MECH database table.
 * 
 */
@Entity
@Table(name="AUTH_MECH")
@NamedQuery(name="AuthMech.findAll", query="SELECT a FROM AuthMech a")
public class AuthMech implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AMH_ID", unique=true, nullable=false, length=10)
	private String amhId;

	@Column(name="AMH_NAME", length=45)
	private String amhName;

	//bi-directional many-to-one association to PartyAuthMech
	@OneToMany(mappedBy="authMech")
	private List<PartyAuthMech> partyAuthMeches;

	public AuthMech() {
	}

	public String getAmhId() {
		return this.amhId;
	}

	public void setAmhId(String amhId) {
		this.amhId = amhId;
	}

	public String getAmhName() {
		return this.amhName;
	}

	public void setAmhName(String amhName) {
		this.amhName = amhName;
	}

	public List<PartyAuthMech> getPartyAuthMeches() {
		return this.partyAuthMeches;
	}

	public void setPartyAuthMeches(List<PartyAuthMech> partyAuthMeches) {
		this.partyAuthMeches = partyAuthMeches;
	}

	public PartyAuthMech addPartyAuthMech(PartyAuthMech partyAuthMech) {
		getPartyAuthMeches().add(partyAuthMech);
		partyAuthMech.setAuthMech(this);

		return partyAuthMech;
	}

	public PartyAuthMech removePartyAuthMech(PartyAuthMech partyAuthMech) {
		getPartyAuthMeches().remove(partyAuthMech);
		partyAuthMech.setAuthMech(null);

		return partyAuthMech;
	}

}