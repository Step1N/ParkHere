package com.parkhere.service;

import java.util.List;

import com.parkhere.models.Person;
import com.parkhere.models.Spot;
import com.parkhere.models.Vehicle;

public interface SpotService {

	void createSpots(String prefix, int numbers);

	Spot parkVehicle(Vehicle vehical);

	void findSpotsStatus(List<Spot> spots);

	void leavSpotForParking(String prefix, int number);

	void findSpotsStatus();

	Person generateDriverDetails(int contactNumber, String name, String lID,
			String emailID);

	Vehicle generateVehicleDetails(String number, String color, Person driver);

	boolean checkForAdvanceBooking(Spot s);

	List<Spot> findSpotByVehicleType(List<Spot> spots, String type);

	List<Spot> findSpotForRegNo(String regNo);

	void freedSpot(Spot spot, Vehicle vehicle);

	List<Spot> findSpotByCarColor(String color);

}
