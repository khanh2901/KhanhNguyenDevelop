package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.CategoryDao;
import com.nguyenquockhanh.restaurant.entity.Category;

@Service("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao dao;
	
	@Override
	public void create(Category category) {
		
		dao.create(category);
	}

	@Override
	public void update(Category material) {
		dao.update(material);		
	}

	@Override
	public Category findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public List<Category> findAll(int userId) {
		// TODO Auto-generated method stub
		return dao.findAll(userId);
	}

	@Override
	public List<Category> findByStatus(int userId) {
		// TODO Auto-generated method stub
		return dao.findByStatus(userId);
	}

}
