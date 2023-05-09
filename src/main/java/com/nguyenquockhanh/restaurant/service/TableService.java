package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Booking;
import com.nguyenquockhanh.restaurant.entity.Tables;

public interface TableService {

	void create(Booking booking);

	List<Booking> spGBooking(int i, int j, int k, String dateBooking);

	List<Booking> findAll();

	Booking findOne(int id);

	void update(Booking bookings);

	Booking findById(int id);

	List<Tables> findAllTable();
	

}
