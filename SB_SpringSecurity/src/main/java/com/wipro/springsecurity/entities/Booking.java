package com.wipro.springsecurity.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking  {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String bookingId;
	private String bookingDate;
	private int scheduleId;
	private int userId;
	private String flightNumber;
	private String flightName;
	private String arrivalDate;
	private String departureDate;
	private String arrivalTime;
	private String departureTime;
	private String arrivalLocation;
	private String departureLocation;
	private String seatClass;
	private double totalAmount;
	private String status;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName="id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Passenger> passenger = new ArrayList<>() ;;


}
