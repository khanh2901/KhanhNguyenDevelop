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

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.MaterialModel;
import com.nguyenquockhanh.restaurant.entity.Unit;
import com.nguyenquockhanh.restaurant.entity.WareHouseSessionDetail;
import com.nguyenquockhanh.restaurant.entity.WareHousesSession;
import com.nguyenquockhanh.restaurant.response.MaterialModelResponse;
import com.nguyenquockhanh.restaurant.response.StoreProcedureListResult;
import com.nguyenquockhanh.restaurant.service.MaterialService;
import com.nguyenquockhanh.restaurant.service.WarehouseSessionService;

@Controller
public class WareHouseController extends BaseController{
	
	@Autowired
	WarehouseSessionService  warehouseSessionService;
	
	@Autowired
	MaterialService materialService;


	@GetMapping(value = { "/warehouse-session" })
	public String introduce(ModelMap model) {

		return "warehouseMaterial";
	}

	@GetMapping(value = { "/warehouse-session/list/to-map-view" })
	public String WareHouseSessionList(ModelMap model) {

		return "wareHouseSessionList";
	}
	

	@GetMapping(value = { "/warehouse-session/list" })
	public ResponseEntity<List<WareHousesSession> > WareHouseSessionList(ModelMap model,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		List<WareHousesSession>  wareHousesSession = warehouseSessionService.
				findAll(this.AccessToken(token).getRestaurantId());
		
		return ResponseEntity.ok().body(wareHousesSession);	
	}
	
	@GetMapping(value = { "/materials/list" })
	public String findAllUser(ModelMap model,Material material,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		List<Material> customersOn = materialService.findAll(this.AccessToken(token).getId());
		List<Material> customersOff = materialService.findByStatus(this.AccessToken(token).getId());

		model.addAttribute("MaterialOn", customersOn);
		model.addAttribute("MaterialOff", customersOff);

		return "materialList";
	}
	
	
	
	@GetMapping(value = { "/materials/total-quantity/to-map-view" })
	public String totalQuantityToMapView() throws Exception {

		return "totalMaterial";	
	}
	
	
	
	@GetMapping(value = { "/materials/total-quantity" })
	public ResponseEntity<List<MaterialModelResponse> > totalQuantity(ModelMap model,Material material,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		List<MaterialModel> materialModel = materialService.getLisstTotalQuanittyMaterial(this.AccessToken(token).getId());

		model.addAttribute("MaterialOn", materialModel);

		
		return ResponseEntity.ok().body( new MaterialModelResponse().maptoList(materialModel));		
		}
	
	
	
	
	@GetMapping(value = { "/material/list/to-warehouse" })
	public ResponseEntity<List<Material>>  findAllMaterial(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		
		List<Material> unitOn = materialService.findAll(this.AccessToken(token).getRestaurantId());


		return ResponseEntity.ok().body(unitOn);	
		}

	@PostMapping("/materials/{id}/update-image/to-warehouse")
	public String update(Model model, @PathVariable("id") int id, @RequestParam("image") MultipartFile image)
			throws IOException {
		try {
			byte[] bytes = image.getBytes();
			Path path = Paths.get("D:\\SOURCEJAVA\\RestaurantManager\\src\\main\\resources\\static\\img"
					+ image.getOriginalFilename());
			Files.write(path, bytes);
			
			Material material   = materialService.findOne(id);
			
			material.setAvatar(image.getOriginalFilename());

			model.addAttribute("materialUpdateImg", material);

			materialService.update(material);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/api/units/list";
	}
	
	@GetMapping(value = { "/material-import" })
	public String materialImportMapView() throws Exception {

		return "importMaterial";	
		}
	
	@PostMapping(value = { "/material-import" })
	public String  importMaterial(ModelMap model,
			@RequestParam(required = false, defaultValue = "0") int discountPercent,
			@RequestParam(required = false, defaultValue = "0") int vat,
			@RequestParam(required = false, defaultValue = "0") int discountAmount,
			@RequestParam(required = false, defaultValue = "") String _description,
			@RequestParam String materials,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		System.out.println(discountPercent);
		System.out.println(vat);
		System.out.println(discountAmount);
		System.out.println(_description);
		System.out.println(materials);
		System.out.println(this.AccessToken(token).getId());


		warehouseSessionService.importMaterial(this.AccessToken(token).getId(), discountPercent,
				vat, discountAmount, _description,materials);

	return "importMaterial";
	}
	
	
	
	
	@GetMapping(value = { "/warehouse-detail-list/{id}" })
	public ResponseEntity<List<WareHouseSessionDetail>>  findAllWarehouseDetail(
			ModelMap model, 
			@PathVariable("id") int id
			) throws Exception {
		
		List<WareHouseSessionDetail> warehouseDetail = warehouseSessionService.findAllWareHouseDetail(id);


		return ResponseEntity.ok().body(warehouseDetail);	
		}

	
	
	
}
