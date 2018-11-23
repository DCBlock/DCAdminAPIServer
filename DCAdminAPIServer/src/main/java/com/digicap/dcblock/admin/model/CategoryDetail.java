package com.digicap.dcblock.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
public class CategoryDetail {
	@JsonProperty("name")
	private String name;

	@JsonProperty("order")
	private int order;

	@JsonProperty("code")
	private int code;

	public CategoryDetail() {

	}

	public CategoryDetail(String name, int order, int code) {
		super();
		this.name = name;
		this.order = order;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", order=" + order + ", code=" + code + "]";
	}
}
