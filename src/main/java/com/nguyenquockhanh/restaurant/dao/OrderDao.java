package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.entity.OrderDetail;

public interface OrderDao {

	List<Order> findAll();

	List<OrderDetail> findAllOrderDetailByOrderId(int orderId);

	Order findOne(int id);

	void changStatus(Order order);

	List<Order> findByCustomerId(int id);

}
