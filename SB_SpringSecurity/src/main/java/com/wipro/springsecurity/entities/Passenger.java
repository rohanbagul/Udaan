package com.wipro.springsecurity.entities;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	private String passengerMobile;
	private String passengerEmail;
	private String passengerCountry;
}
