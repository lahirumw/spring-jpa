package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_region database table.
 * 
 */
@Entity
@Table(name="el_region")
@NamedQuery(name="ElRegion.findAll", query="SELECT e FROM ElRegion e")
public class ElRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="region_id")
	private int regionId;

	private int attr1;

	private String attr2;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="dis_id")
	private int disId;

	@Column(name="postal_code")
	private int postalCode;

	@Column(name="region_name")
	private String regionName;

	public ElRegion() {
	}

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getAttr1() {
		return this.attr1;
	}

	public void setAttr1(int attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return this.attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public int getDisId() {
		return this.disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}