package com.nguyenquockhanh.restaurant.entity;

public class CustomerModelAccessToken {

	private int id;
	
	private String user_name;
	
	private String password;

	
	public CustomerModelAccessToken(int id,Customer customer) {
		super();
		this.id = id;
		this.user_name = customer.getUser_name();
		this.password = customer.getPassword();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
