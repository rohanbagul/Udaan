package com.wipro.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBadRequestException extends RuntimeException {

	String message;
}
