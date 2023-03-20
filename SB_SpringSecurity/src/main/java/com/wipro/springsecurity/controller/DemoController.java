package com.wipro.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.springsecurity.entities.Booking;
import com.wipro.springsecurity.entities.User;
import com.wipro.springsecurity.service.UserService;


@RestController
@RequestMapping("/api/v1/user")
public class DemoController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getUser/{email}")
	public ResponseEntity<User> getUser(@PathVariable String email){
		
		return new ResponseEntity<>(userService.getUserDetails(email), HttpStatus.OK);
	}
	
	@GetMapping("/getMyBooking/{userId}")
	public ResponseEntity<List<Booking>> getBooking(@PathVariable int userId){
		
		return new ResponseEntity<>(userService.getBookings(userId), HttpStatus.OK);
	}
	
	
}
