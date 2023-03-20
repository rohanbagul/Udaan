package com.wipro.flightservices.controller;

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


import com.wipro.flightservices.dto.FlightDTO;
import com.wipro.flightservices.entities.Flight;
import com.wipro.flightservices.service.FlightService;


@RestController
@RequestMapping("/flightservice")
public class FlightController {

	@Autowired
	private FlightService flightService;
		
	
	@PostMapping("/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightdto){
		return ResponseEntity.ok(flightService.addFlight(flightdto));
	}
	
	@GetMapping("/getFlight")
	public ResponseEntity<List<Flight>> getFlights(){
		return ResponseEntity.ok(flightService.getAllFlights());
	}
	
	@GetMapping("/getFlight/{flightID}")
	public ResponseEntity<Flight> getFlightById(@PathVariable Integer flightID){
		return ResponseEntity.ok(flightService.getByFlightId(flightID));
	}
	
	@GetMapping("/getFlightByNum/{flightNumber}")
	public ResponseEntity<Flight> getFlightByFlightNum(@PathVariable String flightNumber){
		return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
	}
	
	
	@DeleteMapping("/deleteFlight/{flightID}")
	public ResponseEntity<?> deleteFlight(@PathVariable Integer flightID){
		
		flightService.deleteFlight(flightID);
		
		return new ResponseEntity<>("Flight Data Deleted",HttpStatus.OK);
	}
	
	@PutMapping("/updateFlight/{flightID}")
	public ResponseEntity<Flight> updateFlight(@PathVariable Integer flightID, @RequestBody FlightDTO flightdto){
		Flight updatedflight = flightService.updateFlight(flightID, flightdto);
		
		return ResponseEntity.ok(updatedflight);
	}
		
	
}
