package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Unit;

public interface UnitService {

	public void create(Unit unit);
	
	public void update(Unit unit);

	public List<Unit> findAll(int userId);

	public List<Unit> findByStatus(int userId);

	public Unit findOne(int id);
}
