package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;

public interface CustomerService {
	
	void create(Customer customer);

	List<Customer> findAll();

	Customer findOne(int i);

	Customer findByPhone(String phone);

	void update(Customer customer);

	List<Customer> findByStatus();
	
	Customer findByUserName(String username);

	Customer findByUserNameAndPassword(String username , String password);

	void createWork(EmployeeWorking work);

	List<EmployeeWorking> findAllJob();

	EmployeeWorking findJobById(int id);

	void updateWork(EmployeeWorking work);

	List<EmployeeWorking> findAllJobDone();

	List<EmployeeWorking> findAllJobByStatus(int status,int id);
}
