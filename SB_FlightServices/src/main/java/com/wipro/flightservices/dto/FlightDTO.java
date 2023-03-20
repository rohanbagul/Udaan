package com.wipro.flightservices.dto;

import java.util.List;


import com.wipro.flightservices.entities.Seats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
	
	private String flightNumber;
	private String flightName;
	private List<Seats> seats;
	
}
