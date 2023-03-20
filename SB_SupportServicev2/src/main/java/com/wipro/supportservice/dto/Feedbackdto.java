package com.wipro.supportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Feedbackdto {

	private String name;
	private String email;
	private String description;
	
}