package com.nguyenquockhanh.restaurant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.dao.MaterialReportDao;
import com.nguyenquockhanh.restaurant.entity.ReportMaterialModel;

@Service
@Transactional
public class MaterialReportServiceImpl implements MaterialReportService {

	@Autowired
	MaterialReportDao dao;

	@Override
	public List<ReportMaterialModel> spGMaterialReport() {
		// TODO Auto-generated method stub
		return dao.spGMaterialReport();
	}

	
	
	
}
