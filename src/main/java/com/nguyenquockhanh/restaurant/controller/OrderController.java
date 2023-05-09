package com.nguyenquockhanh.restaurant.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;
import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.entity.OrderDetail;
import com.nguyenquockhanh.restaurant.service.CustomerService;
import com.nguyenquockhanh.restaurant.service.OrderService;

@Controller
public class OrderController extends BaseController {

	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@GetMapping("/order")
	public String showOrder() {
		return "Order";
	}

	@GetMapping("/order-customer")
	public String showOrderCustomer() {
		return "OrderForCustomer";
	}

	@GetMapping("/order-employee")
	public String showOrderEmployee() {
		return "OrderForEmployee";
	}

	@GetMapping("/order-list")
	public ResponseEntity<List<Order>> listOrder() {

		List<Order> order = orderService.findAll();

		return ResponseEntity.ok().body(order);
	}
	
	
	@GetMapping("/order-list-customer")
	public ResponseEntity<List<Order>> listOrderByCustomerId(
			@RequestHeader(value = "Authorization") String token
			) throws Exception {
		
	
		List<Order> order = orderService.findByCustomerId(this.AccessToken(token).getId());

		return ResponseEntity.ok().body(order);
	}
	
	

	@GetMapping("/order-detail-list")
	public ResponseEntity<List<OrderDetail>> listOrderDetail(@RequestParam int orderId

	) {
		System.out.println(orderId);

		List<OrderDetail> orderDetail = orderService.findAllOrderDetailByOrderId(orderId);

		return ResponseEntity.ok().body(orderDetail);
	}

	@PostMapping("/order-change-status/{id}")
	public ResponseEntity<Order> changeStatus(@PathVariable("id") int id,
			@RequestParam(required = false, defaultValue = "0") int employeeId,
			@RequestParam(required = false, defaultValue = "") String employeeName,
			@RequestParam(required = false, defaultValue = "") String jobName,
			@RequestParam(required = false, defaultValue = "") String description,
			@RequestParam(required = false, defaultValue = "0") int isOrder,
			@RequestParam(required = false, defaultValue = "[]") int orderId,
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
		work.setIsOrders(1);
		work.setOrders(orderId);

		customerService.createWork(work);

		Order order = orderService.findOne(id);
		order.setStatus(2);

		orderService.changStatus(order);

		return ResponseEntity.ok().body(order);
	}

}
