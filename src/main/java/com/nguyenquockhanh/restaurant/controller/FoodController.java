package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenquockhanh.restaurant.entity.BillModel;
import com.nguyenquockhanh.restaurant.entity.Cart;
import com.nguyenquockhanh.restaurant.entity.Food;
import com.nguyenquockhanh.restaurant.entity.Unit;
import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.service.FoodService;
import com.nguyenquockhanh.restaurant.service.WarehouseSessionService;
import com.nguyenquockhanh.restaurant.utils.Utils;

@Controller
public class FoodController extends BaseController {

	@Autowired
	WarehouseSessionService warehouseSessionService;

	@Autowired
	FoodService foodService;

	@GetMapping(value = { "/food" })
	public String foodView(ModelMap model) {

		return "food";
	}
	
	@GetMapping(value = { "/food-sale" })
	public String foodSale(ModelMap model) {

		return "Salefood";
	}

	@PostMapping(value = { "/create/food" })
	public ResponseEntity<Object> importMaterial(ModelMap model, @RequestParam String materials,
			@RequestParam(required = false, defaultValue = "0") int vat,
			@RequestParam(required = false, defaultValue = "0") int categoryType,
			@RequestParam(required = false, defaultValue = "0") int discountPercent,
			@RequestParam(required = false, defaultValue = "0") int discountAmount,
			@RequestParam(required = false, defaultValue = "") String avatar,
			@RequestParam(required = false, defaultValue = "1233803.png") String foodName,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		
			if(discountAmount > 0 &&  discountPercent > 0) {

				return ResponseEntity.ok().body("{\r\n"
						+ "    \"err\": \"Chỉ được sử dụng một trong hai hình thức giảm giá!\"\r\n"
						+ "}");
				
			}
			if(avatar.isEmpty()) {
				avatar = "1233803.png";
			}
		
		warehouseSessionService.spCreateFood(this.AccessToken(token).getId(), materials, vat, categoryType,
				discountPercent, discountAmount, foodName, avatar);
	
		return ResponseEntity.ok().body("{\r\n"
				+ "    \"success\": \"ok\"\r\n"
				+ "}");
	}

	@GetMapping(value = { "food/list/map-to-view" })
	public String findAllFood() throws Exception {

		return "foodList";
	}
	
	@GetMapping(value = { "food/list/map-to-view/employee" })
	public String findAllFoodEmploye() throws Exception {

		return "FoodListForEmployee";
	}
	
	@GetMapping(value = { "food/list/pause/map-to-view" })
	public String findAllFoodPause() throws Exception {

		return "ListFoodPause";
	}
	
	@GetMapping(value = { "/cart" })
	public String cart() throws Exception {

		return "Cart";
	}

	@GetMapping(value = { "food/list" })
	public ResponseEntity<List<Food>> findAllFood(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token) throws Exception {
		List<Food> foodOn = foodService.findAll(this.AccessToken(token).getRestaurantId());
		
		
		return ResponseEntity.ok().body(foodOn);
	}@GetMapping(value = { "food/list/pause" })
	public ResponseEntity<List<Food>> findAllFoodPause(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token) throws Exception {
		List<Food> foodOff = foodService.findAllFoodPause(this.AccessToken(token).getRestaurantId());
		return ResponseEntity.ok().body(foodOff);
	}
	
	

	@PostMapping("food/{id}/update-image")
	public String update(Model model, @PathVariable("id") int id, @RequestParam("image") MultipartFile image)
			throws IOException {
		try {
			byte[] bytes = image.getBytes();
			Path path = Paths.get("D:\\SOURCEJAVA\\RestaurantManager\\src\\main\\resources\\static\\img"
					+ image.getOriginalFilename());
			Files.write(path, bytes);

			Food food = foodService.findOne(id);

			food.setAvatar(image.getOriginalFilename());

			model.addAttribute("userUpdateImg", food);

			foodService.update(food);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/food/list/to-map-view";
	}

	@PostMapping(value = { "food/{id}/change-status" })
	public String updateStatusCustomer(@PathVariable("id") int id, Model model) {
		Food food = foodService.findOne(id);
		if (food.getStatus() == 1) {
			food.setStatus(0);
		} else {
			food.setStatus(1);
		}
		;
		foodService.update(food);
		return "foodList";
	}

	@PostMapping(value = { "/sale-food" })
	public void saleFood(Model model,
			@RequestParam(required = false, defaultValue = "0") int quantity,
			@RequestParam(required = false, defaultValue = "0")  String foodIds,
			@RequestParam(required = false, defaultValue = "") String description,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		System.out.println(foodIds);
		foodService.saleFood(this.AccessToken(token).getRestaurantId(),
				this.AccessToken(token).getId(), quantity, foodIds, description);
	};
	
	@PostMapping(value = { "/add-to-cart" })
	public void addToCart(Model model,
			@RequestParam(required = false, defaultValue = "0") String img,
			@RequestParam(required = false, defaultValue = "0") int foodId,
			@RequestParam(required = false, defaultValue = "0") int quantity,
			@RequestParam(required = false, defaultValue = "0")  String foodName,
			@RequestParam(required = false, defaultValue = "0")  int price,
			@RequestParam(required = false, defaultValue = "") String description,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		Cart cart1 = foodService.findOneCart(foodId,this.AccessToken(token).getId());
		System.out.println(cart1);
		if(cart1 != null) {
			
			cart1.setQuantity(cart1.getQuantity()+ quantity);
			cart1.setPrice(price * (cart1.getQuantity()+ quantity));
			foodService.update(cart1);
		}else {
		
		Cart cart = new Cart();
		cart.setRestaurantId(this.AccessToken(token).getRestaurantId());
		cart.setAvatar(img);
		cart.setRestaurantBrandId(this.AccessToken(token).getRestaurantBrandId());
		cart.setBranchId(this.AccessToken(token).getBranchId());
		cart.setUserId(this.AccessToken(token).getId());
		cart.setFoodId(foodId);
		cart.setFoodName(foodName);
		cart.setQuantity(quantity);
		cart.setPrice(quantity * price);
		cart.getDescription();
		cart.setStatus(1);
		 foodService.create(cart);
		}
		
		
	};
	
	@PostMapping(value = { "cart/{id}/delete" })
	public void deleteCart(@PathVariable("id") int id, Model model) {
		Cart cart = foodService.findOneCart1(id);
		
		foodService.delete(cart);
	}

	@GetMapping(value = { "/list-cart" })
	public ResponseEntity<List<Cart>> findAllCart(ModelMap model,
			@RequestHeader(value = "Authorization") String token) throws Exception {
		List<Cart> cart = foodService.findAllCart(
				this.AccessToken(token).getRestaurantId(),
				this.AccessToken(token).getId());
		return ResponseEntity.ok().body(cart);
	}

	@PostMapping(value = { "/success-bill" })
	public void succesBill(@RequestHeader(value = "Authorization") String token, Model model) throws Exception {
		
		BillModel billModel = foodService.spSuccessBill(
				this.AccessToken(token).getId(),
				this.AccessToken(token).getRestaurantId()
				);
	
		String foodIds = billModel.getFoodIds();
		foodService.saleFood(this.AccessToken(token).getRestaurantId(), 
				this.AccessToken(token).getId(), billModel.getQuantity(), foodIds,"abc");
		
//		foodService.saleFood(1, 
//				169,6, "[40,43]", "abc");
	}
	
	
	void saleFood(int restaurantId, int userId,int quantity, String foodIds) {
		foodService.saleFood(restaurantId, 
				userId,quantity, foodIds, "abc");
	}
	
	@PostMapping(value = { "/test" })
	public void test(Model model) {
		foodService.saleFood(1, 
				169,6, "[40,43]", "abc");
	}
	
}
