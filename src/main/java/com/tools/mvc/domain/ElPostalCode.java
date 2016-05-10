package com.tools.mvc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the el_postal_code database table.
 * 
 */
@Entity
@Table(name="el_postal_code")
@NamedQuery(name="ElPostalCode.findAll", query="SELECT e FROM ElPostalCode e")
public class ElPostalCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="code_id")
	private int codeId;

	private String code;

	@Column(name="code_desc")
	private String codeDesc;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="created_user")
	private String createdUser;

	public ElPostalCode() {
	}

	public int getCodeId() {
		return this.codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
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

}