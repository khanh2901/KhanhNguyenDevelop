package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.Unit;
import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.service.MaterialService;
import com.nguyenquockhanh.restaurant.utils.Utils;

@Controller
public class MaterialController extends BaseController {

	@Autowired
	MaterialService materialService;

	@GetMapping(value = { "materials/list/to-map-view" })
	public String findAllMaterial(ModelMap model, Material material,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		List<Material> customersOn = materialService.findAll(this.AccessToken(token).getRestaurantId());
		List<Material> customersOff = materialService.findByStatus(this.AccessToken(token).getRestaurantId());

		model.addAttribute("MaterialOn", customersOn);
		model.addAttribute("MaterialOff", customersOff);

		return "materialList";
	}

	@GetMapping(value = { "material/list" })
	public ResponseEntity<List<Material>> findAllUnit(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token) throws Exception {
		List<Material> unitOn = materialService.findAll(this.AccessToken(token).getRestaurantId());
		return ResponseEntity.ok().body(unitOn);
	}

	@GetMapping(value = { "materials/create" })
	public String createMaterial(Model model) {
		Material material = new Material();
		model.addAttribute("material", material);
		return "addMaterial";
	}

	@PostMapping(value = { "materials/create" })
	public String createMaterial(ModelMap model, @ModelAttribute("material") Material material,
			@RequestParam String name, @RequestParam int categoryId, @RequestParam int unitId,
			@RequestParam(required = false, defaultValue = "0") BigDecimal wastageRate,
			@RequestParam(required = false, defaultValue = "0") BigDecimal outStockAlertQuantity,
			@RequestParam(required = false, defaultValue = "0") BigDecimal retailPrice,
			@RequestParam(required = false, defaultValue = "0") BigDecimal costPrice,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		material.setName(name);
		material.setCode(Utils.convertToCodeFormat(name).concat(Utils.createRandomCode(1)));
		material.setPrefixName(material.getName().trim().toUpperCase());
		material.setNormalizeName(material.getName().trim().toLowerCase());
		material.setRestaurantId(this.AccessToken(token).getRestaurantId());
		material.setRestaurantBrandId(this.AccessToken(token).getRestaurantBrandId());
		material.setUserId(this.AccessToken(token).getId());
		material.setCategoryId(categoryId);
		material.setUnitId(unitId);
		material.setWastageRate(wastageRate);
		material.setOutStockAlertQuantity(outStockAlertQuantity);
		material.setRetailPrice(retailPrice);
		material.setCostPrice(costPrice);
		material.setStatus(1);
		materialService.create(material);
		model.addAttribute("material", material);

		return "redirect:/materials/list";

	}

	@GetMapping(value = { "/materials/{id}/change-status" })
	public String changeStatusCustomer(@PathVariable("id") int id, Model model) {
		Material material = materialService.findOne(id);
		model.addAttribute("MaterialChanngeStatus", material);
		return "materialList";
	}

	@PostMapping(value = { "/materials/{id}/change-status" })
	public String updateStatusCustomer(@PathVariable("id") int id, Model model) {
		Material material = materialService.findOne(id);
		if (material.getStatus() == 1) {
			material.setStatus(0);
		} else {

			material.setStatus(1);
		}
		;
		materialService.update(material);
		model.addAttribute("materialChanngeStatus", material);
		return "materialList";
	}

	@GetMapping(value = { "/materials/{id}/update" })
	public String updateInforCustomer(@PathVariable("id") int id, Model model) {
		Material material = materialService.findOne(id);
		model.addAttribute("materialUpdateInfor", material);
		return "updateMaterialInfor";
	}

	@PostMapping(value = { "/materials/{id}/update" })
	public String updateInforCustomers(@PathVariable("id") int id, Model model,
			@ModelAttribute("updateMaterialInfor") Material material,
			@RequestHeader(value = "Authorization") String token) {
		Material materialUpdate = materialService.findOne(id);
		materialUpdate.setName(material.getName());
		materialUpdate.setCategoryId(material.getCategoryId());
		materialUpdate.setUnitId(material.getUnitId());
		materialUpdate.setWastageRate(material.getWastageRate());
		materialUpdate.setOutStockAlertQuantity(material.getOutStockAlertQuantity());
		materialUpdate.setRetailPrice(material.getRetailPrice());
		materialUpdate.setCostPrice(material.getCostPrice());
		return "redirect:/api/units/list";
	}

	@PostMapping("/materials/{id}/update-image")
	public String update(Model model, @PathVariable("id") int id, @RequestParam("image") MultipartFile image)
			throws IOException {
		try {
			byte[] bytes = image.getBytes();
			Path path = Paths.get("D:\\eclipse-workspace\\RestaurantManager\\src\\main\\resources\\static\\img"
					+ image.getOriginalFilename());
			Files.write(path, bytes);

			Material material = materialService.findOne(id);

			material.setAvatar(image.getOriginalFilename());

			model.addAttribute("materialUpdateImg", material);

			materialService.update(material);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/api/units/list";
	}

}
