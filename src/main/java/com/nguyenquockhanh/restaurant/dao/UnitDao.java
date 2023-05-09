package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Unit;

public interface UnitDao {
	public void create(Unit unit);
	
	public void update(Unit unit);
	
	public List<Unit> findAll(int userId);

	public List<Unit> findByStatus(int userId);
	
	public Unit findOne(int id);

}
