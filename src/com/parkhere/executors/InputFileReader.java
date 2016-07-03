package com.parkhere.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.parkhere.exceptions.ParkHereException;
import com.parkhere.utils.Constants;

public class InputFileReader {
	private static final String INSTRUCTTION = "Select Option" +
			"\n1.Create parking slots\t\t create_parking_lot 'number parking slots' " +
			"\n2.Park Vehical\t\t\t park 'vehicle number' 'vehical color' " +
			"\n3.Leave Parking Space\t\t leave 'slot number'" +
			"\n4.Park Status\t\t\t status" +
			"\n5.Reg No For Color Vehical\t registration_numbers_for_cars_with_colour 'color of vehicle'" +
			"\n6.Reg No For Color Vehical\t slot_numbers_for_cars_with_colour 'color of vehicle'" +
			"\n7.Reg No For Color Vehical\t slot_number_for_registration_number 'reg no of vehicle'";
	ParkHereExecutor exe;
	public InputFileReader(){
		exe = new ParkHereExecutor();
	}
	
	public void readInputFile(String filePath) throws IOException{
		Scanner sc = new Scanner(Paths.get(filePath));
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			triggerExecutor(line);
		}
	}
	
	public boolean validateInput(String [] words, int shouldHave){
		boolean isValid = false;
		if (words.length ==  shouldHave){
			isValid = true;
		}
		
		return isValid;
	}
	
	public void triggerExecutor(String line) {
		String[] words = line.split(" ");
		switch (words[0]) {
		case Constants.PARK:
			if(!validateInput(words, 3))break;
			exe.parkVehical(words[1], words[2]);
			break;
		case Constants.LEAVE:
			if(!validateInput(words, 2))break;
			Integer number =Integer.valueOf(words[1]);
			exe.leaveSlotNumber(number);
			break;
		case Constants.STATUS:
			if(!validateInput(words, 1))break;
			exe.parkingStatus();
			break;
		case Constants.CREATE_PARKING_SLOTS:
			if(!validateInput(words, 2))break;
			Integer slots =Integer.valueOf(words[1]);
			exe.createParkinLot(slots);
			break;
		case Constants.REG_NO_FOR_COLOR_CARS:
			if(!validateInput(words, 2))break;
			exe.findRegNoForColorCars(words[1]);
			break;
		case Constants.SLOT_NO_FOR_COLOR_CARS:
			if(!validateInput(words, 2))break;
			exe.findSlotNoForColorCars(words[1]);
			break;
		case Constants.SLOT_NO_FOR_REG_NO:
			if(!validateInput(words, 2))break;
			exe.findSlotNoForRegNo(words[1]);
			break;
		default:
			System.out.println(INSTRUCTTION);
			break;
		}
	}
	
	public void writeInputFile(String filePath, List<String> data) throws ParkHereException{
		Path file = Paths.get(filePath);
		try {
			Files.write(file, data, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new ParkHereException("Error while writing flie", e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		InputFileReader in = new InputFileReader();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you have a file containing input say Y for Yes or N for No");
		String doYou = br.readLine();
		if("Y".equalsIgnoreCase(doYou)){
			System.out.println("Provide file path");
			String filePath = br.readLine();
			if (filePath.trim().equals("") || null == filePath) {
				//E:/SearchCoderWS/ParkHere/documentation/Input.txt
				System.out.println("File Path Required");
				return;
			}
			in.readInputFile(filePath);
		}else if("N".equalsIgnoreCase(doYou)){
			String cont = "c";
			do{
				System.out.println(INSTRUCTTION);
				String line = br.readLine();
				String[] command = line.split(" ");
				boolean isValid = in.validateInput(command[0]);
				if(!isValid){
					System.out.println("Invalid input try again");
					continue;
				}
				in.triggerExecutor(line);
				System.out.println("C to continue otherwise press any key for exit");
				cont = br.readLine();
			}while(cont.equalsIgnoreCase("c"));
			
			System.out.println("Exited!!");
		}
	}
	
	private boolean validateInput(String input){
		if(Constants.CREATE_PARKING_SLOTS.equals(input)){
			return true;
		}
		if(Constants.PARK.equals(input)){
			return true;
		}
		if(Constants.LEAVE.equals(input)){
			return true;
		}
		if(Constants.STATUS.equals(input)){
			return true;
		}
		if(Constants.REG_NO_FOR_COLOR_CARS.equals(input)){
			return true;
		}
		
		if(Constants.SLOT_NO_FOR_COLOR_CARS.equals(input)){
			return true;
		}
		if(Constants.SLOT_NO_FOR_REG_NO.equals(input)){
			return true;
		}
		return false;
	}

}
