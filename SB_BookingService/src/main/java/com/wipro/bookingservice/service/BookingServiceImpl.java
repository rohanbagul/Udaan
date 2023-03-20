package com.wipro.bookingservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.bookingservice.dto.BookedSeatDTO;
import com.wipro.bookingservice.dto.BookingDTO;
import com.wipro.bookingservice.entities.Booking;
import com.wipro.bookingservice.entities.Passenger;
import com.wipro.bookingservice.entities.Schedule;
import com.wipro.bookingservice.repository.BookingRepository;
import com.wipro.bookingservice.repository.PassengerRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
	Schedule schedule;
	
	@Override
	public Booking createBooking(BookingDTO bookingdto) {
		
		Booking booking1 = new Booking();
	
		ResponseEntity<Schedule> getSchedule= restTemplate.getForEntity("http://localhost:8082/scheduleService/getSchedById/"+bookingdto.getScheduleId(), Schedule.class);
		schedule = getSchedule.getBody();
		logger.info("{}", getSchedule);
		
		Random rand = new Random();
		long randomnumber =(long)(rand.nextDouble() * 10000000000L); 
		String bookid = String.valueOf(randomnumber);
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String bookingDate = date.format(formatter);
		
		booking1.setScheduleId(bookingdto.getScheduleId());
		booking1.setUserId(bookingdto.getUserId());
		booking1.setBookingId(bookid);
		booking1.setBookingDate(bookingDate);
		booking1.setFlightName(schedule.getFlightName());
		booking1.setFlightNumber(schedule.getFlightNumber());
		booking1.setArrivalDate(schedule.getArrivalDate());
		booking1.setArrivalLocation(schedule.getArrivalLocation());
		booking1.setArrivalTime(schedule.getArrivalTime());
		booking1.setDepartureDate(schedule.getDepartureDate());
		booking1.setDepartureLocation(schedule.getLeavingLocation());
		booking1.setDepartureTime(schedule.getDepartureTime());
		booking1.setStatus(bookingdto.getStatus());
		booking1.setSeatClass(bookingdto.getSeatClass());
		booking1.setPassenger(bookingdto.getPassengers());
		booking1.setTotalAmount(toatalAmount(bookingdto));
		
		booking1 = bookingRepo.save(booking1);
		
		availableSeats(bookingdto);
		
		return booking1 ;
	}
	
	public double toatalAmount(BookingDTO bookingdto) {
		double totalAmount = 0;
		List<Passenger> list = bookingdto.getPassengers();
		
		for(Passenger passenger : list) {
			if(bookingdto.getSeatClass().equals("Bussiness")){
				totalAmount += schedule.getBussinessFare();
			}
			else if(bookingdto.getSeatClass().equals("Economy")) {
				totalAmount += schedule.getEconomyFare();
			}
			else if(bookingdto.getSeatClass().equals("Premium")) {
				totalAmount += schedule.getPremiumFare();
			}
		}
		return totalAmount;
	}

	public void availableSeats(BookingDTO bookingdto) {
		
		BookedSeatDTO bookedseatdto1 = new BookedSeatDTO();
		
		List<Passenger> list = bookingdto.getPassengers();
		
		bookedseatdto1.setFlightNumber(schedule.getFlightNumber());
		bookedseatdto1.setSeatClass(bookingdto.getSeatClass());
		bookedseatdto1.setBookedSeats(list.size());
			
		
		ResponseEntity<Schedule> updateSchedule = restTemplate.postForEntity("http://localhost:8082/scheduleService/updateSeatData/"+bookingdto.getScheduleId(),bookedseatdto1, Schedule.class);
		Schedule getshedule1 = updateSchedule.getBody();
		
		logger.info("{}", getshedule1);
	}
	
	@Override
	public List<Booking> getAllBookings() {
		
		return bookingRepo.findAll() ;
	}

	@Override
	public Booking getBookingByBookingId(String bookingId) {
		Booking booking2 = bookingRepo.findByBookingId(bookingId);
		
		return booking2;
	}


	@Override
	public void deleteBooking(int id) {
		
		Booking booking3 = bookingRepo.findById(id).get();
		//List<Passenger> passengerList = passengerRepo.findByBookings(id);
		if(booking3 != null) {
			bookingRepo.deleteById(id);
			//passengerRepo.deleteAll(passengerList);
		}
		
	}

	@Override
	public Booking updateBooking(int id, BookingDTO bookingdto) {
		Booking updatebooking = bookingRepo.findById(id).get();
		
		ResponseEntity<Schedule> getSchedule= restTemplate.getForEntity("http://localhost:8082/scheduleService/getSchedById/"+bookingdto.getScheduleId(), Schedule.class);
		Schedule schedule = getSchedule.getBody();
		logger.info("{}", getSchedule);
		
		if(updatebooking != null) {
			
			Random rand = new Random();
			long randomnumber =(long)(rand.nextDouble() * 10000000000L); 
			String bookid = String.valueOf(randomnumber);
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String bookingDate = date.format(formatter);
			
			updatebooking.setScheduleId(bookingdto.getScheduleId());
			updatebooking.setBookingId(bookid);
			updatebooking.setBookingDate(bookingDate);
			updatebooking.setFlightName(schedule.getFlightName());
			updatebooking.setFlightNumber(schedule.getFlightNumber());
			updatebooking.setArrivalDate(schedule.getArrivalDate());
			updatebooking.setArrivalLocation(schedule.getArrivalLocation());
			updatebooking.setArrivalTime(schedule.getArrivalTime());
			updatebooking.setDepartureDate(schedule.getDepartureDate());
			updatebooking.setDepartureLocation(schedule.getLeavingLocation());
			updatebooking.setDepartureTime(schedule.getDepartureTime());
			updatebooking.setStatus(bookingdto.getStatus());
			updatebooking.setPassenger(bookingdto.getPassengers());
			
			updatebooking = bookingRepo.save(updatebooking);
			
		}
		
		return updatebooking;	
	}

	@Override
	public List<Booking> getBookingsByUserId(int userId) {
		
		return bookingRepo.findByUserId(userId);
	}
	
	
}
