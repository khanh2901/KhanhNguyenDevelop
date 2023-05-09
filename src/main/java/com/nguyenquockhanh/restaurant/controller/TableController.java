package com.nguyenquockhanh.restaurant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;

import com.nguyenquockhanh.restaurant.entity.Booking;
import com.nguyenquockhanh.restaurant.entity.Tables;
import com.nguyenquockhanh.restaurant.response.BookingListResponse;
import com.nguyenquockhanh.restaurant.service.TableService;

@Controller
public class TableController extends BaseController{

	@Autowired
	TableService tableService;
	
	@GetMapping(value = { "/booking", })
	public String index(ModelMap model) {

	    return "Booking";
	}
	
	
	@GetMapping(value = { "/booking-manager", })
	public String bookingManager(ModelMap model) {

	    return "BookingManager";
	}
	
	
	@GetMapping(value = { "/booking-manager/employee", })
	public String bookingManagerEmployee(ModelMap model) {

	    return "BookingManageEmployee";
	}
	
	
	@GetMapping(value = { "/table-manager", })
	public ResponseEntity<List<Tables>> tableManager(ModelMap model) {
		
		
		List<Tables> tables = tableService.findAllTable();
		
		

		return ResponseEntity.ok().body(tables);
	}
	
	
	@PostMapping(value = { "/create-booking" })
	public ResponseEntity<String>  importMaterial(ModelMap model,
			@RequestParam(required = false, defaultValue = "0") int slot,
			@RequestParam(required = false, defaultValue = "") String description,
			@RequestParam(required = false, defaultValue = "") String bookingTime,
			@RequestParam(required = false, defaultValue = "") String bookingTimeEnd,
			@RequestParam(required = false, defaultValue = "") String dateBooking,
			@RequestHeader(value = "Authorization") String token) throws Exception {

		Booking bookingFind= tableService.findOne(this.AccessToken(token).getId());
		if(bookingFind != null ) {
			
			if(bookingFind.getDateBooking().equals(dateBooking)) {
				String error = "Bạn đã đặt bàn trong ngày này rồi!" ;
				 return ResponseEntity.badRequest().body(error);
			}
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

		Date date1 = sdf1.parse(bookingTime);
		Date date2 = sdf2.parse(bookingTimeEnd);

		int result = date1.compareTo(date2);
		
		if(result  > 0) {
			String error = "Thời gian trả bàn không thể nhỏ hơn thời gian đặt bàn";
			 return ResponseEntity.badRequest().body(error);
		}
		if(bookingTime.equals(bookingTimeEnd) == true) {
			String error = "Thời gian trả bàn không thể bằng thời gian đặt bàn";
			 return ResponseEntity.badRequest().body(error);
		}
		Booking booking = new Booking();
			
		booking.setRestaurantId(1);
		booking.setRestaurantBrandId(1);
		booking.setBranchId(1);
		booking.setCustomerName(this.AccessToken(token).getFullName());
		booking.setCustomerPhone(this.AccessToken(token).get_phone());
		booking.setSlot(slot);
		booking.setDescription(token);
		booking.setDescription(description);
		booking.setStatus(1);
		booking.setBookingTime(bookingTime);
		booking.setBookingTimeEnd(bookingTimeEnd);
		booking.setDateBooking(dateBooking);
		booking.setUserId(this.AccessToken(token).getId());
		tableService.create(booking);

		 return ResponseEntity.ok().body("ok");

	}
	
	
	@GetMapping(value = { "/booking-list", })
	public ResponseEntity<List<BookingListResponse>> bookingLiss(ModelMap model,
			@RequestParam(required = false, defaultValue = "") String dateBooking
			) {

		List<Booking> bookings = tableService.spGBooking(1,1,1,dateBooking);
		List<BookingListResponse> bookingListResponse = new BookingListResponse().maptolist(bookings);
		
	    return ResponseEntity.ok().body(bookingListResponse);
	}

	@GetMapping(value = { "/booking-list-for-customer", })
	public ResponseEntity<List<Booking>> bookingLissCustomer(ModelMap model
			) {

		List<Booking> bookings = tableService.findAll();
		
	    return ResponseEntity.ok().body(bookings);
	}

	@PostMapping(value = { "/comfirm-booking/{id}", })
	public ResponseEntity<?> confirmBooking(ModelMap model,@PathVariable("id") int id,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		Booking bookings = tableService.findById(id);

		
		bookings.setStatus(2);
		tableService.update(bookings);
		
	    return ResponseEntity.ok().body(null);

	}
	
	
	@PostMapping(value = { "/cancel-booking/{id}", })
	public ResponseEntity<?> cancelBooking(ModelMap model,@PathVariable("id") int id,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		Booking bookings = tableService.findById(id);

		
		bookings.setStatus(3);
		tableService.update(bookings);
		
	    return ResponseEntity.ok().body(null);

	}
	

	@GetMapping(value = { "/booking-customer", })
	public ResponseEntity<Booking> bookingCustomer(ModelMap model,
			@RequestHeader(value = "Authorization") String token
			) throws Exception {

		Booking bookings = tableService.findOne(this.AccessToken(token).getId());
		
	    return ResponseEntity.badRequest().body(bookings);
	}
}
