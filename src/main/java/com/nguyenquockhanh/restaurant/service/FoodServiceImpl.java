package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.FoodDao;
import com.nguyenquockhanh.restaurant.entity.BillModel;
import com.nguyenquockhanh.restaurant.entity.Cart;
import com.nguyenquockhanh.restaurant.entity.Food;

@Service("FoodService")
@Transactional
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodDao dao;

	@Override
	public void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent,
			int discountAmount, String foodName) {
		dao.spCreateFood(id, materials, vat, categoryType, discountPercent, discountAmount, foodName);

	}

	@Override
	public List<Food> findAll(int restaurantId) {
		// TODO Auto-generated method stub
		return dao.findAll(restaurantId);
	}

	@Override
	public Food findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public void update(Food food) {
		dao.update(food);

	}

	@Override
	public List<Food> findAllFoodPause(int restaurantId) {
		// TODO Auto-generated method stub
		return dao.findAllFoodPause(restaurantId);
	}

	@Override
	public void saleFood(int restaurantId, int userId, int quantity, String foodIds, String description) {
		dao.saleFood(restaurantId, userId, quantity, foodIds, description);
	}

	@Override
	public void create(Cart cart) {
		dao.create(cart);
	}

	@Override
	public void update(Cart cart) {
		dao.update(cart);
	}

	@Override
	public Cart findOneCart(int id, int userId) {
		// TODO Auto-generated method stub
		return dao.findOneCart(id,userId);
	}

	@Override
	public void delete(Cart cart) {
		dao.delete(cart);
		
	}

	@Override
	public Cart findOneCart1(int id) {
		// TODO Auto-generated method stub
		return dao.findOneCart1(id);
	}

	@Override
	public List<Cart> findAllCart(int restaurantId, int id) {
		// TODO Auto-generated method stub
		return dao.findAllCart(restaurantId, id);
	}

	@Override
	public BillModel spSuccessBill(int id, int restaurantId) {
		// TODO Auto-generated method stub
		return dao.spSuccessBill(id, restaurantId);
	}
}
