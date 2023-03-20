package com.wipro.bookingservice.dto;

import java.util.List;

import com.wipro.bookingservice.entities.Passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

	private int userId;
	private int scheduleId;
	private String seatClass;
	private String status;
	
	private List<Passenger> passengers;
}
