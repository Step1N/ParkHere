package com.parkhere.service;

import java.util.Date;
import java.util.List;

import com.parkhere.models.ParkingTime;

public interface SpotHistoryService {

	List<ParkingTime> getParkingTimeByDay(List<List<ParkingTime>> parkingList, Date date);

}
