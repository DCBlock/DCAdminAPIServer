package com.digicap.dcblock.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
	@JsonProperty("Admin")
	private List<AdminDetail> adminDetail;

	public Admin() {

	}

	public Admin(List<AdminDetail> adminDetail) {
		super();
		this.adminDetail = adminDetail;
	}

	public List<AdminDetail> getAdminDetail() {
		return adminDetail;
	}

	public void setAdminDetail(List<AdminDetail> adminDetail) {
		this.adminDetail = adminDetail;
	}

	@Override
	public String toString() {
		return "Admin [adminDetail=" + adminDetail + "]";
	}

}
