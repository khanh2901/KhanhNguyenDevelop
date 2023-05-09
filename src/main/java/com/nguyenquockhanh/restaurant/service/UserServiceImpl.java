package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.UserDao;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao dao;

	
	@Override
	public void create(User user) {
		dao.create(user);
		
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public List<User> findByStatus() {
		return dao.findByStatus();
	}

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public void update(User user) {
		dao.update(user);
		
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserName(username);
	}

	@Override
	public User findByAccessToken(String accessToken)  {
		// TODO Auto-generated method stub
		return dao.findByAccessToken(accessToken);
	}

	@Override
	public User findByUserNameAndPss(String username, String pss) {
		// TODO Auto-generated method stub
		return dao.findByUserNameAndPss(username, pss);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return dao.findAllUser();
	}

	@Override
	public User findByPhone(String phone) {
		// TODO Auto-generated method stub
		return dao.findByPhone(phone);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return dao.findAllUsers();
	}

	@Override
	public List<User> findAllEmloyee2() {
		// TODO Auto-generated method stub
		return dao.findAllEmloyee2();
	}


}
