package com.wipro.scheduleservices.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	
	private String arrivalTime;
	private String departureTime;
	private String arrivalDate;
	private String departureDate;
	private int bussinessFare;
	private int economyFare;
	private int premiumFare;
	private int bussinessSeats;
	private int economySeats;
	private int premiumSeats;
	private String arrivalLocation;
	private String leavingLocation;
	private String flightNumber;
	private String flightName;
	private boolean isScheduled;


}
