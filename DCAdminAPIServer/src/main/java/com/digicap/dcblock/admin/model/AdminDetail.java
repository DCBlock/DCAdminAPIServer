package com.digicap.dcblock.admin.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminDetail {

	@JsonProperty("index")
	private BigInteger index;

	@JsonProperty("id")
	private String id;

	@JsonProperty("password")
	private String password;

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("company")
	private String company;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("manageuser")
	private boolean manageuser;

	@JsonProperty("managemenu")
	private boolean managemenu;

	@JsonProperty("viewstatistics")
	private boolean viewstatistics;

	@JsonProperty("manageadmin")
	private boolean manageadmin;

	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT+9")
	@JsonProperty("regdate")
	private Date regdate;

	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT+9")
	@JsonProperty("updatedate")
	private Date updatedate;

	public AdminDetail() {

	}

	public AdminDetail(BigInteger index, String id, String password, String name, String email, String company,
			String scope, boolean manageuser, boolean managemenu, boolean viewstatistics, boolean manageadmin,
			Date regdate, Date updatedate) {
		super();
		this.index = index;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.company = company;
		this.scope = scope;
		this.manageuser = manageuser;
		this.managemenu = managemenu;
		this.viewstatistics = viewstatistics;
		this.manageadmin = manageadmin;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public BigInteger getIndex() {
		return index;
	}

	public void setIndex(BigInteger index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public boolean isManageuser() {
		return manageuser;
	}

	public void setManageuser(boolean manageuser) {
		this.manageuser = manageuser;
	}

	public boolean isManagemenu() {
		return managemenu;
	}

	public void setManagemenu(boolean managemenu) {
		this.managemenu = managemenu;
	}

	public boolean isViewstatistics() {
		return viewstatistics;
	}

	public void setViewstatistics(boolean viewstatistics) {
		this.viewstatistics = viewstatistics;
	}

	public boolean isManageadmin() {
		return manageadmin;
	}

	public void setManageadmin(boolean manageadmin) {
		this.manageadmin = manageadmin;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "AdminDetail [index=" + index + ", id=" + id + ", password=" + password + ", name=" + name + ", email="
				+ email + ", company=" + company + ", scope=" + scope + ", manageuser=" + manageuser
				+ ", managemenu=" + managemenu + ", viewstatistics=" + viewstatistics + ", manageadmin=" + manageadmin
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}

}
