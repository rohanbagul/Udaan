package com.wipro.routeservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {

	public NotFoundException(String msg) {
	    super(msg);
	  }
}
