package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightManagerTest {
	
	public static void main(String[] args) {
		testAddFlight_FiveParams_Success();
		testAddFlight_FiveParams_Fail();
		testAddFlight_FlightParam_Success();
		testAddFlight_FlightParam_Fail();
		testGetFlight_Success();
		testGetFlight_Fail();
		testGetFlightsByOrigin_Success();
		testGetFlightsByOrigin_Fail();
		testGetFlightsByOrigin_TwoParams_Success();
		testGetFlightsByOrigin_TwoParams_Fail();
		testGetFlightsByOriginAndDest_Success();
		testGetFlightsByOriginAndDest_Fail();
		testNumFlightsAndRemoveFlight_Success();
		testNumFlightsAndRemoveFlight_Fail();
	}
	/**
	 * Tests the addFlight method that accepts five parameters
	 * needed to create a flight and adds it to the manager if 
	 * the flight number does already exists.
	 */
	private static void testAddFlight_FiveParams_Success() {
		System.out.println("-->testAddFlight_FiveParams_Success()");
		
		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = new FlightManager(am);
		
		//Calls the method and holds it in a variable
		boolean added = fm.addFlight("2432", "2022-05-24", "ANB", "VLD", 150.95);
		
		//Expected and Actual
		String msgExpected="Expected: The flight should be added to the manager.";
		String msgActual="Actual: ";
		if(added) {
			msgActual+= "Flight has been added.";
		}
		else {
			msgActual+="A flight with that flight number already exists.";
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testAddFlight_FiveParams_Fail() {
		System.out.println("\n-->testAddFlight_FiveParams_Fail()");
		
		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = new FlightManager(am);
		
		//Calls the method and holds it in a variable
		fm.addFlight("2432", "2022-05-24", "ANB", "VLD", 150.95);
		boolean added = fm.addFlight("2432", "2021-12-10", "HSV", "CSG", 273.75);
		
		//Expected and Actual
		String msgExpected="Expected: The flight should not be added because of flight number.";
		String msgActual="Actual: ";
		if(added) {
			msgActual+= "Flight has been added.";
		}
		else {
			msgActual+="A flight with that flight number already exists.";
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	/**
	 * Tests the addFlight method that accepts
	 * a Flight object as the parameter.
	 */
	private static void testAddFlight_FlightParam_Success() {
		System.out.println("\n-->testAddFlight_FlightParam_Success()");

		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = new FlightManager(am);
		//Create Flight
		String number = "2432";
		LocalDate date = LocalDate.of(2021, 12, 20);
		Airport origin = am.getAirport("VLD");
		Airport dest =am.getAirport("HSV");
		Flight flight = new Flight(number, date, origin, dest, 143.50);
		
		//Holds call to method in variable
		boolean added = fm.addFlight(flight);
		
		//Expected and Actual
		String msgExpected = "Expected: The flight '"+flight.getNumber()+"' should be added to the manager.";
		String msgActual = "Actual: ";
		if(added) {
			msgActual+="Flight '"+flight.getNumber()+"' is added to the Flight Manager.";
		}
		else {
			msgActual+="This flight could not be added. There's already a flight with number '"+flight.getNumber()+"' in the manager.";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	private static void testAddFlight_FlightParam_Fail() {
		System.out.println("\n-->testAddFlight_FlightParam_Fail()");

		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = new FlightManager(am);
		//Create Flight
		String number = "2432";
		LocalDate date = LocalDate.of(2021, 12, 13); LocalDate date2 = LocalDate.of(2022, 05, 24);
		Airport origin = am.getAirport("VLD"); Airport origin2 = am.getAirport("ANB");
		Airport dest =am.getAirport("HSV"); Airport dest2 = am.getAirport("CSG");
		Flight flight = new Flight(number, date, origin, dest, 143.50);
		Flight flight2 = new Flight(number, date2, origin2, dest2, 238.35);
		
		//Holds call to method in variable
		fm.addFlight(flight);
		boolean added = fm.addFlight(flight2);
		
		//Expected and Actual
		String msgExpected = "Expected: The flight should not get added to to manager.";
		String msgActual = "Actual: ";
		if(added) {
			msgActual+="Flight '"+flight.getNumber()+"' is added to the Flight Manager.";
		}
		else {
			msgActual+="This flight could not be added. There's already a flight with number '"+flight.getNumber()+"' in the manager.";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	private static void testGetFlight_Success() {
		System.out.println("\n-->testGetFlight_Success()");
		
		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = fiveFlights();
		
		//Call to method
		String number = "1234";
		Flight found = fm.getFlight(number);
		
		//Expected and Actual
		String msgExpected="Expected: The result should be a print out of flight '"+number+"'.";
		String msgActual="Actual: ";
		if(found==null) {
			msgActual+="None of the flights in the manager have '"+number+"' as a flight number.";
		}
		else {
			msgActual+="The flight with '"+number+"' as a flight number:\n"+found;
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetFlight_Fail() {
		System.out.println("\n-->testGetFlight_Fail()");
		
		//Create airport and flight manager
		AirportManager am = getSmallAM();
		FlightManager fm = fiveFlights();
		
		//Call to method
		String number = "6456";
		Flight found = fm.getFlight(number);
		
		//Expected and Actual
		String msgExpected="Expected: The result should be a message saying that there isn't a flight with '"+number+"' as a flight number.";
		String msgActual="Actual: ";
		if(found==null) {
			msgActual+="None of the flights in the manager have '"+number+"' as a flight number.";
		}
		else {
			msgActual+="The flight with '"+number+"' as a flight number:\n"+found;
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	/**
	 * Tests the getNumFlights() and removeFlight(String num)
	 * method to verify that both methods work properly.
	 */
	private static void testNumFlightsAndRemoveFlight_Success() {
		System.out.println("\n-->testNumFlightsAndRemoveFlight_Success()");
		FlightManager fm = fiveFlights();
		
		//Call to method
		int beforeRemove=fm.getNumFlights();
		String num= "3456";
		Flight removed = fm.removeFlight(num);
		int afterRemove=fm.getNumFlights();
		
		//Expected and Actual
		String msgExpected = "Expected: The remove method should return a flight matching the number '"+num+"' and"
				+ " \nthe size of the Flight Manager should be one less than the previous size";
		String msgActual = "Actual: The size before removal: "+beforeRemove+", size after removal: "+afterRemove;
		if(removed==null) {
			msgActual+="\nThere is no Flight with the number '"+num+"' in the Flight Manager.";
		}
		else {
			msgActual+="\nFlight Removed:\n"+removed;
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testNumFlightsAndRemoveFlight_Fail() {
		System.out.println("\n-->testNumFlightsAndRemoveFlight_Fail()");
		FlightManager fm = fiveFlights();
		
		//Call to method
		int beforeRemove=fm.getNumFlights();
		String num= "7556";
		Flight removed = fm.removeFlight(num);
		int afterRemove=fm.getNumFlights();
		
		//Expected and Actual
		String msgExpected = "Expected: Should be a message saying that there is no flight matching the number '"+num+"' and"
				+ " \nthe size of the Flight Manager should be the same as before.";
		String msgActual = "Actual: The size before removal: "+beforeRemove+", size after removal: "+afterRemove;
		if(removed==null) {
			msgActual+="\nThere is no Flight with the number '"+num+"' in the Flight Manager.";
		}
		else {
			msgActual+="\nFlight Removed:\n"+removed;
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	/**
	 * Tests the getFlightsByOrigin that accepts
	 * one parameter to return all flight that
	 * matches the origin code.
	 */
	private static void testGetFlightsByOrigin_Success() {
		System.out.println("\n-->getFlightsByOrigin_Success()");
		FlightManager flights = multipleFlights();
		
		//Call to method
		String code = "VLD";
		List<Flight> outgoing = flights.getFlightsByOrigin(code);
		
		//Expected and Actual
		String msgExpected = "Expected: Should be a list of all the flights departing from '"+code+"'";
		String msgActual = "Actual: ";
		if(outgoing.isEmpty()) {
			msgActual+= "There are no flights leaving from '"+code+"'.";
		}
		else {
			msgActual+="Flights departing from '"+code+"':\n";
			for(int i=0;i<outgoing.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, outgoing.get(i));
			}
		}
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	private static void testGetFlightsByOrigin_Fail() {
		System.out.println("\n-->getFlightsByOrigin_Fail()");
		FlightManager flights = multipleFlights();
		
		//Call to method
		String code = "CHS";
		List<Flight> outgoing = flights.getFlightsByOrigin(code);
		
		//Expected and Actual
		String msgExpected = "Expected: Should be a message saying no flights are leaving from '"+code+"'";
		String msgActual = "Actual: ";
		if(outgoing.isEmpty()) {
			msgActual+= "There are no flights leaving from '"+code+"'.";
		}
		else {
			msgActual+="Flights departing from '"+code+"':\n";
			for(int i=0;i<outgoing.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, outgoing.get(i));
			}
		}
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	private static void testGetFlightsByOrigin_TwoParams_Success() {
		System.out.println("\n-->testGetFlightsByOrigin_TwoParams_Success()");
		FlightManager fm = multipleFlightsSameDate();
		
		//Call to method
		String code="VLD";
		String date="2022-10-24";
		List<Flight> originDate = fm.getFlightsByOrigin(code, date);
		
		//Expected and Actual
		String msgExpected="Expected: Should be a list of flight leaving from '"+code+"' on "+date+".";
		String msgActual="Actual: ";
		
		if(originDate.isEmpty()) {
			msgActual+= "There are no flights leaving from '"+code+"' on "+date+".";
		}
		else {
			msgActual+="Flights departing from '"+code+"' on "+date+":\n";
			for(int i=0;i<originDate.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, originDate.get(i));
			}
		}
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	private static void testGetFlightsByOrigin_TwoParams_Fail() {
		System.out.println("\n-->testGetFlightsByOrigin_TwoParams_Fail()");
		FlightManager fm = multipleFlightsSameDate();
		
		//Call to method
		String code="CHS";
		String date="2022-10-24";
		List<Flight> originDate = fm.getFlightsByOrigin(code, date);
		
		//Expected and Actual
		String msgExpected="Expected: Should be a message that no flights are leaving from '"+code+"' on "+date+".";
		String msgActual="Actual: ";
		
		if(originDate.isEmpty()) {
			msgActual+= "There are no flights leaving from '"+code+"' on "+date+".";
		}
		else {
			msgActual+="Flights departing from '"+code+"' on "+date+":\n";
			for(int i=0;i<originDate.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, originDate.get(i));
			}
		}
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * 
	 * 
	 */
	private static void testGetFlightsByOriginAndDest_Success() {
		System.out.println("\n-->testGetFlightsByOriginAndDest_Success()");
		FlightManager fm = multipleFlightsSameDate();
		
		//Call to method
		String origin="VLD";
		String date="2022-10-24";
		String dest="JAX";
		List<Flight> originAndDest = fm.getFlightsByOriginAndDestination(origin,dest, date);
		
		//Expected and Actual
		String msgExpected="Expected: Should be a list of Flights that are traveling from '"+origin+"' to '"+dest+"' on "+date+".";
		String msgActual="Actual: ";
		
		if(originAndDest.isEmpty()) {
			msgActual+= "There are no flights traveling from '"+origin+"' to '"+dest+"' on "+date+".";
		}
		else {
			msgActual+="Flights traveling from '"+origin+"' to '"+dest+"' on "+date+":\n";
			for(int i=0;i<originAndDest.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, originAndDest.get(i));
			}
		}
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	private static void testGetFlightsByOriginAndDest_Fail() {
		System.out.println("\n-->testGetFlightsByOriginAndDest_Fail()");
		FlightManager fm = multipleFlightsSameDate();
		
		//Call to method
		String origin="CHS";
		String date="2022-10-24";
		String dest="JAX";
		List<Flight> originAndDest = fm.getFlightsByOriginAndDestination(origin,dest, date);
		
		//Expected and Actual
		String msgExpected="Expected: Should be a message that no flights are traveling from '"+origin+"' to '"+dest+"' on "+date+".";
		String msgActual="Actual: ";
		
		if(originAndDest.isEmpty()) {
			msgActual+= "There are no flights traveling from '"+origin+"' to '"+dest+"' on "+date+".";
		}
		else {
			msgActual+="Flights traveling from '"+origin+"' to '"+dest+"' on "+date+":\n";
			for(int i=0;i<originAndDest.size();i++) {
				msgActual+=String.format("%d.) %s%n%n", i+1, originAndDest.get(i));
			}
		}
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	
	private static AirportManager getSmallAM() {
		AirportManager am = new AirportManager(AirportLoader.getAirportMap(AirportManagerTest.airportsSmall));
		return am;
	}
	
	private static AirportManager getMediumAM() {
		AirportManager am = new AirportManager(AirportLoader.getAirportMap(AirportManagerTest.airportsMedium));
		return am;
	}
	
	private static FlightManager fiveFlights() {
		AirportManager am = getSmallAM();
		List<Airport> list1 = am.getAirports();
		FlightManager fm = new FlightManager(am);
		String[] nums = {"1234", "2434", "6543", "2345", "3456"};
		double[] prices = {134.70, 189.35, 275.80, 343.23, 123.47};
		int[] daysOfYear = {234, 170, 50, 297, 354};
		
		for(int i=0;i<nums.length;i++) {
			for(int j=list1.size()-1;j>0;j--) {
				Airport origin = list1.get(i);
				Airport dest = list1.get(j);
				if(!origin.equals(dest)) {
					String num=nums[i];
					double cost=prices[i];
					LocalDate date = LocalDate.ofYearDay(2022, daysOfYear[i]);

					Flight f = new Flight(num, date,origin, dest, cost);
					fm.addFlight(f);
				}
			}
		}
		
		return fm;
	}
	
	private static FlightManager multipleFlights() {
		FlightManager fm = new FlightManager(getSmallAM());
		AirportManager am = getSmallAM();
		int numNeeded= am.getAirports().size()*am.getAirports().size();
		ArrayList<String> flightNum = getFlightNum(flightNumbers, numNeeded);
		int index=0;
			for(Airport origin: am.getAirports()) {
				for(Airport dest: am.getAirports()) {
					if(!origin.equals(dest)) {
						double cost= am.getDistanceBetween(origin, dest)*1.06;
						String num=flightNum.get(index);
						LocalDate date=LocalDate.now();
						Flight f = new Flight(num,date,origin,dest,cost);
						fm.addFlight(f);
						index++;
					}
				}
			}
		return fm;	
	}
	
	private static FlightManager multipleFlightsSameDate() {
		FlightManager fm = new FlightManager(getMediumAM());
		AirportManager am = selectedAirports();
		int numNeeded=am.getAirports().size()*getSmallAM().getAirports().size();
		ArrayList<String> flightNum = getFlightNum(flightNumbers, numNeeded);
		int[] daysOfYear = {234, 170, 297, 350, 50};
		int index=0;
		for(Airport origin: getSmallAM().getAirports()) {
			for(Airport dest: am.getAirports()) {
				if(!origin.equals(dest)) {
					int remainder=index%5;
					double cost=am.getDistanceBetween(origin, dest)*1.06;
					String num=flightNum.get(index);
					LocalDate date = LocalDate.ofYearDay(2022, daysOfYear[remainder]);
					Flight f = new Flight(num, date, origin, dest, cost);
					fm.addFlight(f);
					index++;
				}
			}
		}
		return fm;
		
		
	}
	
	private static ArrayList<String> getFlightNum(File flightNumsFile, int numberNeeded){
		ArrayList<String> flightNums = new ArrayList<>();
		
		try {
			Scanner input = new Scanner(flightNumsFile);
			int numInAL=0;
			while(numInAL<numberNeeded) {
				String num = input.next();
				flightNums.add(num);
				numInAL++;
			}
			input.close();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}
		return flightNums;
	}
	
	private static AirportManager selectedAirports() {
		AirportManager am = getMediumAM();
		List<Airport> airports = new ArrayList<>(am.getAirports());
		Map<String, Airport> selectedMap= new HashMap<String, Airport>();
		AirportManager selected = new AirportManager(selectedMap);
		int divisor=11;
		for(int i=0;i<airports.size();i++) {
			if(i%divisor==0) {
				Airport a= airports.get(i);
				selectedMap.put(a.getCode(), a);
			}
		}
		return selected;
	}
	
	private static final File flightNumbers = new File("src/student/FlightNumbers");
	

}
