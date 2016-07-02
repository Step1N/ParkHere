package com.parkhere.utils;

public class ParkhereUtils {

	
	public String getIDNumber(String slotID){
		String [] ids = slotID.split("-");
		return ids[1];
	}
}
