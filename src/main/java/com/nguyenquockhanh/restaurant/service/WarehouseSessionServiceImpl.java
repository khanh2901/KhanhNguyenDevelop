package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.WarehouseSessionDao;
import com.nguyenquockhanh.restaurant.entity.WareHouseSessionDetail;
import com.nguyenquockhanh.restaurant.entity.WareHousesSession;

@Service("WarehouseService")
@Transactional
public class WarehouseSessionServiceImpl implements WarehouseSessionService{

	@Autowired
	WarehouseSessionDao dao;
	
	@Override
	public void importMaterial(int userId, int discountPercent, int vat, int discountAmount, String description,
			String materials) {
    		
		dao.importMaterial(userId, discountPercent, vat, discountAmount, description, materials);
	}

	@Override
	public List<WareHousesSession> findAll(int id) {
		return dao.findAll(id);
	}

	@Override
	public void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent,
			int discountAmount, String foodName , String avatar) {
		dao.spCreateFood(id, materials, vat, categoryType, discountPercent, discountAmount, foodName ,avatar);
		
	}

	@Override
	public List<WareHouseSessionDetail> findAllWareHouseDetail(int id) {
		// TODO Auto-generated method stub
		return dao.findAllWareHouseDetail(id);
	}

}
