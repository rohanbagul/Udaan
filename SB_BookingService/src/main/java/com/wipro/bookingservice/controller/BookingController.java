package com.wipro.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bookingservice.dto.BookingDTO;
import com.wipro.bookingservice.entities.Booking;
import com.wipro.bookingservice.service.BookingService;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@PostMapping("/createBooking")
	public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingdto) {

		return new ResponseEntity<>(bookingService.createBooking(bookingdto), HttpStatus.CREATED);
	}
	
	@GetMapping("/getBookings")
	public ResponseEntity<?> getBookings() {

		return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
	}
	
	@GetMapping("/getBookingsById/{bookingId}")
	public ResponseEntity<?> getBookingByBookingId(@PathVariable String bookingId) {

		return new ResponseEntity<>(bookingService.getBookingByBookingId(bookingId), HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelBooking/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable int id){
		
		bookingService.deleteBooking(id);
		
		return new ResponseEntity<>("Flight Canceld", HttpStatus.OK);
	}
	
	@PutMapping("/updateBooking/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable int id,@RequestBody BookingDTO bookingdto) {

		return new ResponseEntity<>(bookingService.updateBooking(id, bookingdto), HttpStatus.CREATED);
	}
	
	@GetMapping("/getBookingByUserId/{userId}")
	public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable int userId){
		
		return new ResponseEntity<>(bookingService.getBookingsByUserId(userId), HttpStatus.OK);
	}
	
	
}
