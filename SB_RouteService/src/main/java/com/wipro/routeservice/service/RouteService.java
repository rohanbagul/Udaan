package com.wipro.routeservice.service;

import java.util.List;

import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;

public interface RouteService {
	
	public Route addRoute(RouteDTO route);
	
	public List<Route> getRoute();
	public Route getRouteBycode(String routeCode);
	public Route getRouteBySourceAndDestination(String source, String route);
	public List<Route> getRouteBySource(String source);
	public void deleteById(int id);

	

}