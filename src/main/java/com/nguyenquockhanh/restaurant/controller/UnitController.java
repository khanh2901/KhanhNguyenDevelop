package com.nguyenquockhanh.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
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

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.nguyenquockhanh.restaurant.entity.Category;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.Unit;
import com.nguyenquockhanh.restaurant.service.UnitService;

@Controller
public class UnitController extends BaseController {

	@Autowired
	UnitService unitService;

	@GetMapping(value = { "/units/create" })
	public String showAddPersonPage(Model model) {
		Unit unit = new Unit();
		model.addAttribute("unit", unit);
		return "addUnit";
	}

	
	@PostMapping(value = { "/units/create" })
	public String createCustomer(ModelMap model, @ModelAttribute("unit") Unit unit,
			@RequestParam String name,
			@RequestParam String description,
			@RequestHeader(value = "Authorization") String token)	throws Exception {
		
		unit.setName(name);
		unit.setDesctiption(description);
		unit.setUserId(this.AccessToken(token).getId());
		unit.setRestaurantId(this.AccessToken(token).getRestaurantId());
		unit.setStatus(1);
		unitService.create(unit);
		
		model.addAttribute("unit", unit);

		return "addUnit";

	}
	 

	@GetMapping(value = { "unit/detail" })
	public ResponseEntity<Unit>  findUnit(ModelMap model, Unit unit,
			@RequestParam int id,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		
		Unit unitOn = unitService.findOne(id);


		return ResponseEntity.ok().body(unitOn);	
		}

	
	
	@GetMapping(value = { "units/list/to-map-view" })
	public String findAllUnitToView(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		
		List<Unit> unitOn = unitService.findAll(this.AccessToken(token).getRestaurantId());
		List<Unit> unitOff = unitService.findByStatus(this.AccessToken(token).getRestaurantId());

		model.addAttribute("unitOn", unitOn);
		model.addAttribute("unitOff", unitOff);

		return "unitList";
	}

	@GetMapping(value = { "units/list" })
	public ResponseEntity<List<Unit>>  findAllUnit(ModelMap model, Unit unit,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		
		List<Unit> unitOn = unitService.findAll(this.AccessToken(token).getRestaurantId());


		return ResponseEntity.ok().body(unitOn);	
		}

	
	@GetMapping(value = { "/units/{id}/change-status" })
	public String changeStatusCustomer(@PathVariable("id") int id, Model model) {
		Unit unit  = unitService.findOne(id);
		model.addAttribute("UnitChanngeStatus", unit);
		return "unitList";
	}

	@PostMapping(value = { "/units/{id}/change-status" })
	public String updateStatusCustomer(@PathVariable("id") int id, Model model) {
		 Unit unit  = unitService.findOne(id);
		if (unit.getStatus() == 1) {
			unit.setStatus(0);
		} else {

			unit.setStatus(1);
		}
		;
		unitService.update(unit);
		model.addAttribute("UnitChanngeStatus", unit);
		return "redirect:/api/units/list";
	}

	@GetMapping(value = { "/units/{id}/update" })
	public String updateInforCustomer(@PathVariable("id") int id, Model model) {
		Unit unit  = unitService.findOne(id);
		model.addAttribute("unitUpdateInfor", unit);
		return "updateCustomerInfor";
	}

	@PostMapping(value = { "/units/{id}/update" })
	public String updateInforCustomers(@PathVariable("id") int id, Model model,
			@ModelAttribute("unitUpdateInfor") Unit unit
			) {
		Unit unitUpdate  = unitService.findOne(id);
		unitUpdate.setName(unit.getName());
		unitUpdate.setDesctiption(unit.getDesctiption());
		unitUpdate.getRestaurantId();
		model.addAttribute("unitUpdateInfor", unitUpdate);
		return "redirect:/api/units/list";
	}

}
