package com.parkhere.serviceImpl;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.parkhere.models.ParkingTime;
import com.parkhere.models.Vehicle;
import com.parkhere.service.SpotHistoryService;
import com.parkhere.service.SpotService;

public class SpotHistoryServiceImplTest {

	SpotHistoryService historyService;
	
	@Before
	public void Setup(){
		historyService = new SpotHistoryServiceImpl();
	}
	
	@Test
	public void testGetParkingTimeByDay() {
		List<List<ParkingTime>> parkingList = new ArrayList<List<ParkingTime>>();
		ParkingTime pt = new ParkingTime();
		pt.setChekinTime(new Date());
		Vehicle vehicle = new Vehicle();
		vehicle.setColor("red");
		vehicle.setNumber("abc123");
		pt.setVehicle(vehicle );
		List<ParkingTime> pts = new ArrayList<ParkingTime>();
		pts.add(pt);
		parkingList.add(pts);
		List<ParkingTime> ptHistory = historyService.getParkingTimeByDay(parkingList, new Date());
		assertEquals(1, ptHistory.size());
	}
	
	@Test
	public void testGetParkingTimeByDayFutureDate() {
		List<List<ParkingTime>> parkingList = new ArrayList<List<ParkingTime>>();
		ParkingTime pt = new ParkingTime();
		pt.setChekinTime(new Date());
		Vehicle vehicle = new Vehicle();
		vehicle.setColor("red");
		vehicle.setNumber("abc123");
		pt.setVehicle(vehicle );
		List<ParkingTime> pts = new ArrayList<ParkingTime>();
		pts.add(pt);
		parkingList.add(pts);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -2);//Past Date
		List<ParkingTime> ptHistory = historyService.getParkingTimeByDay(parkingList, c.getTime());
		assertEquals(0, ptHistory.size()); 
	}

}
