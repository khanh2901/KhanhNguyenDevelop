package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.entity.UserModelAccessToken;
import com.nguyenquockhanh.restaurant.service.CustomerService;
import com.nguyenquockhanh.restaurant.service.UserService;
import com.nguyenquockhanh.restaurant.utils.Utils;

/**
 * 
 * @author quock
 *
 */
@Controller
public class CustomerController extends BaseController {

	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;

	@Autowired
	ServletContext servletContext;

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/introduction")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "introduce";
    }
	
    
    @GetMapping("/customer-overview")
    public String overvVew() {
        return "CustomerOverView";
    }
    
    
	
	@GetMapping(value = { "/customer-list" })
	public String findAllCustomer(ModelMap model, User user) {
		List<User> customersOn = userService.findAllUser();

		model.addAttribute("CustomerOn", customersOn);

		return "customerList";
	}

	@GetMapping(value = "/employee-list" , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String>  findAllEmployee(ModelMap model, User user) {
		List<User> employee = userService.findAllUser();

		return ResponseEntity.ok().body(Utils.convertObjectToJson(employee));
	}
	
	@GetMapping(value = "/employee-list-v2" , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<String>  findAllEmployee2(ModelMap model, User user) {
		List<User> employee = userService.findAllEmloyee2();

		return ResponseEntity.ok().body(Utils.convertObjectToJson(employee));
	}

	
	@GetMapping(value = { "/create" })
	public String showAddUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addCustomer";
	}

	@PostMapping(value = { "/create" })
	public String showUserPage(Model model, @ModelAttribute("user") User user, @RequestParam String firstName,
			@RequestParam String LastName, @RequestParam int genDer, @RequestParam String birthDay,
			@RequestParam String phone, @RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestHeader(value = "Authorization") String token) throws Exception {

		List<User> userList = userService.findAll();
		boolean isPhoneNumberExist = userList.stream().anyMatch(x -> x.get_phone().equals(phone));
		if(isPhoneNumberExist) {
		    model.addAttribute("errorMessage", "Số điện thoại đã tồn tại!");
		    return "redirect:/user-list";
		}
		
		user.setFirstName(firstName);
		user.setLastName(LastName);
		user.setFullName(user.getFirstName().concat(" ").concat(user.getLastName()));
		user.setRestaurantId(this.AccessToken(token).getRestaurantId());
		user.setRestaurantBrandId(this.AccessToken(token).getRestaurantBrandId());
		user.setBranchId(this.AccessToken(token).getBranchId());
		user.setGenDer(genDer);
		user.setEmail(email);
		user.set_phone(phone);
		user.setUser_name(username);
		user.setRole(3);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setAcessToken(Utils.encodeBase64String(
				Utils.convertObjectToJsonString(new UserModelAccessToken(userList.get(0).getId() + 1, user,3))));
		user.setFbUId("facebook");
		user.setApplUID("apple");
		user.setGgUId("google");
		user.setAvatar("21321321");
		user.setStatus(1);
		userService.create(user);

		model.addAttribute("user", user);

		return "addCustomer";
	}

	@GetMapping(value = { "customer/{id}/change-status" })
	public String changeStatusCustomer(@PathVariable("id") int id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("UserChangeStatus", user);
		return "userList";
	}

	@PostMapping(value = { "customer/{id}/change-status" })
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
		return "redirect:/customer-list";
	}

	@GetMapping(value = { "customer/{id}/update" })
	public ResponseEntity<User> updateInforCustomer(@PathVariable("id") int id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("UserUpdateInfor", user);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping(value = { "customer/{id}/update" })
	public ResponseEntity<User> updateInforCustomers(@PathVariable("id") int id, Model model,
			@ModelAttribute("customerUpdateInfor") User user, @RequestParam String firstName,
			@RequestParam String LastName, @RequestParam(required = false, defaultValue = "1") int genDer,
			@RequestParam String phone, @RequestParam (required = false, defaultValue = "")String email,
			@RequestParam(required = false, defaultValue = "0") int role) {
		
				
		User userUpdate = userService.findOne(id);
		
		List<User> userList = userService.findAll();
		
		String phoneCheck = phone;
		
		System.out.println(phoneCheck);
		
		boolean isPhoneNumberExist = userList.stream().anyMatch(x -> x.get_phone().equals(phoneCheck));
		if(isPhoneNumberExist) {		    
		   return ResponseEntity.ok().body(userUpdate);
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

	@PostMapping("customer/{id}/update-image")
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

		return "redirect:/customer-list";
	}

}
