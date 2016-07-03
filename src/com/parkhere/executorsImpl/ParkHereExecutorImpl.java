package com.parkhere.executorsImpl;

import java.util.List;

import com.parkhere.executors.ParkHereExecutor;
import com.parkhere.models.Spot;
import com.parkhere.models.Vehicle;
import com.parkhere.service.SpotService;
import com.parkhere.serviceImpl.SpotServiceImpl;
import com.parkhere.utils.ParkhereUtils;

public class ParkHereExecutorImpl implements ParkHereExecutor{

	private SpotService spotService;
	private ParkhereUtils parkUtil;
	
	public ParkHereExecutorImpl() {
		spotService = new SpotServiceImpl();
		parkUtil = new ParkhereUtils();
	}
	
	

	public void createParkinLot(int number) {
		spotService.createSpots("Allocated slot number", number);
	}

	public void parkVehical(String number, String color) {
		Vehicle vehicle = new Vehicle(number, color);
		spotService.parkVehicle(vehicle);
	}

	public void leaveSlotNumber(int number) {
		spotService.leavSpotForParking("Allocated slot number", number);
	}

	public void parkingStatus() {
		spotService.findSpotsStatus();
	}

	@Override
	public void findRegNoForColorCars(String color) {
		List<Spot> spots = spotService.findSpotByCarColor(color);
		System.out.println();
		if(0 == spots.size()){
			System.out.println("Not Found");
			return;
		}
		for (Spot s : spots) {
			Vehicle v = s.getVehical();
			System.out.print(v.getNumber()+"\t");
		}
	}

	@Override
	public void findSlotNoForColorCars(String color) {
		List<Spot> spots = spotService.findSpotByCarColor(color);
		System.out.println();
		if(0 == spots.size()){
			System.out.println("Not Found");
			return;
		}
		for (Spot s : spots) {
			System.out.print(parkUtil.getIDNumber(s.getId())+"\t");
		}
	}

	@Override
	public void findSlotNoForRegNo(String regNo) {
		List<Spot> spots = spotService.findSpotForRegNo(regNo);
		System.out.println();
		if(0 == spots.size()){
			System.out.println("Not Found");
			return;
		}
		for (Spot s : spots) {
			System.out.println(parkUtil.getIDNumber(s.getId()));
		}
	}

}