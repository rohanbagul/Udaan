package com.wipro.scheduleservices.service;

import java.util.List;

import com.wipro.scheduleservices.dto.ScheduleDTO;
import com.wipro.scheduleservices.dto.UpdateSeats;
import com.wipro.scheduleservices.entities.Schedule;

public interface ScheduleService {

	public Schedule addSchedule(ScheduleDTO scheduledto);
	
	public Schedule updateSchedule(int scheuleId,ScheduleDTO scheduledto);
	
	public List<Schedule> getAllSchedule();
	
	public Schedule getScheduleById(int scheduleId);
	
	public List<Schedule> getSchedByleaveLocAndArrLoc(String leavingLocation, String arrivalLocation);
	
    public List<Schedule> getSchedByFlightNum(String flightNumber);
	
	public List<Schedule> getSchedByFlightName(String flightName);
	
	public List<Schedule> getSchedByArrTime(String arrivalTime);
	
	public List<Schedule> getSchedByDepTime(String departureTime);
	
	public List<Schedule> getSchedByLeaveLoc(String leavingLocation);
	
	public List<Schedule> getSchedByArrLoc(String arrivalLocation);
	
	public void deleteSchedule(int scheduleId);
	
	public Schedule updateSeatData(int scheduleId,UpdateSeats updateSeats);
	
	
}
