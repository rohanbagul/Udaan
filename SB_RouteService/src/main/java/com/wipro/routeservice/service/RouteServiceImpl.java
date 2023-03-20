package com.wipro.routeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;
import com.wipro.routeservice.exception.CustomException;
import com.wipro.routeservice.exception.NotFoundException;
import com.wipro.routeservice.repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepo;

	@Override
	public Route addRoute(RouteDTO route) {

		Route foundRoute = routeRepo.findByRouteCode(route.getRouteCode());
		Route routes = new Route();

		if (foundRoute == null) {

			routes.setRouteCode(route.getRouteCode());
			routes.setSource(route.getSource());
			routes.setDestination(route.getDestination());
			routeRepo.save(routes);
		} else {

			throw new CustomException("Data Can not be saved !!");
		}

		return routes;
	}

	@Override
	public List<Route> getRoute() {
		return routeRepo.findAll();
	}

	@Override
	public Route getRouteBycode(String routeCode) {
		Route getRoute = routeRepo.findByRouteCode(routeCode);
		if (getRoute != null) {

			return getRoute;
		} else {
			throw new NotFoundException("No Data Found !!");
		}

	}

	@Override
	public Route getRouteBySourceAndDestination(String source, String route) {

		Route getRoute = routeRepo.findBySourceAndDestination(source, route);

		if (getRoute != null) {
			return getRoute;
		} else {
			throw new NotFoundException("No Data Found !!");
		}
	}

	@Override
	public List<Route> getRouteBySource(String source) {

		List<Route> getList = routeRepo.findBySource(source);
		
		if(getList != null) {
			return getList;
		}
		else {	
			throw new CustomException("No Source Data Found !!");
		}	 
	}

	@Override
	public void deleteById(int id) {

		Route getRoute = routeRepo.findById(id).orElseThrow(()-> new CustomException("Route data not available to delete !!"));
		
		if (getRoute != null) {	
			routeRepo.deleteById(id);
		}	
	}

}
