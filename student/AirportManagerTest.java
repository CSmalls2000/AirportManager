package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


public class AirportManagerTest {
	
	public static void main(String[] args) {
		
		testConstructorAndGetAirports();
		testGetAirport_Success();
		testGetAirport_Fail();
		testGetAirportClosestTo();
		testGetAirportsByCity();
		testGetAirportsByCityState();
		testGetAirportsClosestTo();
		testGetAirportsSortedBy_City();
		testGetAirportsSortedBy_State();
		testGetAirportsSortedBy_Invalid();
		testGetAirportsWithin_LatLon();
		testGetAirportsWithin_OneAirport();
		testGetAirportsWithinTwoAirports();
		testGetDistanceBetween();
		testNWSNamedAirports();
			
	}
	
	/**
	 * This test will see if the constructor will 
	 * create the AirportManager properly.
	 */
	private static void testConstructorAndGetAirports() {
		System.out.println("-->testConstructorandGetAirports()");
		AirportManager am = new AirportManager(fiveAirports());
		List<Airport> airportList = am.getAirports();
		System.out.println("\nAirports in the Airport Manager:");
		for(int i=0;i<airportList.size();i++) {
				System.out.println((i+1)+". "+airportList.get(i));
			}
	}
	
	/**
	 * A successful and fail will be used
	 * to test the getAirport method.
	 */
	private static void testGetAirport_Success() {
		System.out.println("\n-->testGetAirport_Success()");
		
		AirportManager am = new AirportManager(fiveAirports());
		
		//Creation of the key and variable holder
		String code = "SDL";
		Airport a = am.getAirport(code);
		
		//Expected and Actual Strings
		String msgExpected = "Expected: The method should return the airport that match the code '"+code+"'.";
		String msgActual = "Actual: ";
		if(a==null) {
			msgActual += "There is no airport that has the code '"+code+"' in the Airport Manager.";
		}
		else {
			msgActual += "The airport that has the code '"+code+"' is Airport - "+a; 
		}
		
		//Results 
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetAirport_Fail() {
		System.out.println("\n-->testGetAirport_Fail()");
		
		AirportManager am = new AirportManager(fiveAirports());
		
		//Creation of the key and variable holder
		String code = "GVT";
		Airport a = am.getAirport(code);
		
		//Expected and Actual Strings
		String msgExpected = "Expected: The method should return a null object and the message should say that \n\t there is not an airport in the Airport Manager that mtches the code '"+code+"'.";
		String msgActual = "Actual: ";
		if(a==null) {
			msgActual += "There is no airport that has the code '"+code+"' in the Airport Manager.";
		}
		else {
			msgActual += "The airport that has the code '"+code+"' is Airport - "+a; 
		}
		
		//Results 
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * 1.) This test will check to see if the Airport closest 
	 *  to the parameter will be returned.
	 * 
	 * 2.) This test will also check if the getDistanceBetween method 
	 *  to verify that the airport closest to the airport passed 
	 *  in the parameter is returned.
	 */
	private static void testGetAirportClosestTo() {
		System.out.println("\n-->testGetAirportClosestTo()");
		AirportManager airportManager = new AirportManager(airportClosest());
		
		double[] distArray = new double[airportManager.getAirports().size()];
		Airport loc = new Airport("PDK", 33.88, 84.3, "Atlanta/Dklb", "GA");
		
		//Grabs all the distances in the manager and puts them in array
		List<Airport> airports = airportManager.getAirports();
		for(int i=0; i<airports.size();i++) {
			Airport a = airports.get(i);
			double dist = airportManager.getDistanceBetween(loc, a);
			distArray[i]=dist;
		}
		
		//Variables
		Airport closest = airportManager.getAirportClosestTo(loc.getCode());
		double minDistforDA = getMin(distArray);
		double closestDist=airportManager.getDistanceBetween(loc, closest);
		
		//Expected and Actual
		String msgExpected = String.format("The airport closest to Airport-%s should be the airport that is %.2f miles away.", loc, minDistforDA);
		String msgActual = String.format("The airport that is closest to Airport-%s is Airport-%s and it is %.2f miles away.", loc, closest, closestDist);
		//Outcomes
//		for(int j=0;j<distArray.length;j++) {
//			System.out.print(String.format("%.1f", distArray[j]) +" ");
//		}
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
		
		
		
		
	}
	
	/**
	 * This test will see if airports that have the same city as the parameter 
	 * regardless of the state will be returned in the form of a list.
	 */
	private static void testGetAirportsByCity() {
		System.out.println("\n-->testGetAirportsByCity()");
		AirportManager airports = new AirportManager(sameCities());
		
		//Parameter and list
		String city = "Greenville";
		List<Airport> cityAirports = airports.getAirportsByCity(city);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of all the Airports in a city named "+city;
		String msgActual = "";
		for(int i=0; i<cityAirports.size();i++) {
			msgActual += (i+1)+". "+ cityAirports.get(i)+"\n";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * This test will be similar to the getAirportsByCity test, however,
	 * both the city and the state have to match in order to be 
	 * placed in the list.
	 */
	private static void testGetAirportsByCityState() {
		System.out.println("\n-->testGetAirportsByCityState()");
		AirportManager airports = new AirportManager(sameCities());
		
		//Parameter and list
		String city = "Greenville";
		String state = "SC";
		List<Airport> cityStateAirports = airports.getAirportsByCityState(city, state);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of all the Airports located in '"+city+", "+state+"' .";
		String msgActual = "";
		for(int i=0; i<cityStateAirports.size();i++) {
			msgActual += (i+1)+". "+ cityStateAirports.get(i)+"\n";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * This test will check if the requested number of airports 
	 * closest to the airport passed in the parameter are 
	 * returned in the list.
	 */
	private static void testGetAirportsClosestTo() {
		System.out.println("\n-->testGetAirportsClosestTo()");
		AirportManager aManager = new AirportManager(airportsClosestToMultiple());
		
		//parameters
		int num = 10;
		String code = "VLD";
		List<Airport> airportsClosest = aManager.getAirportsClosestTo(code, num);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of "+num+" airports closest to the Airport with this code '"+code+"'";
		String msgActual = "Actual: The "+num+ " airports closest to Airport '"+code+"': \n";
		for(int i=0; i<airportsClosest.size();i++) {
			msgActual += String.format("%d. %s, distance from '%s': %.2f miles", i+1, airportsClosest.get(i),code, aManager.getDistanceBetween(code, airportsClosest.get(i).getCode()))+"\n";
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * Tests the getAirportsSortedBy method
	 * (1) sorts by state
	 * (2) sorts by city
	 * (3) invalid parameter
	 */
	private static void testGetAirportsSortedBy_City() {
		System.out.println("\n-->testGetAirportsSortedBy_City()");
		AirportManager airports = new AirportManager(fiveAirports());
		
		//Parameter and Variable
		String sortType = "City";
		List<Airport> sorted = airports.getAirportsSortedBy(sortType);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of the airports sorted by "+sortType.toLowerCase();
		String msgActual = "Actual: \n";
		if(sorted==null) {
			msgActual = "The sort type '"+sortType+"' is invalid."; 
		}
		else {
			for(int i=0;i<sorted.size();i++) {
				msgActual+=(i+1)+". "+sorted.get(i)+"\n";
			}
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetAirportsSortedBy_State() {
		System.out.println("\n-->testGetAirportsSortedBy_State()");
		AirportManager airports = new AirportManager(fiveAirports());
		
		//Parameter and Variable
		String sortType = "State";
		List<Airport> sorted = airports.getAirportsSortedBy(sortType);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of the airports sorted by "+sortType.toLowerCase();
		String msgActual = "Actual: \n";
		if(sorted==null) {
			msgActual = "The sort type '"+sortType+"' is invalid."; 
		}
		else {
			for(int i=0;i<sorted.size();i++) {
				msgActual+=(i+1)+". "+sorted.get(i)+"\n";
			}
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetAirportsSortedBy_Invalid() {
		System.out.println("\n-->testGetAirportsSortedBy_Invalid()");
		AirportManager airports = new AirportManager(fiveAirports());
		
		//Parameter and Variable
		String sortType = "Code";
		List<Airport> sorted = airports.getAirportsSortedBy(sortType);
		
		//Expected and Actual
		String msgExpected = "The expected result should be an error message because '"+sortType.toLowerCase()+"' is an invalid sort type.";
		String msgActual = "Actual: \n";
		if(sorted==null) {
			msgActual = "The sort type '"+sortType+"' is invalid."; 
		}
		else {
			for(int i=0;i<sorted.size();i++) {
				msgActual+=(i+1)+". "+sorted.get(i)+"\n";
			}
		}
		
		//Outcomes
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	/**
	 * These will test the overloaded getAirportsWithin method.
	 * (1) getAirportsWithin(double, double, double)
	 * (2) getAirportsWithin(String, double)
	 * (3) getAirportsWithin(String, String, double)
	 */
	private static void testGetAirportsWithin_LatLon() {
		System.out.println("\n-->testGetAirportsWithin_LatLon()");
		AirportManager aManager = new AirportManager(AirportLoader.getAirportMap(airports));
		
		//Parameters and list
		double lat = 30.78;
		double lon = 83.28;
		double dist = 100.0;
		List<Airport> airportsWithinDist = aManager.getAirportsWithin(dist, lat, lon);
		
		//Expected and Actual
		String msgExpected = "The expected result should be a list of all the airports in the manager that are within "+dist+" miles of latitude '"+lat+"' longitude '"+lon+"'";
		String msgActual = "The list of airports and the distance:\n";
		for(int i=0; i<airportsWithinDist.size();i++) {
			Airport a = airportsWithinDist.get(i);
			double aDist = DistanceCalculator.getDistance(lat, lon, a.getLatitude(), a.getLongitude());
			msgActual+=String.format("%d. %s, distance: %.2f miles", i+1, a, aDist)+"\n";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetAirportsWithin_OneAirport() {
		System.out.println("\n-->testGetAirportsWithin_OneAirport()");
		AirportManager aManager = new AirportManager(AirportLoader.getAirportMap(airports));
		
		//Parameters and list
		Airport search = aManager.getAirport("VLD");
		double dist = 100.0;
		List<Airport> airportsWithinDist = aManager.getAirportsWithin(search.getCode(), dist);
		
		//Expected and Actual
		String msgExpected = "Expected: The expected result should be a list of all the airports in the manager that are within "+dist+" miles of Airport-"+search;
		String msgActual = "Actual: The list of airports and the distance:\n";
		for(int i=0; i<airportsWithinDist.size();i++) {
			Airport a = airportsWithinDist.get(i);
			double aDist = DistanceCalculator.getDistance(search.getLatitude(), search.getLongitude(), a.getLatitude(), a.getLongitude());
			msgActual+= String.format("%d. %s, distance: %.2f miles", i+1, a, aDist)+"\n";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	private static void testGetAirportsWithinTwoAirports() {
		System.out.println("\n-->testGetAirportsWithin_TwoAirports()");
		AirportManager aManager = new AirportManager(AirportLoader.getAirportMap(airports));
		
		//Parameters and list
		Airport search1 = aManager.getAirport("AND");
		Airport search2 = aManager.getAirport("CHS");
		double dist = 150.0;
		List<Airport> airportsWithinDist = aManager.getAirportsWithin(search1.getCode(), search2.getCode(), dist);
		
		//Expected and Actual
		String msgExpected = "Expected: The expected result should be a list of Airports in the manager that are within "+
							dist+ " miles of both Airport-"+search1+" and Airport-"+search2;
		String msgActual = "Actual: Airports within "+dist+" miles of '"+search1.getCode()+"' and '"+search2.getCode()+"'\n";
		for(int i=0;i<airportsWithinDist.size();i++) {
			Airport a = airportsWithinDist.get(i);
			double dist1 = DistanceCalculator.getDistance(search1.getLatitude(), search1.getLongitude(), a.getLatitude(), a.getLongitude());
			double dist2 = DistanceCalculator.getDistance(search2.getLatitude(), search2.getLongitude(), a.getLatitude(), a.getLongitude());
			msgActual+= String.format("%d. %s, Distance from '%s': %.2f miles, Distance from '%s': %.2f miles",i+1, a, search1.getCode(), dist1,search2.getCode(),dist2)+"\n";
			
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	/**
	 * This will test the overloaded getDistanceBetween method.
	 * (1) getDistanceBetween(String, String) 
	 */
	private static void testGetDistanceBetween() {
		System.out.println("\n-->testGetDistanceBetween()");
		AirportManager airports = new AirportManager(fiveAirports());
		
		//Parameter and Variable
		String code1 = "ANB";
		String code2 = "SDL";
		Airport a1 = airports.getAirport(code1);
		Airport a2 = airports.getAirport(code2);
		double dist = airports.getDistanceBetween(code1, code2);
		
		//Expected and Actual
		String msgExpected = "Expected: The expected result should be the distance between Airport-"+a1+" and Airport-"+a2;
		String msgActual = "Actual: "+String.format("The distance between Airport-%s and Airport-%s is %.2f miles", a1, a2, dist);
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
		
	}
	
	/**
	 * This will be used to test the getNWSNamedAirports
	 * and it should return all the airports that have "X"
	 * as the third character.
	 */
	private static void testNWSNamedAirports() {
		System.out.println("\n-->testNWSNamedAirports()");
		AirportManager airports = new AirportManager(nSWNamed());
		
		//List
		List<Airport> nWS = airports.getNWSNamedAirports();
		
		//Expected and Actual
		String msgExpected = "Expected: The expected result should be a list of all the airports that has an 'X' in the third character of its code.";
		String msgActual = "Actual: NWS named Airports: \n";
		for(int i=0;i<nWS.size();i++) {
			msgActual+=(i+1)+". "+nWS.get(i)+"\n";
		}
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}
	
	//----------------------------------
	// Helper Methods
	//----------------------------------
	
	private static Map<String,Airport> fiveAirports(){
		Map<String, Airport> airport = new HashMap<String,Airport>();
		
		//Creating airport objects
		Airport a1 = new Airport("ANB", 33.58, 85.85, "Anniston", "AL");
		Airport a2 = new Airport("ANC", 61.17, 150.02, "Anchorage", "AK");
		Airport a3 = new Airport("SDL", 33.62, 111.92, "Scottsdale", "AZ");
		Airport a4 = new Airport("GUC", 38.55, 106.92, "Gunnison", "CO");
		Airport a5 = new Airport("HUF", 39.45, 87.3, "TerreHaute", "IN");
		
		airport.put(a1.getCode(), a1);
		airport.put(a2.getCode(), a2);
		airport.put(a3.getCode(), a3);
		airport.put(a4.getCode(), a4);
		airport.put(a5.getCode(), a5);
		
		return airport;
	}
	
	private static Map<String, Airport> sameCities(){
		Map<String, Airport> airports = AirportLoader.getAirportMap(airportsSameCities);
		return airports;
	}
	
	private static Map<String, Airport> nSWNamed(){
		Map<String, Airport> airport = AirportLoader.getAirportMap(airports);
		return airport;
		
	}
	
	private static Map<String, Airport> airportsClosestToMultiple(){
		Map<String, Airport> airportMap = AirportLoader.getAirportMap(airports);
		return airportMap;
	}
	
	private static Map<String, Airport> airportClosest(){
		Map<String, Airport> airportMap = AirportLoader.getAirportMap(airportsSmall);
		return airportMap;
	}
	
	private static double getMin(double[] vals) {
		if(vals.length==0) {
			return Double.MAX_VALUE;
		}
		else {
			return getMin(vals, 0, Double.MAX_VALUE);
		}
	}
	
	private static double getMin(double[] vals, int loc, double min) {
		if(loc==vals.length) {
			return min;
		}
		else{
			double num = vals[loc];
			if(num<min) {
				min = num;
			}
			return getMin(vals, loc+1, min);

		}
	}
	
	static final File airports = new File("src/student/airports.txt");
	static final File airportsMedium = new File("src/student/airportsMedium.txt");
	static final File airportsSameCities = new File("src/student/airportsSameCities.txt");
	static final File airportsSmall = new File("src/student/airportsSmall.txt");

}
