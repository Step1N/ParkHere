package com.parkhere.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.parkhere.models.ParkingTime;
import com.parkhere.models.Person;
import com.parkhere.models.Spot;
import com.parkhere.models.SpotStatus;
import com.parkhere.models.SpotType;
import com.parkhere.models.Spots;
import com.parkhere.models.Vehicle;
import com.parkhere.service.SpotHistoryService;
import com.parkhere.service.SpotService;
import com.parkhere.utils.DateUtils;
import com.parkhere.utils.ParkhereUtils;


public class SpotServiceImpl implements SpotService {
	private Map<String, Spot> mapSpot;
	private DateUtils dateUtils;
	private ParkhereUtils parkUtil;
	private SpotHistoryService spotHistoryService;

	public SpotServiceImpl() {
		mapSpot = new TreeMap<String, Spot>();
		dateUtils = new DateUtils();
		parkUtil = new ParkhereUtils();
		spotHistoryService = new SpotHistoryServiceImpl();
	}

	@Override
	public void createSpots(String prefix, int numbers) {
		Spots spot = new Spots(numbers);
		for (int i = 0; i < numbers; i++) {
			mapSpot.put(prefix + "-" + i, spot.getSpots().get(i));
		}
		System.out.println("Created a parking lot with "+numbers+" slots");
	}
	

	@Override
	public Spot parkVehicle(Vehicle vehical) {
		List<Spot> slots = new ArrayList<Spot>(mapSpot.values());
		Spot s = new Spot();
		if (isSpotAvailable(mapSpot.entrySet())) {
			s = findClosestSpot(slots);
			if (null != s) {
				// TODO Check availability in next hour
				// TODO Check whether slot under maintenance
				// TODO Find navigation
				// TODO Any special service required
				// TODO Generate Payment
				// TODO Generate Receipt
				Person driver = new Person();
				vehical.setDriver(driver);
				vehical.setSpotID(s.getId());
				s.setVehical(vehical);
				s.setIsFree(false);
				s.setIsAdvanceBooking(false);
				s.setStatus(SpotStatus.OCCUPIED);
				createParkingTime(s, vehical);
				System.out.println("Allocated slot number: "+parkUtil.getIDNumber(s.getId()));
			}
		}else{
			System.out.println("Slots are completly occupied");
		}

		return s;
	}
	
	@Override
	public List<Spot> findSpotByCarColor(String color){
		List<Spot> spots= findSpotByDate(new Date());
		List<Spot> colorSpot = new ArrayList<Spot>();
		for(Spot s : spots){
			Vehicle v = s.getVehical();
			if(color.equals(v.getColor())){
				colorSpot.add(s);
			}
		}
		
		return colorSpot;
	}
	
	@Override
	public List<Spot> findSpotForRegNo(String regNo){
		List<Spot> spots= findSpotByDate(new Date());
		List<Spot> regSpot = new ArrayList<Spot>();
		for(Spot s : spots){
			Vehicle v = s.getVehical();
			if(regNo.equals(v.getNumber())){
				regSpot.add(s);
			}
		}
		
		return regSpot;
	}
	
	private List<Spot> findSpotByDate(Date date){
		List<List<ParkingTime>> parkinHystry = new ArrayList<List<ParkingTime>>();
		for(Entry<String, Spot> s : mapSpot.entrySet()){
			parkinHystry.add(s.getValue().getParkingTimes());
		}
		
		List<ParkingTime> pts = spotHistoryService.getParkingTimeByDay(parkinHystry, date);
		List<Spot> spots = new ArrayList<Spot>();
		for(ParkingTime pt : pts){
			Spot sp = new Spot();
			sp.setVehical(pt.getVehicle());
			sp.setId(pt.getVehicle().getSpotID());
			spots.add(sp);
		}
		
		return spots;
	}
	
	//TODO
	public void unParkVehicle(Vehicle vehicle){
		//TODO Find Payment Due
		//TODO Find Any Maintenance any due
		//TODO Update history
		List<Spot> spots = new ArrayList<Spot>(mapSpot.values());
		Spot s = findSpotByID(spots, vehicle.getSpotID());
		Vehicle spotVehicle = s.getVehical();
		if(vehicle.getSpotID().equals(s.getId())){
			boolean verified = verifyVehicle(spotVehicle, vehicle);
			if(verified){
				freedSpot(s, spotVehicle);
			}
		}
	}
	
	private void createParkingTime(Spot spot, Vehicle vehicle){
		ParkingTime pt = new ParkingTime();
		pt.setChekinTime(new Date());
		pt.setVehicle(vehicle);
		spot.getParkingTimes().add(pt);
	}
	
	@Override
	public void freedSpot(Spot spot, Vehicle vehicle){
		List<ParkingTime> parkingTimes = spot.getParkingTimes();
		for(ParkingTime pt : parkingTimes){
			if(pt.getVehicle().getNumber().equals(vehicle.getNumber())){
				pt.setChekoutTime(new Date());
				String duration = dateUtils.getDiff(pt.getChekinTime(), pt.getChekoutTime());
				pt.setDuration(duration);
			}
		}
		
		spot.setIsFree(true);
		spot.setIsAdvanceBooking(false);
		spot.setStatus(SpotStatus.EMPTY);
		System.out.println("Slot number "+parkUtil.getIDNumber(spot.getId())+" is free");
	}

	private boolean verifyVehicle(Vehicle spotVehical, Vehicle vehicle){
		boolean verifiedVehicle = false;
		if(spotVehical.getNumber().equals(vehicle.getNumber())){
			if(verifyDriverIdentity(spotVehical.getDriver(), vehicle.getDriver())){
				verifiedVehicle = true;
			}
		}
		
		return verifiedVehicle;
	}
	
	private boolean verifyDriverIdentity(Person spotVehicleDriver, Person driver){
		boolean driverVerified = false;
		if(spotVehicleDriver.getDlID().equals(driver.getDlID()) && 
				spotVehicleDriver.getEmailID().equals(driver.getEmailID())){
			driverVerified = true;
		}
		else{
			//TODO Verify driver identity
		}
		
		return driverVerified;
	}
	
	private Spot findSpotByID(List<Spot> spots, String sID){
		Spot existingSpot = new Spot();
		for(Spot s :spots){
			if(s.getId().equals(sID)){
				existingSpot =s; 
			}
		}
		
		return existingSpot;
	}
	
	@Override
	public List<Spot> findSpotByVehicleType(List<Spot>spots, String type){
		List<Spot>freeSpot = new ArrayList<Spot>();
		for (Spot s : spots){
			if(s.getIsFree() && s.getIsAdvanceBooking()){
				if(s.getVehical().getType().equals(SpotType.valueOf(type))){
					freeSpot.add(s);
				}
			}
		}
		
		return freeSpot;
	}
	
	private boolean checkForAdvanceBooking(Spot s){
		if(s.getIsAdvanceBooking()){
			return true;
		}
		
		return false;
	}
	
	private Person generateDriverDetails(int contactNumber, String name, String lID, String emailID){
		Person driver = new Person();
		driver.setDlID(lID);
		driver.setEmailID(emailID);
		driver.setName(name);
		driver.setContactNumber(contactNumber);
		
		return driver;
	}
	
	private Vehicle generateVehicleDetails(String number, String color, Person driver){
		Vehicle vehicle = new Vehicle();
		vehicle.setColor(color);
		vehicle.setNumber(number);
		vehicle.setDriver(driver);
		
		return vehicle;
	}
	
	@Override
	public void findSpotsStatus() {
		List<Spot> spots = new ArrayList<Spot>(mapSpot.values());
		findSpotsStatus(spots);
	}

	
	private void findSpotsStatus(List<Spot> spots) {
		System.out.println("Slot No.\tRegistration No\t Colour");
		for (Spot s : spots) {
			if(SpotStatus.OCCUPIED.equals(s.getStatus())){
				Vehicle v =s.getVehical();
				System.out.println(s.getId()+"\t\t"+v.getNumber()+"\t "+v.getColor());
			}
		}
	}

	private boolean isSpotAvailable(Set<Entry<String, Spot>> slotEntry) {
		boolean isFree = false;
		for (Entry<String, Spot> sen : slotEntry) {
			Spot s = sen.getValue();
			if (s.getIsFree()) {
				isFree = true;
				break;
			}
		}

		return isFree;
	}

	private Spot findClosestSpot(List<Spot> slots) {
		int minDistance = Integer.MAX_VALUE;
		Spot closestSlot = null;
		for (Spot s : slots) {
			if (s.getDistanceFromFront() < minDistance && (s.getIsFree() || s.getIsAdvanceBooking())) {
				minDistance = s.getDistanceFromFront();
				closestSlot = s;
				break;
			}
		}

		return closestSlot;
	}

	@Override
	public void leavSpotForParking(String prefix, int number) {
		for (Entry<String, Spot> slotentry : mapSpot.entrySet()) {
			String spotKey = slotentry.getKey();
			if (spotKey.equals(prefix + "-" + (number-1))) {
				Spot spot = slotentry.getValue();
				freedSpot(spot, spot.getVehical());
			}
		}
	}

}
