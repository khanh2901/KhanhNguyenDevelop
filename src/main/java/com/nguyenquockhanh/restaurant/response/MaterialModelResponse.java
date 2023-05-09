package com.nguyenquockhanh.restaurant.response;

import java.util.List;
import java.util.stream.Collectors;

import com.nguyenquockhanh.restaurant.entity.MaterialModel;

public class MaterialModelResponse {

	private int id;

	private String name;

	private int totalQuantity;

	private int wastageRate;

	private int retailPrice;

	private int costPrice;

	public MaterialModelResponse() {}

	public MaterialModelResponse(MaterialModel a) {
		this.id = a.getId();
		this.name = a.getName();
		this.totalQuantity = a.getTotalQuantity();
		this.wastageRate = a.getWastageRate();
		this.retailPrice = a.getRetailPrice();
		this.costPrice = a.getCostPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getWastageRate() {
		return wastageRate;
	}

	public void setWastageRate(int wastageRate) {
		this.wastageRate = wastageRate;
	}

	public int getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public List<MaterialModelResponse> maptoList(List<MaterialModel> e) {

		return e.stream().map(x -> new MaterialModelResponse(x)).collect(Collectors.toList());
	}

}
