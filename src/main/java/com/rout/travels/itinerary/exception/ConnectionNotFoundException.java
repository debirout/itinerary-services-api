package com.rout.travels.itinerary.exception;

public class ConnectionNotFoundException extends RuntimeException{
	

	private long connectionId;
	
	public long getConnectionId() {
		return connectionId;
	}

	public ConnectionNotFoundException(long conId){
		super("Connection ID"+conId+" not available.");
		this.connectionId=conId;
	}
	
	
	
}
