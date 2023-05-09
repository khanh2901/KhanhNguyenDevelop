package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.WareHouseSessionDetail;
import com.nguyenquockhanh.restaurant.entity.WareHousesSession;

public interface WarehouseSessionService {

	void importMaterial(int userId, int discountPercent, int vat ,
			int discountAmount, String description, String materials);

	 List<WareHousesSession> findAll(int id) ;

	void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent, int discountAmount,
			String foodName , String avatar);

	List<WareHouseSessionDetail> findAllWareHouseDetail(int id);
}
