package com.digicap.dcblock.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty("Users")
	private List<UserDetail> userDetail;
	
	public User() {
		
	}
	
	public User(List<UserDetail> userDetail) {
		super();
		this.userDetail = userDetail;
	}

	public List<UserDetail> getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(List<UserDetail> userDetail) {
		this.userDetail = userDetail;
	}

	@Override
	public String toString() {
		return "User [userDetail=" + userDetail + "]";
	}


}
