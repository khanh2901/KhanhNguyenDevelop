package com.nguyenquockhanh.restaurant.controller;

import java.security.Key;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.service.UserService;

public class BaseController {
	@SuppressWarnings("unused")
	@Autowired
	private UserService userService;

    static Cipher cipher;  

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'T', 'E', 'S', 'T' };


	public User AccessToken(String encodeString) throws Exception {

		byte[] decodedBytes = Base64.getMimeDecoder().decode(encodeString);
		String decodedMime = new String(decodedBytes);

		ObjectMapper mapper = new ObjectMapper();
		try {
			User map = mapper.readValue(decodedMime, User.class);

			User user = userService.findOne(map.getId());

			if (user != null)
				return user;
			else
				throw new Exception();

		} catch (Exception e) {

			throw new Exception("Sai token");

		}

	}

	public static String endCrypt(String password) {
		String salt = BCrypt.gensalt(12);

		String hashedPassword = BCrypt.hashpw(password, salt);

		return hashedPassword;
	}
}
