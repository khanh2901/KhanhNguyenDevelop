package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.MaterialDao;
import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.MaterialModel;
import com.nguyenquockhanh.restaurant.response.StoreProcedureListResult;

@Service("MaterialService")
@Transactional
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	MaterialDao dao;

	@Override
	public void create(Material material) {
			dao.create(material);
	}

	@Override
	public void update(Material material) {
				dao.update(material);		
	}

	@Override
	public Material findOne(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Material> findAll(int id) {
		return dao.findAll(id);
	}

	@Override
	public List<Material> findByStatus(int userId) {
		// TODO Auto-generated method stub
		return dao.findByStatus(userId);
	}

	@Override
	public List<MaterialModel> getLisstTotalQuanittyMaterial(int id) {
		// TODO Auto-generated method stub
		return dao.getLisstTotalQuanittyMaterial(id);
	}
	
	
}
