package com.wipro.springsecurity.exception;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {
	
	String message;
	
	public CustomException(String message) {
		super();
		this.message = message;
	}
}
