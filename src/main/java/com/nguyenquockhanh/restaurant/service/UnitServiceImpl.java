package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.UnitDao;
import com.nguyenquockhanh.restaurant.entity.Unit;

@Service("UnitService")
@Transactional
public  class UnitServiceImpl implements UnitService {
	
	@Autowired
	UnitDao dao;

	@Override
	public void create(Unit unit) {
		 dao.create(unit);
	}

	@Override
	public void update(Unit unit) {
		dao.update(unit);
		
	}

	@Override
	public List<Unit> findAll(int userId) {
		// TODO Auto-generated method stub
		return dao.findAll(userId);
	}

	@Override
	public List<Unit> findByStatus(int userId) {
		// TODO Auto-generated method stub
		return dao.findByStatus(userId);
	}
	
	
	
	@Override
	public Unit findOne(int id) {
		return dao.findOne(id);
		
	}
	
	
	
	

}
