package com.senecaglobal.assignment.exception;

/**
* 
* App exception handling for any BAD request   
* 
* @author  Deepak Jakhotia
* @version 1.0
* @since   2018-08-07 
*/


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorkLoadException extends RuntimeException {

	public WorkLoadException(String exception) {
		super(exception);
	}
}
