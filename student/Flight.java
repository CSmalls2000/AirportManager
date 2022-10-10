package student;

import java.time.LocalDate;

public class Flight {

	//Instance Variables
	private double cost;
	private LocalDate date;
	private Airport destination;
	private double distance;
	private String number;
	private Airport origin;
	
	//Constructor
	public Flight(String number, LocalDate date, Airport origin, Airport destination, double cost) {
		if(number.length()<4) {
			throw new IllegalArgumentException("Flight number is invalid");
		}
		this.number=number;
		this.date=date;
		this.origin=origin;
		this.destination=destination;
		this.cost=cost;
	}
	
	//Getters
	public double getCost() {
		return cost;
	}

	public LocalDate getDate() {
		return date;
	}

	public Airport getDestination() {
		return destination;
	}

	public double getDistance() {
		double distance = DistanceCalculator.getDistance(origin.getLatitude(), origin.getLongitude(), destination.getLatitude(), destination.getLongitude());
		return distance;
	}

	public String getNumber() {
		return number;
	}

	public Airport getOrigin() {
		return origin;
	}
	
	//ToString
	@Override
	public String toString() {
		return String.format("Flight: %s, Date: %s\nFrom: %s-%s, %s\nTo: %s-%s, %s\nDistance: %.0f miles, Cost: $%,.2f", getNumber(), getDate().toString(),
				origin.getCode(),origin.getCity(),origin.getState(),destination.getCode(),destination.getCity(),destination.getState(), getDistance(), getCost());
	}
	
}