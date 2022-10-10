package student;

import java.time.LocalDate;

public class FlightTest {
	
	public static void main(String[] args) {
		testFlight();
	}
	
	private static void testFlight() {
		System.out.println("-->testFlight()");
		
		String flightNumber = "2432";
		AirportManager aManager = new AirportManager(AirportLoader.getAirportMap(AirportManagerTest.airports));
		Airport origin = aManager.getAirport("AND");
		Airport destination = aManager.getAirport("VLD");
		LocalDate date = LocalDate.of(2022, 05, 24);
		double cost = 155.0;
		
		Flight f = new Flight(flightNumber, date, origin, destination, cost);
		
		//Expected and Actual
		String msgExpected="Expected: The result should be the template of the flight.";
		String msgActual= "Actual:\n"+f;
		
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
		
	}

}
