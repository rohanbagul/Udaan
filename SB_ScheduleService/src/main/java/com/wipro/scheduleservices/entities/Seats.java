package com.wipro.scheduleservices.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Seats")
public class Seats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;

	private String seatClass;
	private int seatCapacity;
	

}
