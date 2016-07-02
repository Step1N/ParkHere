package com.parkhere.models;

import java.util.Date;

public class ParkingTime {
	
	private Date chekinTime;
	private Date chekoutTime;
	private Vehicle vehicle;
	private String duration;
	


	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getChekinTime() {
		return chekinTime;
	}
	public void setChekinTime(Date chekinTime) {
		this.chekinTime = chekinTime;
	}
	public Date getChekoutTime() {
		return chekoutTime;
	}
	public void setChekoutTime(Date chekoutTime) {
		this.chekoutTime = chekoutTime;
	}
}
