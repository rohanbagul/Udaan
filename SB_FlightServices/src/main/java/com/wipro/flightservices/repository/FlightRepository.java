package com.wipro.flightservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.flightservices.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	public Flight findByFlightNumber(String flightNumber);
	
	public Flight findById(int Id);
}
