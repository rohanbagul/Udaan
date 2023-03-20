package com.wipro.scheduleservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

	private String arrivalTime;
	private String departureTime;
	private String arrivalDate;
	private String departureDate;
	private int bussinessFare;
	private int economyFare;
	private int premiumFare;
	private String arrivalLocation;
	private String leavingLocation;
	private String flightNumber;
	private String flightName;
	private boolean isScheduled;
}
