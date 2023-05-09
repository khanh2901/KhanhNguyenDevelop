package com.nguyenquockhanh.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

	@GetMapping(value = { "/profile" })
	public String introduce(ModelMap model) {

		return "profile";
	}

	@GetMapping(value = { "/profile-customer" })
	public String introduceCustomer(ModelMap model) {

		return "CustomerProfile";
	}
	
	@GetMapping(value = { "/profile-employee" })
	public String profileEmployee(ModelMap model) {

		return "EmployeeProfile";
	}
}
