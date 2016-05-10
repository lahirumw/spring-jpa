package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_control database table.
 * 
 */
@Entity
@Table(name="el_control")
@NamedQuery(name="ElControl.findAll", query="SELECT e FROM ElControl e")
public class ElControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="contr_id")
	private int contrId;

	@Temporal(TemporalType.DATE)
	private Date altDate;

	private String attr1;

	private String attr2;

	private int attrn1;

	private int attrn2;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_BILL_DATE")
	private Date lastBillDate;

	public ElControl() {
	}

	public int getContrId() {
		return this.contrId;
	}

	public void setContrId(int contrId) {
		this.contrId = contrId;
	}

	public Date getAltDate() {
		return this.altDate;
	}

	public void setAltDate(Date altDate) {
		this.altDate = altDate;
	}

	public String getAttr1() {
		return this.attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return this.attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public int getAttrn1() {
		return this.attrn1;
	}

	public void setAttrn1(int attrn1) {
		this.attrn1 = attrn1;
	}

	public int getAttrn2() {
		return this.attrn2;
	}

	public void setAttrn2(int attrn2) {
		this.attrn2 = attrn2;
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

	public Date getLastBillDate() {
		return this.lastBillDate;
	}

	public void setLastBillDate(Date lastBillDate) {
		this.lastBillDate = lastBillDate;
	}

}