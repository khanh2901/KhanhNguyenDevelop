package com.nguyenquockhanh.restaurant.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenquockhanh.restaurant.service.CustomerService;
import com.nguyenquockhanh.restaurant.service.UserService;

@Service
public class CustomerUsserDetailService implements UserDetailsService {

	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userService.findByUserName(username) == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (userService.findByUserName(username).getRole() == 1) {
			grantedAuthorities.add(new SimpleGrantedAuthority("OWNER"));

		} else if (userService.findByUserName(username).getRole() == 2) {
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

		} else if (userService.findByUserName(username).getRole() == 3) {
			grantedAuthorities.add(new SimpleGrantedAuthority("CUSTOMER"));

		} else {
			grantedAuthorities.add(new SimpleGrantedAuthority("MANAGER"));

		}
		return new org.springframework.security.core.userdetails.User(username,
				userService.findByUserName(username).getPassword(), grantedAuthorities);
	}

}
