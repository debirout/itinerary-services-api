package com.rout.travels.itinerary.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.rout.travels.itinerary.exception.ConnectionNotFoundException;
import com.rout.travels.itinerary.model.ApplicationStatusMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


@ControllerAdvice
public class ItineraryControllerAdvice {
	
	@ExceptionHandler(ConnectionNotFoundException.class)
	ResponseEntity<ApplicationStatusMessage> connectionNotFound(ConnectionNotFoundException e){
		ApplicationStatusMessage message = new ApplicationStatusMessage();
		message.setStatusCode(HttpStatus.NOT_FOUND);
		message.setStatusDesc(e.getConnectionId()+" is not available.");
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}

}
