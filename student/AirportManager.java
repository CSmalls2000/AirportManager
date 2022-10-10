package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirportManager {
	//Instance variables
	private Map<String, Airport> airports;
	
	//Constructor
	public AirportManager(Map<String,Airport> airports) {
		this.airports=airports;
	}
	
	//Methods
	public Airport getAirport(String code) {
		if(airports.containsKey(code)) {
			return airports.get(code);
		}
		return null;
	}
	
	public Airport getAirportClosestTo(String code) {
		ArrayList<Airport> aptsIM = new ArrayList<>(airports.values());
		ArrayList<Double> distList = new ArrayList<>();
		double[] foundDist = new double[1];
		for(int i=0; i<aptsIM.size();i++) {
			Airport a =aptsIM.get(i);
			if(!a.getCode().equals(code)) {
				double dist = getDistanceBetween(code, a.getCode());
				distList.add(dist);
			}
		}
		distList.sort(new DoubleComparator());
		for(int found=0;found<foundDist.length;found++) {
			foundDist[found]=distList.get(found);
		}
		for(Airport a1: aptsIM) {
			double distBet = getDistanceBetween(code, a1.getCode());
			for(int j=0; j<foundDist.length;j++) {
				double distArray = foundDist[j];
				if(distBet==distArray) {
					return a1;
				}
			}
		}
		return null;
	}
	
	public List<Airport> getAirports(){
		List<Airport> airportList = new ArrayList<>(airports.values());
		return airportList;
	}
	
	public double getDistanceBetween(Airport a1, Airport a2) {
		double lat1 = a1.getLatitude();
		double lon1 = a1.getLongitude();
		
		double lat2 = a2.getLatitude();
		double lon2 = a2.getLongitude();
		
		double distance = DistanceCalculator.getDistance(lat1, lon1, lat2, lon2);
		return distance;
	}
	
	public double getDistanceBetween(String code1, String code2) {
		if(airports.containsKey(code1)&&airports.containsKey(code2)) {
			Airport a1 = airports.get(code1);
			Airport a2 = airports.get(code2);

			return getDistanceBetween(a1,a2);
		}
		else {
			return -1.0;
		}
	}
	
	public List<Airport> getAirportsByCity(String city){
		List<Airport> inCity = new ArrayList<>();
		for(Airport a : airports.values()) {
			String aCity = a.getCity();
			if(aCity.equals(city)) {
				inCity.add(a);
			}
		}
		return inCity;
	}
	
	public List<Airport> getAirportsByCityState(String city, String state){
		List<Airport> cityState = new ArrayList<>();
		for(Airport a: airports.values()) {
			String aCity = a.getCity();
			String aState = a.getState();
			if(aCity.equals(city)&&aState.equals(state)) {
				cityState.add(a);
			}
		}
		return cityState;
	}
	
	public List<Airport> getAirportsClosestTo(String code, int num){
		List<Airport> closestAirports = new ArrayList<>();
		ArrayList<Airport> aptsIM = new ArrayList<>(airports.values());
		ArrayList<Double> distList = new ArrayList<>();
		double[] foundDists = new double[num];
		for(int i=0; i<aptsIM.size();i++) {
			Airport a =aptsIM.get(i);
			if(!a.getCode().equals(code)) {
				double dist = getDistanceBetween(code, a.getCode());
				distList.add(dist);
			}
		}
		distList.sort(new DoubleComparator());
		for(int found=0;found<foundDists.length;found++) {
			foundDists[found]=distList.get(found);
		}
		for(Airport a1: aptsIM) {
			double distBet = getDistanceBetween(code, a1.getCode());
			for(int j=0; j<foundDists.length;j++) {
				double distArray = foundDists[j];
				if(distBet==distArray) {
					closestAirports.add(a1);
					break;
				}
			}
		}
		return closestAirports;
		
	}
	
	public List<Airport> getAirportsSortedBy(String sortType){
		List<Airport> sorted = new ArrayList<>(airports.values());
		String type = sortType.toLowerCase();
		
		if(type.equals("city")) {
			sorted.sort(new AirportCityComparator());
			return sorted;
		}
		else if(type.equals("state")) {
			sorted.sort(new AirportStateComparator());
			return sorted;
		}
		else {
			return null;
		}		
	}
	
	public List<Airport> getNWSNamedAirports(){
		List<Airport> nwsAirports = new ArrayList<>();
		for(Airport a: airports.values()) {
			String code = a.getCode();
			String lastLetter = code.substring(2);
			if(lastLetter.equals("X")) {
				nwsAirports.add(a);
			}
		}
		return nwsAirports;
	}
	
	public List<Airport> getAirportsWithin(String code, double withinDist){
		List<Airport> airportsInMap = new ArrayList<>(airports.values());
		List<Airport> airportsInRange = new ArrayList<>();
		Airport a = new Airport(code);
		
		if(airportsInMap.contains(a)) {
			int loc = airportsInMap.indexOf(a);
			a = airportsInMap.get(loc);
			double lat1 = a.getLatitude();
			double lon1 = a.getLongitude();
			
			for(Airport inMap: airportsInMap) {
				if(!inMap.getCode().equals(code)) {
					double lat2 = inMap.getLatitude();
					double lon2 = inMap.getLongitude();
					double distBetweenAirports = DistanceCalculator.getDistance(lat1, lon1, lat2, lon2);
					if(distBetweenAirports<=withinDist) {
						airportsInRange.add(inMap);
					}
				}
			}
		}
		return airportsInRange;
	}
	
	public List<Airport> getAirportsWithin(String code1, String code2, double withinDist){
		List<Airport> airports1 = getAirportsWithin(code1, withinDist);
		List<Airport> airports2 = getAirportsWithin(code2, withinDist);
		List<Airport> combinedList = new ArrayList<>(airports1);
		combinedList.retainAll(airports2);
		return combinedList;
	}
	
	public List<Airport> getAirportsWithin(double withinDistance, double lat, double lon){
		List<Airport> airportsInRange = new ArrayList<>();
		for(Airport a: airports.values()) {
			double aLat = a.getLatitude();
			double aLong = a.getLongitude();
		
			double distance = DistanceCalculator.getDistance(lat, lon, aLat, aLong);
			if(distance<=withinDistance) {
				airportsInRange.add(a);
			}
		}
		return airportsInRange;
	}
	
}