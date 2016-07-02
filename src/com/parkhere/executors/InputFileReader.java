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
			System.out.println("Try Valid Option\n 1.Park Vehical\t park 'vehicle number'\n" +
					"2.Leave Parking Space\t leave 'slot number' \n" +
					"3.Park Status\t status \n" +
					"4.Reg No For Color Vehical\t registration_numbers_for_cars_with_colour 'color of vehicle' \n" +
					"5.Reg No For Color Vehical\t slot_numbers_for_cars_with_colour 'color of vehicle' \n" +
					"6.Reg No For Color Vehical\t slot_number_for_registration_number 'reg no of vehicle' \n");
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
		System.out.println("Enter File Path");
		String filePath = br.readLine();
		if (filePath.trim().equals("") || null == filePath) {
			//E:/SearchCoderWS/ParkHere/documentation/Input.txt
			System.out.println("File Path Required");
			return;
		}
		
		in.readInputFile(filePath);
	}

}
