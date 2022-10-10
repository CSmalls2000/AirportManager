package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AirportLoader {
	
	public static Map<String, Airport> getAirportMap(File airportFile){
		Map<String, Airport> airportMap = new HashMap<String, Airport>();

		try {
			Scanner input = new Scanner(airportFile);
			
			while(input.hasNext()) {
				String line = input.nextLine();
				String[] tokens = line.split("\\s");
				
				String code = tokens[0];
				double lat = Double.parseDouble(tokens[1]);
				double lon = Double.parseDouble(tokens[2]);
				String city = tokens[3];
				String state = tokens[4];
				
				Airport a = new Airport(code, lat, lon, city, state);
				airportMap.put(a.getCode(), a);
			}
			input.close();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}
		return airportMap;
	}

}
