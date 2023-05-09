package com.nguyenquockhanh.restaurant.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenquockhanh.restaurant.entity.Category;
import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.service.CategoryService;

@Controller
public class CategoryController extends BaseController {

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = { "categories/create" })
	public String showAddMaterialPage(Model model) {
		Material material = new Material();
		model.addAttribute("material", material);
		return "addCategory";
	}
	
	@GetMapping(value = { "/categories/detail" })
	public ResponseEntity<Category> getDetail(ModelMap model,Material material,
			@RequestParam int id,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		Category category  = categoryService.findOne(id);

		model.addAttribute("categoryOff", category);

		return ResponseEntity.ok().body(category);

	}
	
	@PostMapping(value = { "categories/create" })
	public String addCategory(Model model,@RequestHeader(value = "Authorization") String token,
			@RequestParam String name,
			@RequestParam String description,
			@RequestParam int sort
			) throws Exception {
		
		Category category = new Category();
		category.setName(name);
		category.setCode(name.trim().toLowerCase());
		category.setUserId(this.AccessToken(token).getId());
		category.setRestaurantId(this.AccessToken(token).getRestaurantId());
		category.setBranchId(this.AccessToken(token).getRestaurantBrandId());
		category.setBranchId(this.AccessToken(token).getBranchId());
		category.setPrefixName(category.getName().trim().toUpperCase());
		category.setNormalizeName(category.getName().trim().toLowerCase());
		category.setDescription(description);
		category.setSort(sort);
		category.setStatus(1);

		categoryService.create(category);

		model.addAttribute("category", category);
		return "addCategory";
	}

	
	@GetMapping(value = { "categories/list/to-map-view" })
	public String findAllCategory(ModelMap model,Material material,@RequestHeader(value = "Authorization") String token) throws Exception {

		List<Category> customersOn = categoryService.findAll(this.AccessToken(token).getRestaurantId());
		List<Category> customersOff = categoryService.findByStatus(this.AccessToken(token).getRestaurantId());

		model.addAttribute("categoryOn", customersOn);
		model.addAttribute("categoryOff", customersOff);

		return "categoryList";
	}

	@GetMapping(value = { "categories/list" })
	public ResponseEntity<List<Category>> findAllCategoryResponse(Material material,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		List<Category> categoryOn = categoryService.findAll(this.AccessToken(token).getRestaurantId());

		return ResponseEntity.ok().body(categoryOn);
	}

	
	@GetMapping(value = { "categories/{id}/change-status" })
	public String changeStatusCustomer(@PathVariable("id") int id, Model model) {
		Category category  = categoryService.findOne(id);
		model.addAttribute("CategoryChanngeStatus", category);
		return "categoryList";
	}

	@PostMapping(value = { "categories/{id}/change-status" })
	public String updateStatusCustomer(@PathVariable("id") int id, Model model) {
		Category category  = categoryService.findOne(id);
		if (category.getStatus() == 1) {
			category.setStatus(0);	
		} else {
			category.setStatus(1);
		};
		
		categoryService.update(category);
		model.addAttribute("CategoryChanngeStatus", category);
		return "redirect:/api/categories/list";
	}
	

	@GetMapping(value = { "categories/{id}/update" })
	public String updateInforCustomer(@PathVariable("id") int id, Model model) {
		Category category  = categoryService.findOne(id);
		model.addAttribute("categoryUpdateInfor", category);
		return "categoryMaterialInfor";
	}

	@PostMapping(value = { "categories/{id}/update" })
	public String updateInforCustomers(@PathVariable("id") int id, Model model,
			@ModelAttribute("categoryUpdateInfor") Material material,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		Category categoryUpdate  = categoryService.findOne(id);
		categoryUpdate.getName();
		categoryUpdate.getCode();
		categoryUpdate.setRestaurantId(this.AccessToken(token).getRestaurantId());
		categoryUpdate.setBranchId(this.AccessToken(token).getRestaurantBrandId());
		categoryUpdate.setBranchId(this.AccessToken(token).getBranchId());
		categoryUpdate.getDescription();
		categoryUpdate.getSort();
		categoryUpdate.setStatus(1);

		return "redirect:/categories/list";
	}

	
}
