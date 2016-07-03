package com.parkhere.serviceImpl;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

import com.parkhere.models.Spot;
import com.parkhere.models.SpotType;
import com.parkhere.models.Vehicle;
import com.parkhere.service.SpotService;

public class SpotServiceImplTest {

	SpotService service;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void Setup(){
		System.setOut(new PrintStream(outContent));
		service = new SpotServiceImpl();
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testCreateSpots() {
		service.createSpots("abc", 3);
		Assert.assertThat(outContent.toString(), new Matches("Created a parking lot with 3 slots\r\n"));
	}

	@Test
	public void testParkVehicle() {
		String color = "red";
		Vehicle v = new Vehicle("abc123", color);
		service.createSpots("abc", 1);
		Spot spot=  service.parkVehicle(v);
		assertNotNull(spot);
		Assert.assertThat(outContent.toString(), new Matches("Created a parking lot with 1 slots\r\nAllocated slot number: 1\r\n"));
	}
	
	@Test
	public void testParkVehicleWhenNoSlotAvailble() {
		String color = "red";
		Vehicle v1 = new Vehicle("abc123", color);
		service.createSpots("abc", 1);
		Spot spot=  service.parkVehicle(v1);
		assertNotNull(spot);
		Vehicle v2 = new Vehicle("abc123", color);
		Spot spot2 =  service.parkVehicle(v2);
		assertNull(spot2.getId());
		String expected ="Created a parking lot with 1 slots\r\nAllocated slot number: 1\r\nSlots are completly occupied\r\n";
		Assert.assertThat(outContent.toString(), new Matches(expected));
	}

	@Test
	public void testFindSpotByCarColor() {
		String color = "red";
		String number ="abcd1234";
		Vehicle v = new Vehicle(number, color);
		service.createSpots("abc", 1);
		Spot spot = service.parkVehicle(v);
		assertNotNull(spot);
		assertEquals(color, spot.getVehical().getColor());
		assertEquals(number, spot.getVehical().getNumber());
		List<Spot> spots = service.findSpotByCarColor(color);
		assertEquals(1, spots.size());
		assertEquals(color, spots.get(0).getVehical().getColor());
	}

	@Test
	public void testFindSpotForRegNo() {
		String color = "red";
		String regNo ="abcd1234";
		Vehicle v = new Vehicle(regNo, color);
		service.createSpots("abc", 1);
		Spot spot = service.parkVehicle(v);
		assertNotNull(spot);
		assertEquals(color, spot.getVehical().getColor());
		assertEquals(regNo, spot.getVehical().getNumber());
		List<Spot> spots = service.findSpotForRegNo(regNo);
		assertEquals(1, spots.size());
		assertEquals(regNo, spots.get(0).getVehical().getNumber());
	}

	@Test
	public void testFreedSpot() {
		String color = "red";
		String regNo ="abcd1234";
		Vehicle v = new Vehicle(regNo, color);
		service.createSpots("abc", 1);
		Spot spot = service.parkVehicle(v);
		assertNotNull(spot);
		service.freedSpot(spot, v);
		String expected ="Created a parking lot with 1 slots\r\nAllocated slot number: 1\r\nSlot number 1 is free\r\n";
		Assert.assertThat(outContent.toString(), new Matches(expected));
	}


	@Test
	public void testFindSpotByVehicleType() {
		String color = "red";
		String regNo ="abcd1234";
		Vehicle v = new Vehicle(regNo, color);
		v.setType(SpotType.FOURWHEELER);
		service.createSpots("abc", 1);
		Assert.assertThat(outContent.toString(), new Matches("Created a parking lot with 1 slots\r\n"));
		List<Spot> spots = new ArrayList<Spot>();
		Spot s = new Spot();
		s.setIsFree(true);
		s.setIsAdvanceBooking(true);
		s.setVehical(v);
		spots.add(s);
		List<Spot>exitingSpot = service.findSpotByVehicleType(spots, "FOURWHEELER");
		assertNotNull(exitingSpot);
		assertNotNull(exitingSpot.get(0).getVehical());
		assertEquals(SpotType.FOURWHEELER, exitingSpot.get(0).getVehical().getType());
	}

	@Test
	public void testFindSpotsStatus() {
		String color = "red";
		String number ="abcd1234";
		Vehicle v = new Vehicle(number, color);
		service.createSpots("abc", 1);
		Spot spot = service.parkVehicle(v);
		assertNotNull(spot);
		service.findSpotsStatus();
		String expected ="Created a parking lot with 1 slots\r\nAllocated slot number: 1\r\nSlot No.\tRegistration No\t Colour\r\nSpot-1\t\tabcd1234\t red\r\n";
		Assert.assertThat(outContent.toString(), new Matches(expected));
	}
	
	@Test
	public void testFindSpotsStatusForNoVehichalPark() {
		service.createSpots("abc", 1);
		service.findSpotsStatus();
		String expected ="Created a parking lot with 1 slots\r\nSlot No.\tRegistration No\t Colour\r\n";
		Assert.assertThat(outContent.toString(), new Matches(expected));
	}

}
