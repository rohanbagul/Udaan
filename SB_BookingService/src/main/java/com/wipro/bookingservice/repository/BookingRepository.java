package com.wipro.bookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bookingservice.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	public Booking findByBookingId(String bookingId);
	
	public void deleteByBookingId(String bookingId);
	
	public List<Booking> findByUserId(int userId);
	
	
}
