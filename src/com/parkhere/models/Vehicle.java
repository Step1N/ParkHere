package com.parkhere.models;

public class Vehicle {
	private String number;
	private String color;
	private SpotType type;
	private Person driver;
	private String spotID;
	
	public Vehicle(String number, String color){
		this.number = number;
		this.color = color;
	}
	
	public Vehicle(){}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Person getDriver() {
		return driver;
	}
	public void setDriver(Person driver) {
		this.driver = driver;
	}
	public SpotType getType() {
		return type;
	}
	public void setType(SpotType type) {
		this.type = type;
	}
	public String getSpotID() {
		return spotID;
	}
	public void setSpotID(String spotID) {
		this.spotID = spotID;
	}
	
}
