package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.OrderDao;
import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.entity.OrderDetail;

@Service("OrderService")
@Transactional
public class OrderServiceImpl  implements OrderService {

	@Autowired
	OrderDao dao;

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<OrderDetail> findAllOrderDetailByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return dao.findAllOrderDetailByOrderId(orderId);
	}

	@Override
	public Order findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public void changStatus(Order order) {
		dao.changStatus(order);
		
	}

	@Override
	public List<Order> findByCustomerId(int id) {
		// TODO Auto-generated method stub
		return dao.findByCustomerId(id);
	}
	
	
	
	
	
}
