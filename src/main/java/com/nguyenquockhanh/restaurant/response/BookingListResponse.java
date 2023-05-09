package com.nguyenquockhanh.restaurant.response;

import java.util.List;
import java.util.stream.Collectors;

import com.nguyenquockhanh.restaurant.entity.Booking;

public class BookingListResponse {

	private int id;

	private String customerName;

	private String customerPhone;

	private int slot;

	private String description;

	private int status;

	private String bookingTime;

	private String bookingTimeEnd;

	private String dateBooking;

	public String getCustomerName() {
		return customerName;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getBookingTimeEnd() {
		return bookingTimeEnd;
	}

	public void setBookingTimeEnd(String bookingTimeEnd) {
		this.bookingTimeEnd = bookingTimeEnd;
	}

	public String getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}

	public BookingListResponse() {
	}

	public BookingListResponse(Booking b) {
		this.id = b.getId();
		this.customerName = b.getCustomerName();
		this.customerPhone = b.getCustomerPhone();
		this.slot = b.getSlot();
		this.description = b.getDescription();
		this.status = b.getStatus();
		this.bookingTime = b.getBookingTime();
		this.bookingTimeEnd = b.getBookingTimeEnd();
		this.dateBooking = b.getDateBooking();
	}

	public List<BookingListResponse> maptolist(List<Booking> e) {
		return e.stream().map(x -> new BookingListResponse(x)).collect(Collectors.toList());

	}

}
