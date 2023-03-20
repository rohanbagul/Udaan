package com.wipro.scheduleservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSeats {

	private String flightNumber;
	private String seatClass;
	private int bookedSeats;
	
}
