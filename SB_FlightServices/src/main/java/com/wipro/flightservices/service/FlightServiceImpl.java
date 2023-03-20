package com.wipro.flightservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.flightservices.dto.FlightDTO;
import com.wipro.flightservices.entities.Flight;
import com.wipro.flightservices.entities.Seats;
import com.wipro.flightservices.exception.CustomException;
import com.wipro.flightservices.exception.MyBadRequestException;
import com.wipro.flightservices.exception.NotFoundException;
import com.wipro.flightservices.repository.FlightRepository;
import com.wipro.flightservices.repository.SeatRepository;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private SeatRepository seatRepo;
	

	@Override
	public Flight addFlight(FlightDTO flightdto) {
		
		Flight foundflight = flightRepo.findByFlightNumber(flightdto.getFlightNumber());
		Flight flight = new Flight();
		
		if(foundflight == null) {
			
			flight.setFlightName(flightdto.getFlightName());
			flight.setFlightNumber(flightdto.getFlightNumber());
			flight.setSeats(flightdto.getSeats());
			
			flightRepo.save(flight);
		}
		else {
			throw new MyBadRequestException("Unable to add new Flight !!");
		}
		
		return flight;	
	}



	@Override
	public Flight getByFlightId(int flightId) {
		
		Flight flight1 = flightRepo.findById(flightId); 
		
		if(flight1 != null) {
			return flight1;
		}
		else {
			throw new NotFoundException("Data Not Found with id :"+ flightId);
		}
		
	}

	@Override
	public List<Flight> getAllFlights() {
		
		return flightRepo.findAll();
	}

	@Override
	public void deleteFlight(int flightId) {
		
		Flight flight = flightRepo.findById(flightId);
		List<Seats> seatlist = seatRepo.findByFlight(flightId);
		
		if(flight != null) {
			
			flightRepo.deleteById(flightId);
			seatRepo.deleteAll(seatlist);
			
		}
		else {
			throw new NotFoundException("Data not found with id :"+ flightId);
		}
		
	}

	@Override
	public Flight updateFlight(int flightId, FlightDTO flightdto) {
		Flight flight = flightRepo.findById(flightId);
		
		if(flight != null) {
			flight.setSeats(flightdto.getSeats());
			flight = flightRepo.save(flight);
		}
		else {
			throw new CustomException("Data can not be updated !!");
		}
		
		return flight;
	}

	@Override
	public Flight getFlightByFlightNumber(String flightNumber) {
		
		Flight flight1 = flightRepo.findByFlightNumber(flightNumber);
		
		if(flight1 != null) {
			
			return flight1;
		}
		else {
			throw new NotFoundException("Data not found with Flight Number :"+ flightNumber);
		}
		
	}
	
	
}
