package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.TableDao;
import com.nguyenquockhanh.restaurant.entity.Booking;
import com.nguyenquockhanh.restaurant.entity.Tables;

@Service("TableService")
@Transactional
public class TableServiceImpl implements TableService{

	@Autowired
	TableDao dao;

	@Override
	public void create(Booking booking) {
		// TODO Auto-generated method stub
		 dao.create(booking);
	}

	@Override
	public List<Booking> spGBooking(int i, int j, int k, String dateBooking) {
		// TODO Auto-generated method stub
		return dao.spGBooking(i, j, k, dateBooking);
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Booking findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public void update(Booking bookings) {
		// TODO Auto-generated method stub
		dao.update(bookings);
	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Tables> findAllTable() {
		// TODO Auto-generated method stub
		return dao.findAllTable();
	}

}
