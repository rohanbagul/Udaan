package com.wipro.scheduleservices.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.scheduleservices.dto.ScheduleDTO;
import com.wipro.scheduleservices.dto.UpdateSeats;
import com.wipro.scheduleservices.entities.Flight;
import com.wipro.scheduleservices.entities.Route;
import com.wipro.scheduleservices.entities.Schedule;
import com.wipro.scheduleservices.entities.Seats;
import com.wipro.scheduleservices.exception.NotFoundException;
import com.wipro.scheduleservices.exception.ResourceNotFoundException;
import com.wipro.scheduleservices.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {


	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

	@Override
	public Schedule addSchedule(ScheduleDTO scheduledto) {
		
		ResponseEntity<Route> getRoute= restTemplate.getForEntity("http://localhost:8084/api/v2/getRouteBySrcAndDest/"+scheduledto.getLeavingLocation()+"/"+scheduledto.getArrivalLocation(), Route.class);
		Route routes = getRoute.getBody();
		logger.info("{}",routes);
		
		ResponseEntity<Flight> getFlight = restTemplate.getForEntity("http://localhost:8085/flightservice/getFlightByNum/"+scheduledto.getFlightNumber(), Flight.class);
		Flight flight = getFlight.getBody();
		logger.info("{}",flight);
		
		Schedule schedule = new Schedule();
		
		if(routes != null && flight != null) {
		
		schedule.setArrivalTime(scheduledto.getArrivalTime());
		schedule.setDepartureTime(scheduledto.getDepartureTime());
		schedule.setArrivalDate(scheduledto.getArrivalDate());
		schedule.setDepartureDate(scheduledto.getDepartureDate());
		schedule.setBussinessFare(scheduledto.getBussinessFare());
		schedule.setEconomyFare(scheduledto.getEconomyFare());
		schedule.setPremiumFare(scheduledto.getPremiumFare());
		
		schedule.setLeavingLocation(routes.getSource());
		schedule.setArrivalLocation(routes.getDestination());
		schedule.setFlightNumber(flight.getFlightNumber());
		schedule.setFlightName(flight.getFlightName());
		schedule.setScheduled(true);
		
		
		List<Seats> seats = flight.getSeats();
		for(Seats seat : seats) {
			if(seat.getSeatClass().equals("Bussiness")){
				schedule.setBussinessSeats(seat.getSeatCapacity());
			}
			else if(seat.getSeatClass().equals("Premium")) {
				schedule.setPremiumSeats(seat.getSeatCapacity());
			}
			else if(seat.getSeatClass().equals("Economy")) {
				schedule.setEconomySeats(seat.getSeatCapacity());
			}
			
		}
		
		schedule = scheduleRepo.save(schedule);
		}
		else {
			
			throw new ResourceNotFoundException("Resources not found !!!");
		}
		
		return schedule;
	}
	
	

	@Override
	public Schedule updateSchedule(int scheduleId,ScheduleDTO scheduledto) {
		
        Schedule schedule = scheduleRepo.findById(scheduleId).get();
        
        ResponseEntity<Route> getRoute= restTemplate.getForEntity("http://localhost:8084/api/v2/getRouteBySrcAndDest/"+scheduledto.getLeavingLocation()+"/"+scheduledto.getArrivalLocation(), Route.class);
		Route routes = getRoute.getBody();
		logger.info("{}",routes);
		
		ResponseEntity<Flight> getFlight = restTemplate.getForEntity("http://localhost:8085/flightservice/getFlightByNum/"+scheduledto.getFlightNumber(), Flight.class);
		Flight flight = getFlight.getBody();
		logger.info("{}",flight);
		
		if(schedule != null && routes != null && flight != null)
		{
		schedule.setArrivalTime(scheduledto.getArrivalTime());
		schedule.setDepartureTime(scheduledto.getDepartureTime());
		schedule.setArrivalDate(scheduledto.getArrivalDate());
		schedule.setDepartureDate(scheduledto.getDepartureDate());
		schedule.setBussinessFare(scheduledto.getBussinessFare());
		schedule.setEconomyFare(scheduledto.getEconomyFare());
		schedule.setPremiumFare(scheduledto.getPremiumFare());
		schedule.setLeavingLocation(routes.getSource());
		schedule.setArrivalLocation(routes.getDestination());
		schedule.setFlightNumber(flight.getFlightNumber());
		schedule.setFlightName(flight.getFlightName());
		schedule.setScheduled(true);
		
		
		List<Seats> seats = flight.getSeats();
		for(Seats seat : seats) {
			if(seat.getSeatClass().equals("Bussiness")){
				schedule.setBussinessSeats(seat.getSeatCapacity());
			}
			else if(seat.getSeatClass().equals("Premium")) {
				schedule.setPremiumSeats(seat.getSeatCapacity());
			}
			else if(seat.getSeatClass().equals("Economy")) {
				schedule.setEconomySeats(seat.getSeatCapacity());
			}
			
		}
		
		schedule = scheduleRepo.save(schedule);
		}
		else {
			
			throw new NotFoundException("Data not found with id: "+ scheduleId);
		}
		return schedule;
	}

	@Override
	public List<Schedule> getAllSchedule() {
		
		return scheduleRepo.findAll();
	}

	@Override
	public List<Schedule> getSchedByFlightNum(String flightNumber) {
	
		
		if(flightNumber != null) {
			
			return   scheduleRepo.findByFlightNumber(flightNumber);

		}
		else {
			
			throw new NotFoundException("Data not found with flightNumber : "+ flightNumber);
		}
		
		
	}

	@Override
	public List<Schedule> getSchedByFlightName(String flightName) {
		
		return scheduleRepo.findByFlightName(flightName);
	}

	@Override
	public List<Schedule> getSchedByArrTime(String arrivalTime) {
		
		return scheduleRepo.findByArrivalTime(arrivalTime);
	}

	@Override
	public List<Schedule> getSchedByDepTime(String departureTime) {
		
		return scheduleRepo.findByDepartureTime(departureTime);
	}

	@Override
	public List<Schedule> getSchedByLeaveLoc(String leavingLocation) {
		
		return scheduleRepo.findByLeavingLocation(leavingLocation);
	}

	@Override
	public List<Schedule> getSchedByArrLoc(String arrivalLocation) {
		
		return scheduleRepo.findByArrivalLocation(arrivalLocation);
	}

	@Override
	public void deleteSchedule(int scheduleId) {
		
		Schedule schedule = scheduleRepo.findById(scheduleId).get();
		
		if(schedule != null) {
			scheduleRepo.deleteById(scheduleId);
		}
		else {
			throw new NotFoundException("Data not found with id :"+ scheduleId);
		}
	}

	@Override
	public Schedule getScheduleById(int scheduleId) {
		
		Schedule schedule = scheduleRepo.findById(scheduleId).get();
		
		return schedule ;
	}



	@Override
	public Schedule updateSeatData(int scheduleId,UpdateSeats updateSeats) {
		
		 Schedule schedule = scheduleRepo.findById(scheduleId).get();
		 int totalSeats =0;
		 int availableSeats = 0;
		
		 if(schedule != null) {
			 if(updateSeats.getSeatClass().equals("Bussiness")) {
				 totalSeats = schedule.getBussinessSeats();
				 if(totalSeats > 1) {
				 availableSeats = totalSeats - updateSeats.getBookedSeats();
				 schedule.setBussinessSeats(availableSeats);
				 }
			 }
			 else if(updateSeats.getSeatClass().equals("Economy")) {
				 totalSeats = schedule.getEconomySeats();
				 if(totalSeats > 1) {
				 availableSeats = totalSeats - updateSeats.getBookedSeats();
				 schedule.setEconomySeats(availableSeats);
				 }
			 }
			 else if(updateSeats.getSeatClass().equals("Premium")) {
				 totalSeats = schedule.getPremiumSeats();
				 if(totalSeats > 1) {
				 availableSeats = totalSeats - updateSeats.getBookedSeats();
				 schedule.setPremiumSeats(availableSeats);
				 }
			 }
			
		 }
		return scheduleRepo.save(schedule);
	}



	@Override
	public List<Schedule> getSchedByleaveLocAndArrLoc(String leavingLocation, String arrivalLocation) {
		
		return scheduleRepo.findByLeavingLocationAndArrivalLocation(leavingLocation, arrivalLocation);
	}
	
}
