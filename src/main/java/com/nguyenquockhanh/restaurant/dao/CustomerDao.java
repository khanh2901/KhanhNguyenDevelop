package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;

import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;

public interface CustomerDao {

	void create(Customer customer);

	Customer findOne(int i);

	Customer findByPhone(String phone);

	List<Customer> findAll();
	
	void update(Customer customer);
	
	List<Customer> findByStatus();
	
	Customer findByUserNameAndPassword(String username , String password);
	
	Customer findByUserName(String username);

	void createWork(EmployeeWorking work);

	List<EmployeeWorking> findAllJob();

	EmployeeWorking findJobById(int id);

	void updateWork(EmployeeWorking work);

	List<EmployeeWorking> findAllJobDone();

	List<EmployeeWorking> findAllJobByStatus(int status,int id);
}
