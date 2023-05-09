package com.nguyenquockhanh.restaurant.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nguyenquockhanh.restaurant.service.UserService;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private UserService userService;

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = authResult.getName();
		HttpSession session = request.getSession();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String accessToken = userService.findByUserName(username).getAcessToken();

		session.setAttribute("accesstoken", accessToken);
		
		super.successfulAuthentication(request, response, chain, authResult);

	}
}
