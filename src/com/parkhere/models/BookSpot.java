package com.parkhere.models;

import java.util.ArrayList;
import java.util.List;

public class BookSpot {
	
	private List<BookingSpotDetail> bookingDetails;

	public List<BookingSpotDetail> getBookingDetails() {
		if(null == bookingDetails){
			return new ArrayList<BookingSpotDetail>();
		}
		
		return bookingDetails;
	}

}
