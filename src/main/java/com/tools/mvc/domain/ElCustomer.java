package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_customer database table.
 * 
 */
@Entity
@Table(name="el_customer")
@NamedQuery(name="ElCustomer.findAll", query="SELECT e FROM ElCustomer e")
public class ElCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cust_id")
	private int custId;

	private String add1;

	private String add2;

	private String add3;

	private int attr1;

	private String category;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="cust_dtl")
	private String custDtl;

	@Column(name="cust_name")
	private String custName;

	private String email;

	private int regon;

	private String status;

	@Column(name="tel_no")
	private String telNo;

	public ElCustomer() {
	}

	public int getCustId() {
		return this.custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getAdd1() {
		return this.add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return this.add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return this.add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public int getAttr1() {
		return this.attr1;
	}

	public void setAttr1(int attr1) {
		this.attr1 = attr1;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getCustDtl() {
		return this.custDtl;
	}

	public void setCustDtl(String custDtl) {
		this.custDtl = custDtl;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRegon() {
		return this.regon;
	}

	public void setRegon(int regon) {
		this.regon = regon;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

}