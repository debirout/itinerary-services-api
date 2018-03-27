package com.rout.travels.itinerary.model;

import org.springframework.http.HttpStatus;

public class ApplicationStatusMessage {
	private HttpStatus statusCode;
	private String statusDesc;
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus notFound) {
		this.statusCode = notFound;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
