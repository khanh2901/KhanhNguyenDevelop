package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.BillModel;
import com.nguyenquockhanh.restaurant.entity.Cart;
import com.nguyenquockhanh.restaurant.entity.Food;

public interface FoodDao {
	void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent, int discountAmount,
			String foodName);
	
	List<Food> findAll(int restaurantId);
	
	Food findOne(int id);
	
	void update(Food userUpdate);
	
	List<Food> findAllFoodPause(int restaurantId);
	
	void saleFood(int restaurantId, int userId, int quantity,String foodIds , String description);

	void create(Cart cart);
	
	Cart findOneCart(int id,int userId);
	
	void delete(Cart cart);

	Cart findOneCart1(int id);

	List<Cart> findAllCart(int restaurantId, int id);
	
	void update(Cart cart);
	
	BillModel spSuccessBill(int id, int restaurantId);


}
