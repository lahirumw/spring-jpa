package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_consum database table.
 * 
 */
@Entity
@Table(name="el_consum")
@NamedQuery(name="ElConsum.findAll", query="SELECT e FROM ElConsum e")
public class ElConsum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cons_id")
	private int consId;

	private int attr1;

	private String attr2;

	@Column(name="cons_item")
	private String consItem;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="cust_id")
	private int custId;

	public ElConsum() {
	}

	public int getConsId() {
		return this.consId;
	}

	public void setConsId(int consId) {
		this.consId = consId;
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

	public String getConsItem() {
		return this.consItem;
	}

	public void setConsItem(String consItem) {
		this.consItem = consItem;
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

	public int getCustId() {
		return this.custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

}