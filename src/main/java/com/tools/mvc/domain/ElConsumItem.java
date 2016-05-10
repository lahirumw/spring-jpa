package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_consum_item database table.
 * 
 */
@Entity
@Table(name="el_consum_item")
@NamedQuery(name="ElConsumItem.findAll", query="SELECT e FROM ElConsumItem e")
public class ElConsumItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private int itemId;

	private int attr1;

	private String attr2;

	private int attr3;

	@Column(name="cons_item_id")
	private int consItemId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cons_time")
	private Date consTime;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="item_usage")
	private int itemUsage;

	public ElConsumItem() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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

	public int getAttr3() {
		return this.attr3;
	}

	public void setAttr3(int attr3) {
		this.attr3 = attr3;
	}

	public int getConsItemId() {
		return this.consItemId;
	}

	public void setConsItemId(int consItemId) {
		this.consItemId = consItemId;
	}

	public Date getConsTime() {
		return this.consTime;
	}

	public void setConsTime(Date consTime) {
		this.consTime = consTime;
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

	public int getItemUsage() {
		return this.itemUsage;
	}

	public void setItemUsage(int itemUsage) {
		this.itemUsage = itemUsage;
	}

}