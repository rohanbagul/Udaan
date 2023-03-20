package com.wipro.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedSeatDTO {

	private String flightNumber;
	private String seatClass;
	private int bookedSeats;
}
