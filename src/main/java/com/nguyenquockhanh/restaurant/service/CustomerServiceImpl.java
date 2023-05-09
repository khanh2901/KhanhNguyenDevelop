package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.CustomerDao;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;

@Service("CustomerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao dao;
	
	@Override
	public void create(Customer customer) {
		dao.create(customer);
		
	}

	@Override
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public Customer findOne(int i) {
		// TODO Auto-generated method stub
		return dao.findOne(i);
	}

	@Override
	public Customer findByPhone(String phone) {
		// TODO Auto-generated method stub
		return dao.findByPhone(phone);
	}

	@Override
	public void update(Customer customer) {
		dao.update(customer);
		
	}

	@Override
	public List<Customer> findByStatus() {
		// TODO Auto-generated method stub
		return dao.findByStatus();
	}

	@Override
	public Customer findByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByUserNameAndPassword(username, password);
	}

	@Override
	public Customer findByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserName(username);
	}

	@Override
	public void createWork(EmployeeWorking work) {
		dao.createWork(work);
		
	}

	@Override
	public List<EmployeeWorking> findAllJob() {
		// TODO Auto-generated method stub
		return dao.findAllJob();
	}

	@Override
	public EmployeeWorking findJobById(int id) {
		// TODO Auto-generated method stub
		return dao.findJobById(id);
	}

	@Override
	public void updateWork(EmployeeWorking work) {
		dao.updateWork(work);
		
	}

	@Override
	public List<EmployeeWorking> findAllJobDone() {
		// TODO Auto-generated method stub
		return dao.findAllJobDone();
	}

	@Override
	public List<EmployeeWorking> findAllJobByStatus(int status , int id) {
		// TODO Auto-generated method stub
		return dao.findAllJobByStatus(status,id);
	}
	
	
}

