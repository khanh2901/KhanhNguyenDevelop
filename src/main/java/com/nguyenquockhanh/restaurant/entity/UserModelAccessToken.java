package com.nguyenquockhanh.restaurant.entity;

public class UserModelAccessToken {
private int id;
	
	private String user_name;
	
	private String password;

	private int role;
	
	public UserModelAccessToken(int id,User user,int role) {
		super();
		this.id = id;
		this.user_name = user.getUser_name();
		this.password = user.getPassword();
		this.role = role;
	}

	public int getId() {
		return id;
	}

	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
