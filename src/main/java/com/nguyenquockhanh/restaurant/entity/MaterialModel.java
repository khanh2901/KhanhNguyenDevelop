package com.nguyenquockhanh.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MaterialModel {
	@Id
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	
	@Column(name="wastage_rate")
	private int wastageRate;
	
	@Column(name="retail_price")
	private int retailPrice;
	
	@Column(name="cost_price")
	private int costPrice;

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
	
	
	

}
