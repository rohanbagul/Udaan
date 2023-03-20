package com.wipro.scheduleservices.controller;

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

import com.wipro.scheduleservices.dto.ScheduleDTO;
import com.wipro.scheduleservices.dto.UpdateSeats;
import com.wipro.scheduleservices.entities.Schedule;
import com.wipro.scheduleservices.service.ScheduleService;

@RestController
@RequestMapping("/scheduleService")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/addSchedule")
	public ResponseEntity<?> addSchedule(@RequestBody ScheduleDTO scheduledto) {

		return new ResponseEntity<>(scheduleService.addSchedule(scheduledto), HttpStatus.OK);
	}

	@GetMapping("/getSchedule")
	public ResponseEntity<List<Schedule>> getSchedule() {

		return new ResponseEntity<>(scheduleService.getAllSchedule(), HttpStatus.OK);
	}
	
	@GetMapping("/getSchedById/{scheduleId}")
	public ResponseEntity<Schedule> getScheduleById(@PathVariable int scheduleId) {

		return new ResponseEntity<>(scheduleService.getScheduleById(scheduleId), HttpStatus.OK);
	}
	
	
	@PutMapping("/updateSchedule/{scheduleId}")
	public ResponseEntity<?> updateSchedule(@PathVariable int scheduleId,@RequestBody ScheduleDTO scheduledto) {

		return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId, scheduledto), HttpStatus.OK);
	}
	
	@PostMapping("/updateSeatData/{scheduleId}")
	public ResponseEntity<?> updateSeatData(@PathVariable int scheduleId,@RequestBody UpdateSeats updateseats) {

		return new ResponseEntity<>(scheduleService.updateSeatData(scheduleId, updateseats), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSchedule/{scheduleId}")
	public ResponseEntity<?> deleteSchedule(@PathVariable int scheduleId){
		
		scheduleService.deleteSchedule(scheduleId);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	

	@GetMapping("/getSchedByFlightNum/{flightNumber}")
	public ResponseEntity<List<Schedule>> getScheduleByFlightNum(@PathVariable String flightNumber) {

		return new ResponseEntity<>(scheduleService.getSchedByFlightNum(flightNumber), HttpStatus.OK);
	}

	@GetMapping("/getSchedByFlightName/{flightName}")
	public ResponseEntity<List<Schedule>> getScheduleByFlightName(@PathVariable String flightName) {

		return new ResponseEntity<>(scheduleService.getSchedByFlightName(flightName), HttpStatus.OK);
	}

	@GetMapping("/getSchedByLeaveAndArrLoc/{leavingLocation}/{arrivalLocation}")
	public ResponseEntity<List<Schedule>> getScheduleByLeaveAndArrLoc(@PathVariable String leavingLocation, @PathVariable String arrivalLocation) {

		return new ResponseEntity<>(scheduleService.getSchedByleaveLocAndArrLoc(leavingLocation, arrivalLocation), HttpStatus.OK);
	}
	
	@GetMapping("/getSchedByArrTime/{arrivalTime}")
	public ResponseEntity<List<Schedule>> getSchedByArrTime(@PathVariable String arrivalTime) {

		return new ResponseEntity<>(scheduleService.getSchedByArrTime(arrivalTime), HttpStatus.OK);
	}
	
	@GetMapping("/getSchedByDeptTime/{departureTime}")
	public ResponseEntity<List<Schedule>> getSchedByDepTime(@PathVariable String departureTime) {

		return new ResponseEntity<>(scheduleService.getSchedByDepTime(departureTime), HttpStatus.OK);
	}
	
	@GetMapping("/getSchedByLeaveLoc/{leavingLocation}")
	public ResponseEntity<List<Schedule>> getSchedByLeaveLoc(@PathVariable String leavingLocation) {

		return new ResponseEntity<>(scheduleService.getSchedByLeaveLoc(leavingLocation), HttpStatus.OK);
	}
	
	@GetMapping("/getSchedByArrLoc/{arrivalLocation}")
	public ResponseEntity<List<Schedule>> getSchedByArrLoc(@PathVariable String arrivalLocation) {

		return new ResponseEntity<>(scheduleService.getSchedByArrLoc(arrivalLocation), HttpStatus.OK);
	}
	
	
	
	
}
