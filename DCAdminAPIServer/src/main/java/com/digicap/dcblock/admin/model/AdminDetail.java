package com.digicap.dcblock.admin.model;

import java.math.BigInteger;
import java.util.Date;

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

	@JsonProperty("permission")
	private int permission;

	@JsonProperty("regdate")
	private Date regdate;

	@JsonProperty("updatedate")
	private Date updatedate;

	public AdminDetail() {

	}

	public AdminDetail(BigInteger index, String id, String password, String name, String email, String company,
			int permission, Date regdate, Date updatedate) {
		super();
		this.index = index;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.company = company;
		this.permission = permission;
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

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
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
				+ email + ", company=" + company + ", permission=" + permission + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + "]";
	}

}
