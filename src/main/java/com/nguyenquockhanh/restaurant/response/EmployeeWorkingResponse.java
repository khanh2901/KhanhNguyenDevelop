package com.nguyenquockhanh.restaurant.response;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;

public class EmployeeWorkingResponse {

	private int id;

	private int restaurantId;

	private int restaurantBrandId;

	private int branchId;

	private String jobName;

	private int employeeId;

	private String employeeName;

	private String jobAssignor;

	private int status;

	private String description;

	private int taskLevel;
	
	private String created_at;

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

	public String getJobAssignor() {
		return jobAssignor;
	}

	public void setJobAssignor(String jobAssignor) {
		this.jobAssignor = jobAssignor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(int taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public EmployeeWorkingResponse(EmployeeWorking e) {
		this.id = e.getId();
		this.restaurantId = e.getRestaurantId();
		this.restaurantBrandId = e.getRestaurantBrandId();
		this.branchId = e.getBranchId();
		this.jobName = e.getJobName();
		this.employeeId = e.getEmployeeId();
		this.employeeName = e.getEmployeeName();
		this.jobAssignor = e.getJobAssignor();
		this.status = e.getStatus();
		this.description = e.getDescription();
		this.taskLevel = e.getTaskLevel();
		this.created_at = e.getCreated_at();
	}

	public EmployeeWorkingResponse() {
		super();
	}
	
	
	
	public List<EmployeeWorkingResponse> mapToList (List<EmployeeWorking> x){
		return x.stream().map(e -> new EmployeeWorkingResponse(e)).collect(Collectors.toList());
		
	}
}
