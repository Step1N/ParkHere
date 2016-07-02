package com.parkhere.models;

import java.util.ArrayList;
import java.util.List;

public class Spot {
	private String id;
	private SpotType type;
	private MaintenanceDetails maintainanceDetails;
	private Boolean isFree;
	private int distanceFromFront;
	private Boolean isAdvanceBooking;
	private Vehicle vehical;
	private SpotStatus status;
	private List<ParkingTime> parkingTimes = new ArrayList<ParkingTime>();
	
	public Spot(){
		maintainanceDetails = new MaintenanceDetails();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SpotType getType() {
		return type;
	}
	public void setType(SpotType type) {
		this.type = type;
	}
	
	public MaintenanceDetails getMaintainanceDetails() {
		return maintainanceDetails;
	}
	public void setMaintainanceDetails(MaintenanceDetails maintainanceDetails) {
		this.maintainanceDetails = maintainanceDetails;
	}
	public Boolean getIsFree() {
		return isFree;
	}
	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}
	public int getDistanceFromFront() {
		return distanceFromFront;
	}
	public void setDistanceFromFront(int distanceFromFront) {
		this.distanceFromFront = distanceFromFront;
	}
	public Boolean getIsAdvanceBooking() {
		return isAdvanceBooking;
	}
	public void setIsAdvanceBooking(Boolean isAdvanceBooking) {
		this.isAdvanceBooking = isAdvanceBooking;
	}
	public Vehicle getVehical() {
		return vehical;
	}
	public void setVehical(Vehicle vehical) {
		this.vehical = vehical;
	}
	public SpotStatus getStatus() {
		return status;
	}
	public void setStatus(SpotStatus status) {
		this.status = status;
	}

	public List<ParkingTime> getParkingTimes() {
		return parkingTimes;
	}

}
