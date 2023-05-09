package com.nguyenquockhanh.restaurant.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;
import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.response.EmployeeWorkingResponse;
import com.nguyenquockhanh.restaurant.service.CustomerService;
import com.nguyenquockhanh.restaurant.service.OrderService;

/**
 * 
 * @author quock
 *
 */
@Controller
public class EmployeeWorkingController extends BaseController {

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@GetMapping("/employee-work-view")
	public String overvVew() {
		return "EmployeeWorkinng";
	}

	@GetMapping("/assinn-job")
	public String jobAssign() {

		return "AssigeJobToEmployee";
	}

	@PostMapping("/create-job")
	public String createJob(@RequestParam(required = false, defaultValue = "0") int employeeId,
			@RequestParam(required = false, defaultValue = "") String employeeName,
			@RequestParam(required = false, defaultValue = "") String jobName,
			@RequestParam(required = false, defaultValue = "") String description,
			@RequestParam(required = false, defaultValue = "0") int isOrder,
			@RequestParam(required = false, defaultValue = "[]") String orderId,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		EmployeeWorking work = new EmployeeWorking();
		work.setRestaurantId(1);
		work.setRestaurantBrandId(1);
		work.setBranchId(1);
		work.setJobName(jobName);
		work.setEmployeeId(employeeId);
		work.setEmployeeName(employeeName);
		work.setJobAssignor(this.AccessToken(token).getFullName());
		work.setDescription(description);
		work.setTaskLevel(1);
		work.setCreated_at(formattedDateTime);
		work.setStatus(1);

		customerService.createWork(work);

		return "AssigeJobToEmployee";

	}

	@GetMapping(value = "/list-job", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<EmployeeWorking>> jobList() {

		return ResponseEntity.ok().body(customerService.findAllJob());
	}

	@GetMapping(value = "/list-job/done", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<EmployeeWorkingResponse>> jobListDonnne() {

		System.out.println(customerService.findAllJobDone().toString());

		List<EmployeeWorkingResponse> list = new EmployeeWorkingResponse().mapToList(customerService.findAllJobDone());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/list-job-by-status", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<EmployeeWorkingResponse>> job(
			@RequestParam(required = false, defaultValue = "0") int status,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		List<EmployeeWorkingResponse> list = new EmployeeWorkingResponse()
				.mapToList(customerService.findAllJobByStatus(status, this.AccessToken(token).getId()));

		return ResponseEntity.ok().body(list);
	}

	@PostMapping(value = { "/change-status/{id}", })
	public ResponseEntity<?> cancelBooking(ModelMap model, @PathVariable("id") int id, @RequestParam int status,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		EmployeeWorking work = customerService.findJobById(id);

		if (work.getOrders() != 0) {

			Order order = orderService.findOne(work.getOrders());
			order.setStatus(3);
			orderService.changStatus(order);
		}

		work.setStatus(status);

		customerService.updateWork(work);

		return ResponseEntity.ok().body(null);

	}

}
