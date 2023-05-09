package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.MaterialModel;
import com.nguyenquockhanh.restaurant.response.StoreProcedureListResult;

public interface MaterialService {

	
	public void create (Material  material);
	
	
	public void update (Material  material);
	
	
	public Material findOne (int id);
	
	
	public List<Material> findAll (int userId);
	
	
	public List<Material> findByStatus(int userId);


	public List<MaterialModel> getLisstTotalQuanittyMaterial(int id);




	
}
