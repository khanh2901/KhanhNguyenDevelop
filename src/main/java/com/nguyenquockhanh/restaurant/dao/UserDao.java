package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.User;

public interface UserDao {

	void create(User user);

	List<User> findAll();

	List<User> findByStatus();

	User findOne(int id);

	void update(User user);

	User findByUserName(String username);

	public User findByAccessToken(String accessToken);

	public User findByUserNameAndPss(String username, String pss);

	List<User> findAllUser();

	User findByPhone(String phone);

	public List<User> findAllUsers();

	List<User> findAllEmloyee2();

}
