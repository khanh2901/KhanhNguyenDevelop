package com.nguyenquockhanh.restaurant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_working")
public class EmployeeWorking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "restaurant_id")
	private int restaurantId;

	@Column(name = "restaurant_brand_id")
	private int restaurantBrandId;

	@Column(name = "branch_id")
	private int branchId;

	@Column(name = "job_name")
	private String jobName;

	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "job_assignor")
	private String jobAssignor;

	private int status;

	private String description;

	@Column(name = "task_level")
	private int taskLevel;

	private String created_at;

	private int orders;

	@Column(name = "is_orders")
	private int isOrders;

	public int getIsOrders() {
		return isOrders;
	}

	public void setIsOrders(int isOrders) {
		this.isOrders = isOrders;
	}

	
	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public int getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(int taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobAssignor() {
		return jobAssignor;
	}

	public void setJobAssignor(String jobAssignor) {
		this.jobAssignor = jobAssignor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getRestaurantBrandId() {
		return restaurantBrandId;
	}

	public void setRestaurantBrandId(int restaurantBrandId) {
		this.restaurantBrandId = restaurantBrandId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
