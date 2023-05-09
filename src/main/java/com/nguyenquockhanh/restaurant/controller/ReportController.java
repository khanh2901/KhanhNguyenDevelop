package com.nguyenquockhanh.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nguyenquockhanh.restaurant.entity.ReportMaterialModel;
import com.nguyenquockhanh.restaurant.service.MaterialReportService;

@Controller
public class ReportController {

	@Autowired
	MaterialReportService materialReportService;
	
	@GetMapping(value = { "/material-report-view" })
	public String reportMarialView() {


		return "MaterialReport";
	}

	
	

	@GetMapping(value = { "/material-report" })
	public ResponseEntity<List<ReportMaterialModel>> introduce(ModelMap model) {

		List<ReportMaterialModel> list = materialReportService.spGMaterialReport();

		return ResponseEntity.ok().body(list);
	}

}
