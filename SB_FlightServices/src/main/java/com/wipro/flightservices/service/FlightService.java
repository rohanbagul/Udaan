package com.wipro.flightservices.service;

import java.util.List;

import com.wipro.flightservices.dto.FlightDTO;
import com.wipro.flightservices.entities.Flight;

public interface FlightService {

	public Flight addFlight(FlightDTO flightdto);
	
	public Flight getByFlightId(int flightId);
	
	public Flight getFlightByFlightNumber(String flightNumber);
	
	public List<Flight> getAllFlights();
	
	public void deleteFlight(int flightId);
	
	public Flight updateFlight(int flightId,FlightDTO flightdto);
}
