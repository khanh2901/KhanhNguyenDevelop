package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.entity.OrderDetail;

public interface OrderService {

	List<Order> findAll();

	List<OrderDetail> findAllOrderDetailByOrderId(int orderId);

	Order findOne(int id);

	void changStatus(Order order);

	List<Order> findByCustomerId(int id);

}
