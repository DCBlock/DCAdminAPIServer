package com.digicap.dcblock.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
public class Category {
	@JsonProperty("Category")
	private List<CategoryDetail> categoryDetail;

	public Category() {

	}

	public Category(List<CategoryDetail> categoryDetail) {
		super();
		this.categoryDetail = categoryDetail;
	}

	public List<CategoryDetail> getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(List<CategoryDetail> categoryDetail) {
		this.categoryDetail = categoryDetail;
	}

	@Override
	public String toString() {
		return "Category [categoryDetail=" + categoryDetail + "]";
	}
}
