package com.wipro.flightservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.wipro.flightservices.entities.Seats;



@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer> {

	@Query("from Seats where flight =?1")
	List<Seats> findByFlight(int flight);
		
}
