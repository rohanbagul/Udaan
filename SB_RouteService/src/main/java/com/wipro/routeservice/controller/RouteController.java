package com.wipro.routeservice.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;
import com.wipro.routeservice.service.RouteService;

@RestController
@RequestMapping("/api/v2")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	
	@PostMapping("/addRoute")
	public ResponseEntity<?> addRoute(@RequestBody RouteDTO route){
		
		return new ResponseEntity<>(routeService.addRoute(route),HttpStatus.OK);
	}
	
	@GetMapping("/getRoute")
	public ResponseEntity<List<Route>> getRoute(){
		return ResponseEntity.ok(routeService.getRoute());
	}
	
	@GetMapping("/getRouteByCode/{routeCode}")
	public ResponseEntity<Route> getRouteByCode(@PathVariable String routeCode){
		return ResponseEntity.ok(routeService.getRouteBycode(routeCode));
	}
	
	@GetMapping("/getRouteBySource/{source}")
	public ResponseEntity<List<Route>> getRouteBySource(@PathVariable String source){
		return ResponseEntity.ok(routeService.getRouteBySource(source));
	}
	
	@GetMapping("/getRouteBySrcAndDest/{source}/{destination}")
	public ResponseEntity<Route> getRouteBySourceAndDestination(@PathVariable String source, @PathVariable String destination){
		return ResponseEntity.ok(routeService.getRouteBySourceAndDestination(source, destination));
	}
	
	@DeleteMapping("/deleteroute/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		
		routeService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
