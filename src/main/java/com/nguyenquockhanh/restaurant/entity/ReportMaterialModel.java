package com.nguyenquockhanh.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportMaterialModel {

	@Id
	@Column(name = "id")
	private int id;

	private String name;

	private String materia_unit_name;

	private int cost;

	private int revenue;

	private int profit;

	private int wastage_rate;

	private int retail_price;

	private int cost_price;

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

	public int getCost() {
		return cost;
	}

	public String getMateria_unit_name() {
		return materia_unit_name;
	}

	public void setMateria_unit_name(String materia_unit_name) {
		this.materia_unit_name = materia_unit_name;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int getWastage_rate() {
		return wastage_rate;
	}

	public void setWastage_rate(int wastage_rate) {
		this.wastage_rate = wastage_rate;
	}

	public int getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(int retail_price) {
		this.retail_price = retail_price;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

}
