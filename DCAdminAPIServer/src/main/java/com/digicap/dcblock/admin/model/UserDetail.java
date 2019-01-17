package com.digicap.dcblock.admin.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties({ "index", "regdate", "updatedate" })
public class UserDetail {

	@JsonProperty("index")
	private BigInteger index;

	@JsonProperty("email")
	private String email;

	@JsonProperty("rfid")
	private String rfid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("company")
	private String company;

	@JsonProperty("leave")
	private Boolean leave;

	@JsonProperty("regdate")
	private Date regdate;

	@JsonProperty("updatedate")
	private Date updatedate;

	public UserDetail() {

	}

	public UserDetail(BigInteger index, String email, String rfid, String name, String company, Boolean leave, Date regdate,
			Date updatedate) {
		super();
		this.index = index;
		this.email = email;
		this.rfid = rfid;
		this.name = name;
		this.company = company;
		this.leave = leave;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public BigInteger getIndex() {
		return index;
	}

	public void setIndex(BigInteger index) {
		this.index = index;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getLeave() {
		return leave;
	}

	public void setLeave(Boolean leave) {
		this.leave = leave;
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
		return "UserDetail [index=" + index + ", email=" + email + ", rfid=" + rfid + ", name=" + name + ", company="
				+ company + ", leave=" + leave + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}

}
