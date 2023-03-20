package com.wipro.flightservices.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String flightNumber;
	private String flightName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Seats> seats = new ArrayList<>();

}
