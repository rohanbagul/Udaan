package com.wipro.flightservices.entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Seats")
public class Seats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String seatClass;
	private int seatCapacity;


}
