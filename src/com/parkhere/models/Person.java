package com.parkhere.models;

public class Person {

	private String name;
	private Integer contactNumber;
	private String emailID;
	private String dlID;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Integer contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getDlID() {
		return dlID;
	}
	public void setDlID(String dlID) {
		this.dlID = dlID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
}
