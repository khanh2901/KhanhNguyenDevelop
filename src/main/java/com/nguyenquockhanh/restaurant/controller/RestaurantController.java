package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestaurantController {

	@GetMapping(value = { "/", })
	public String index(ModelMap model) {

		return "redirect:/success";
	}

	@RequestMapping("/success")
	public String loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {

		String role = authResult.getAuthorities().toString();

		if (role.contains("CUSTOMER")) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/customer-overview"));
		} else if (role.contains("USER")) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/employee-work-view"));
		} else {
			return "index";
		}
		return "index";
	}

}
