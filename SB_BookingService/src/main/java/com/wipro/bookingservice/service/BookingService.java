package com.wipro.bookingservice.service;

import java.util.List;

import com.wipro.bookingservice.dto.BookingDTO;
import com.wipro.bookingservice.entities.Booking;

public interface BookingService {

	public Booking createBooking(BookingDTO bookingdto);
	
	public List<Booking> getAllBookings();
	
	public Booking getBookingByBookingId(String bookingId);
	
	public void deleteBooking(int id);
	
	public Booking updateBooking(int id, BookingDTO bookingdto);
	
	public List<Booking> getBookingsByUserId(int userId);
	
	
}
