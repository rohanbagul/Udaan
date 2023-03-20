package com.wipro.bookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.bookingservice.entities.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

//	@Query("from Passenger where bookings = ?1")
//	List<Passenger> findByBookings(int id);
}
