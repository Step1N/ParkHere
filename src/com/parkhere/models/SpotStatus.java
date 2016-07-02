package com.parkhere.models;

public enum SpotStatus {

	EMPTY("empty"), OCCUPIED("occupied");

	private String status;
	SpotStatus(String status) {
		this.status =status;
	}
	
	public String getStatus(){
		return status;
	}
}
