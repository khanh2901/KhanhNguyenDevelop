package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.User;

public interface UserService {

	void create(User user);

	List<User> findAll();

	List<User> findByStatus();

	User findOne(int id);

	void update(User user);

	User findByUserName(String username);
	
	User findByUserNameAndPss(String username,String pss);


	User findByAccessToken(String accessToken) ;

	List<User> findAllUser();

	User findByPhone(String phone);
	
	public List<User> findAllUsers();

	List<User> findAllEmloyee2();

}
