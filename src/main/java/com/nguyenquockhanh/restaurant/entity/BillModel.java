package com.nguyenquockhanh.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BillModel {

	@Id
	@Column(name="food_id")
	private String foodIds;
	 
	
	private int quantity;


	public String getFoodIds() {
		return foodIds;
	}


	public void setFoodIds(String foodIds) {
		this.foodIds = foodIds;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	
}
