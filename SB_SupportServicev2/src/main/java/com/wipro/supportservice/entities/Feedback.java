package com.wipro.supportservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Feedback { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackid;

	private String name;
	private String email;
	private String description;
	

}
