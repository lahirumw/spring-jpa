package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_group database table.
 * 
 */
@Entity
@Table(name="el_group")
@NamedQuery(name="ElGroup.findAll", query="SELECT e FROM ElGroup e")
public class ElGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_id")
	private int groupId;

	private int attr1;

	private String attr2;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	@Column(name="grp_lead")
	private int grpLead;

	@Column(name="grp_name")
	private String grpName;

	public ElGroup() {
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public int getGrpLead() {
		return this.grpLead;
	}

	public void setGrpLead(int grpLead) {
		this.grpLead = grpLead;
	}

	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

}