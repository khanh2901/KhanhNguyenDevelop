package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Category;

public interface CategoryDao {

public void create(Category category);
	
	
	public void update (Category  material);
	
	
	public Category findOne (int id);
	
	
	public List<Category> findAll (int userId);
	
	
	public List<Category> findByStatus(int userId);


}
