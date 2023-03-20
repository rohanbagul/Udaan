package com.wipro.springsecurity.controller;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.springsecurity.request.AuthenticationRequest;
import com.wipro.springsecurity.request.AuthenticationResponse;
import com.wipro.springsecurity.request.RegisterRequest;
import com.wipro.springsecurity.request.UpdateRequest;
import com.wipro.springsecurity.service.AuthenticationService;
import com.wipro.springsecurity.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service;
	private final UserService uservice;
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(service.register(request));
	}
	
	@PutMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(service.changePassword(request));
	}
	
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<?> getbyid(@PathVariable Integer id){
		
		return ResponseEntity.ok(service.getUserById(id));
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> deletbyid(@PathVariable Integer id){
		return ResponseEntity.ok(service.deletebyid(id));
	}
	
	@PutMapping("/updateprofile/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable Integer id,@RequestBody UpdateRequest user){
		return ResponseEntity.ok(uservice.updateUser(id,user));
	}
	
	@PutMapping("/logout/{id}")
	public ResponseEntity<?> signout(@PathVariable Integer id){
		return ResponseEntity.ok(service.signout(id));
	}
}
