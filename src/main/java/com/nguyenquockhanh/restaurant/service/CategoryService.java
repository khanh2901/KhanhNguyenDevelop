package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Category;

public interface CategoryService {

	public void create(Category category);
	
	
	public void update (Category  material);
	
	
	public Category findOne (int id);
	
	
	public List<Category> findAll (int userId);
	
	
	public List<Category> findByStatus(int userId);

}
