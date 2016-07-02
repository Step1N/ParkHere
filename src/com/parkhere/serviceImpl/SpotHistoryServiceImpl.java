package com.parkhere.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parkhere.models.ParkingTime;
import com.parkhere.service.SpotHistoryService;

public class SpotHistoryServiceImpl implements SpotHistoryService{
	
	@Override
	public List<ParkingTime> getParkingTimeByDay(List<List<ParkingTime>> parkingList, Date date) {
		List<ParkingTime> parkingTimes = new ArrayList<ParkingTime>();
		for (List<ParkingTime> parkings : parkingList) {
			for (ParkingTime pt : parkings) {
				if (pt.getChekinTime().compareTo(date) < 0) {
					parkingTimes.add(pt);
				}
			}
		}

		return parkingTimes;
	}
	
	/*@Override
	public ParkingTime getParkingTimeForVehical(List<ParkingTime> parkingTimes, Vehicle vehicle, String spotID) {
		ParkingTime parked = new ParkingTime();
		for (ParkingTime pt : parkingTimes) {
			if (pt.getChekinTime().compareTo(new Date()) < 0) {
				parked = pt;
				break;
			}
		}

		return parked;
	}
	
	@Override
	public  Vehicle getVehicalByDate(List<ParkingTime> parkingTimes, Date today){
		Vehicle vehicle = new Vehicle();
		for (ParkingTime pt : parkingTimes) {
			if (pt.getChekinTime().compareTo(today) < 1) {
				vehicle = pt.getVehicle();
			}
		}
		
		return vehicle;
	}
	
	@Override
	public List<ParkingTime> getParkingTimeByVehicle(Map<String, List<ParkingTime>>historyMap, String vehicleNumber){
		 List<ParkingTime> parkingTimes = new ArrayList<ParkingTime>();
		for(Entry<String, List<ParkingTime>> entrySet : historyMap.entrySet()){
			for(ParkingTime pt : entrySet.getValue()){
				Vehicle v =pt.getVehicle();
				if(vehicleNumber.equals(v.getNumber())){
					parkingTimes.add(pt);
				}
			}
		}
		
		return parkingTimes;
	}
	
	@Override
	public List<ParkingTime> getParkingTimeBySpot(Map<String, List<ParkingTime>> historyMap, String spotID){
		if (0 == historyMap.size()) {
			return new ArrayList<ParkingTime>();
		}
		
		return historyMap.get(spotID);
	}

	@Override
	public ParkingTime getParkingTimeForVehical(Vehicle vehicle, String spotID) {
		// TODO Auto-generated method stub
		return null;
	}

*/}
