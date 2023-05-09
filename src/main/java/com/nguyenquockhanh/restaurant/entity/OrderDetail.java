package com.nguyenquockhanh.restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int user_id;

	private int restaurant_id;

	private int restaurant_brand_id;

	private int branch_id;

	private int category_id;
	
	private String category_name;

	private int material_unit_id;
	
	private String material_unit_name;

	private int material_id;
	
	private String material_name;
	
	private int price;
	
	private int cost_price;
	
	private int quantity;
	
	private int type;
	
	private int total_cost_price_amount;
	
	private int total_amount;
	
	private String created_at;
	
	private String updated_at;

	private int order_id;

	private int status;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public int getRestaurant_brand_id() {
		return restaurant_brand_id;
	}

	public void setRestaurant_brand_id(int restaurant_brand_id) {
		this.restaurant_brand_id = restaurant_brand_id;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getMaterial_unit_id() {
		return material_unit_id;
	}

	public void setMaterial_unit_id(int material_unit_id) {
		this.material_unit_id = material_unit_id;
	}

	public String getMaterial_unit_name() {
		return material_unit_name;
	}

	public void setMaterial_unit_name(String material_unit_name) {
		this.material_unit_name = material_unit_name;
	}

	public int getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotal_cost_price_amount() {
		return total_cost_price_amount;
	}

	public void setTotal_cost_price_amount(int total_cost_price_amount) {
		this.total_cost_price_amount = total_cost_price_amount;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
