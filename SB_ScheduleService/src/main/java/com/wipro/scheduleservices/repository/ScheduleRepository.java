package com.wipro.scheduleservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.scheduleservices.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	public List<Schedule> findByFlightNumber(String flightNumber);
	
	public List<Schedule> findByFlightName(String flightName);
	
	public List<Schedule> findByArrivalTime(String arrivalTime);
	
	public List<Schedule> findByDepartureTime(String departureTime);
	
	public List<Schedule> findByLeavingLocation(String leavingLocation);
	
	public List<Schedule> findByArrivalLocation(String arrivalLocation);
	
	public List<Schedule> findByLeavingLocationAndArrivalLocation(String leavingLocation, String arrivalLocation);
	
	
}
