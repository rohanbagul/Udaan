package com.wipro.routeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
	
	public Route findBySourceAndDestination(String source, String destination);
	
	public Route findByRouteCode(String routeCode);
	
	public List<Route> findBySource(String source);
	

}
