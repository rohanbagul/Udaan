package com.wipro.flightservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	 public ResponseEntity<Object> handleCustomException(CustomException ex,WebRequest req) {
	        return new ResponseEntity<>("Custom exception message: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	
	
	@ExceptionHandler(MyBadRequestException.class)
	 public ResponseEntity<Object> handleMyBadRequestException(MyBadRequestException ex) {
	        return new ResponseEntity<>("exception message: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	
	@ExceptionHandler(NotFoundException.class)
	 public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
	        return new ResponseEntity<>("exception message: " + ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
}
