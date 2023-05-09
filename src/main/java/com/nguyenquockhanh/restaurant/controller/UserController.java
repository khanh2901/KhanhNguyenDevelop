package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.entity.UserModelAccessToken;
import com.nguyenquockhanh.restaurant.service.UserService;
import com.nguyenquockhanh.restaurant.utils.Utils;

@Controller
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	ServletContext servletContext;

	@GetMapping(value = { "/user-list" })
	public String findAllUser(ModelMap model, User user) {

		List<User> customersOn = userService.findAll();
		List<User> customersOff = userService.findByStatus();

		model.addAttribute("UserOn", customersOn);
		model.addAttribute("UserOff", customersOff);

		return "userList";
	}

	@GetMapping("/headerEmployee")
	public String showLoginForm() {
		return "HeaderForCustomer";
	}

	@GetMapping(value = { "/profile-fage" })
	public ResponseEntity<User> profile(ModelMap model, @RequestParam int id) {

		User user = userService.findOne(id);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping(value = { "/user/create" })
	public String showAddUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "addUser";
	}

	@PostMapping(value = { "/user/create" })
	public String showUserPage(Model model, @ModelAttribute("user") User user, @RequestParam String firstName,
			@RequestParam String LastName, @RequestParam int genDer, @RequestParam String birthDay,
			@RequestParam String phone, @RequestParam String username, @RequestParam String email,

			BindingResult result, @RequestParam int role, @RequestParam String password) throws Exception{

		List<User> userList = userService.findAllUsers();

		boolean isPhoneNumberExist = userList.stream().anyMatch(x -> x.get_phone().equals(phone));
		if (isPhoneNumberExist) {
			model.addAttribute("errorMessage", "Số điện thoại đã tồn tại!");
			return "redirect:/user-list";
		}

		user.setFirstName(firstName);
		user.setLastName(LastName);
		user.setFullName(user.getFirstName().concat(" ").concat(user.getLastName()));
		user.setRestaurantId(1);
		user.setRestaurantBrandId(1);
		user.setBranchId(1);
		user.setGenDer(genDer);
		user.setEmail(email);
		user.set_phone(phone);
		user.setUser_name(username);
		user.setRole(role);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setAcessToken(Utils.encodeBase64String(
				Utils.convertObjectToJsonString(new UserModelAccessToken(userList.get(0).getId() + 1, user, role))));
		user.setFbUId("facebook");
		user.setApplUID("apple");
		user.setGgUId("google");
		if (genDer == 1) {
			user.setAvatar("avtfm1.jpg");
		} else {
			user.setAvatar("avfm1.jpg");
		}
		user.setStatus(1);
		userService.create(user);


			return "redirect:/user-list";
		
	}
//==============================================================

	@GetMapping(value = { "user/{id}/change-status" })
	public String changeStatusCustomer(@PathVariable("id") int id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("UserChangeStatus", user);
		return "userList";
	}

	@GetMapping(value = { "/register-customer" })
	public String registerCustomer() {

		return "RegisterCustomer";
	}

	@PostMapping(value = { "user/{id}/change-status" })
	public String updateStatusCustomer(@PathVariable("id") int id, Model model) {
		User user = userService.findOne(id);
		if (user.getStatus() == 1) {
			user.setStatus(0);
		} else {
			user.setStatus(1);
		}
		;
		userService.update(user);
		model.addAttribute("UserChangeStatus", user);
		return "redirect:/user-list";
	}

	@GetMapping(value = { "user/{id}/update" })
	public ResponseEntity<User> updateInforCustomer(@PathVariable("id") int id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("UserUpdateInfor", user);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping(value = { "user/{id}/update" })
	public ResponseEntity<User> updateInforCustomers(@PathVariable("id") int id, Model model,
			@ModelAttribute("customerUpdateInfor") User user, @RequestParam String firstName,
			@RequestParam String LastName, @RequestParam(required = false, defaultValue = "1") int genDer,
			@RequestParam String phone, @RequestParam String email,
			@RequestParam(required = false, defaultValue = "0") int role) {
		User userUpdate = userService.findOne(id);

		User userCheck = userService.findByPhone(phone);
		System.out.println(userCheck);

		if (userCheck != null) {

			return ResponseEntity.ok().body(new User());
		}
		if (firstName.equals("") == true && LastName.equals("") == true) {
			firstName = userUpdate.getFirstName();
			LastName = userUpdate.getLastName();
		}

		if (role == 0) {
			role = userUpdate.getRole();

		}
		if (phone.equals("") == true) {
			phone = userUpdate.get_phone();

		}
		if (email.equals("") == true) {
			email = userUpdate.getEmail();

		}
		userUpdate.setFirstName(firstName);
		userUpdate.setLastName(LastName);
		userUpdate.setFullName(firstName.concat(" ").concat(LastName));
		userUpdate.setGenDer(genDer);
		userUpdate.setEmail(email);
		userUpdate.setRole(role);
		userUpdate.set_phone(phone);
		userService.update(userUpdate);

		return ResponseEntity.ok().body(userUpdate);
	}

	@PostMapping("user/{id}/update-image")
	public String update(Model model, @PathVariable("id") int id, @RequestParam("image") MultipartFile image)
			throws IOException {
		try {
			byte[] bytes = image.getBytes();
			Path path = Paths.get("D:\\SOURCEJAVA\\RestaurantManager\\src\\main\\resources\\static\\img"
					+ image.getOriginalFilename());
			Files.write(path, bytes);

			User userUpdate = userService.findOne(id);

			userUpdate.setAvatar(image.getOriginalFilename());

			model.addAttribute("userUpdateImg", userUpdate);

			userService.update(userUpdate);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/user-list";
	}

}
