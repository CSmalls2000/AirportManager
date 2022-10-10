package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {
	
	//Instance variable
	private AirportManager airportManager;
	private Map<String, Flight> flights = new HashMap<String, Flight>();
	
	//Constructors
	public FlightManager(AirportManager am) {
		this.airportManager=am;
	}
	
	//Getter
	public int getNumFlights() {
		return flights.size();
	}
	
	//Methods
	public boolean addFlight(String number, String date, String originCode, String destinationCode, double cost) {
		LocalDate localDate = LocalDate.parse(date);
		Airport origin = airportManager.getAirport(originCode);
		Airport dest = airportManager.getAirport(destinationCode);
		Flight f = new Flight(number, localDate, origin, dest, cost);
		if(!flights.containsKey(f.getNumber())) {
			flights.put(f.getNumber(), f);
			return true;
		}
		return false;
	}
	
	public boolean addFlight(Flight flight) {
		if(!flights.containsKey(flight.getNumber())) {
			flights.put(flight.getNumber(), flight);
			return true;
		}
		return false;
	}
	
	public Flight getFlight(String number) {
		return flights.get(number);
	}
	
	public Flight removeFlight(String number) {
		return flights.remove(number);
	}
	public List<Flight> getFlightsByOrigin(String originCode){
		List<Flight> sameOrigin = new ArrayList<>();
		List<Flight> flightsIM = new ArrayList<>(flights.values());
		for(Flight f: flightsIM) {
			Airport a = f.getOrigin();
			if(a.getCode().equals(originCode)) {
				sameOrigin.add(f);
			}
		}
		return sameOrigin;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode, String date){
		List<Flight> sameOrigin = new ArrayList<>(getFlightsByOrigin(originCode));
		List<Flight> sameOgnAndDate=new ArrayList<>();
		for(Flight f: sameOrigin) {
			String fDate = f.getDate().toString();
			if(fDate.equals(date)) {
				sameOgnAndDate.add(f);
			}
		}
		return sameOgnAndDate;
	}
	
	public List<Flight> getFlightsByOriginAndDestination(String originCode, String destinationCode, String date){
		List<Flight> fBO = getFlightsByOrigin(originCode, date);
		List<Flight> fOgnAndDest = new ArrayList<>();
		for(Flight f: fBO) {
			String destCode = f.getDestination().getCode();
			if(destCode.equals(destinationCode)) {
				fOgnAndDest.add(f);
			}
		}
		return fOgnAndDest;
	}
	

}