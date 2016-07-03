package com.parkhere.executors;


public interface ParkHereExecutor {

	void findRegNoForColorCars(String color);

	void findSlotNoForColorCars(String color);

	void findSlotNoForRegNo(String regNo);

}
