package com.parkhere.executorsImpl;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

import com.parkhere.executors.ParkHereExecutor;
import com.parkhere.models.Spot;
import com.parkhere.models.Vehicle;
import com.parkhere.service.SpotService;
import com.parkhere.serviceImpl.SpotServiceImpl;
import com.parkhere.utils.ParkhereUtils;

public class ParkHereExecutorImplTest {

	ParkHereExecutor parkHereExecutor;
	SpotService spotService;
	ParkhereUtils parkUtil;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void Setup(){
		System.setOut(new PrintStream(outContent));
		parkHereExecutor = new ParkHereExecutorImpl();
		spotService =  mock(SpotServiceImpl.class);
		parkUtil = mock(ParkhereUtils.class);
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testFindRegNoForColorCarsWhenNoCarParked() {
		String color ="red";
		List<Spot> spots =  new ArrayList<Spot>();
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals(0, spotService.findSpotByCarColor(anyString()).size());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}
	
	@Test
	public void testFindRegNoForColorCarsWhenDifferentColorCarParked() {
		String color ="green";
		List<Spot> spots =  new ArrayList<Spot>();
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals(0, spotService.findSpotByCarColor(anyString()).size());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}
	
	@Test
	public void testFindRegNoForColorCarsWhenSameColorCarParked() {
		String color ="red";
		Vehicle v1 = new Vehicle();
		v1.setColor(color);
		v1.setNumber("abcd-45-9874");
		Spot s1 = new Spot();
		s1.setId("S-1");
		s1.setVehical(v1);
		List<Spot> spots = new ArrayList<Spot>();
		spots.add(s1);
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		doReturn(spots).when(spotService).findSpotByCarColor("red");
		assertEquals(1, spotService.findSpotByCarColor(anyString()).size());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}




	@Test
	public void testFindSlotNoForColorCarsSuccess() {
		String color ="green";
		String regNo ="ma-01-1245";
		List<Spot> spots = new ArrayList<Spot>();
		spots.addAll(testData(color, regNo));
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals("S-1", spotService.findSpotByCarColor(anyString()).get(0).getId());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}
	
	@Test
	public void testFindSlotNoForColorCarsError() {
		String color ="green";
		List<Spot> spots = new ArrayList<Spot>();
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals(0, spotService.findSpotByCarColor(anyString()).size());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}

	@Test
	public void testFindSlotNoForRegNoSuccess() {
		String color ="white";
		String regNo ="ma-01-1245";
		List<Spot> spots = new ArrayList<Spot>();
		Vehicle v1 = new Vehicle();
		v1.setColor(color);
		v1.setNumber(regNo);
		Spot s1 = new Spot();
		s1.setId("S-1");
		s1.setVehical(v1);
		spots.add(s1);
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals(regNo, spotService.findSpotByCarColor(anyString()).get(0).getVehical().getNumber());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}
	
	@Test
	public void testFindSlotNoForRegNo() {
		String color ="white";
		List<Spot> spots = new ArrayList<Spot>();
		when(spotService.findSpotByCarColor(anyString())).thenReturn(spots);
		assertEquals(0, spotService.findSpotByCarColor(anyString()).size());
		parkHereExecutor.findRegNoForColorCars(color);
		verify(spotService).findSpotByCarColor(anyString());
	}

	private List<Spot> testData(String color, String regNo) {
		List<Spot> spots = new ArrayList<Spot>();
		Vehicle v1 = new Vehicle();
		v1.setColor(color);
		v1.setNumber(regNo);
		Spot s1 = new Spot();
		s1.setId("S-1");
		s1.setVehical(v1);
		
		Vehicle v2 = new Vehicle();
		v1.setColor("white");
		v1.setNumber("abcd-50-9874");
		Spot s2 = new Spot();
		s2.setId("S-2");
		s2.setVehical(v2);
		spots.add(s1);
		spots.add(s2);
		
		return spots;
	}
}
