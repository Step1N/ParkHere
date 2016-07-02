package com.parkhere.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.parkhere.exceptions.ParkHereException;

public class DateUtils {
	
	private SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss"); 
	
	public String getDiff(Date d1, Date d2){
		long diff = d2.getTime() - d1.getTime();
	    long diffSeconds = diff / 1000 % 60;
	    long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000);
	    String duration = diffHours+":"+diffMinutes+":"+diffSeconds;
	    
	    return duration;
	}
	
	public String getDiff(String startDate, String endDate) throws ParkHereException{
		
		Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(startDate);
	        d2 = format.parse(endDate);
	    } catch (ParseException e) {
	        throw new ParkHereException("Error while parsing date", e);
	    }
		long diff = d2.getTime() - d1.getTime();
	    long diffSeconds = diff / 1000 % 60;
	    long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000);
	    String duration = diffHours+":"+diffMinutes+":"+diffSeconds;
	    
	    return duration;
	}
}
