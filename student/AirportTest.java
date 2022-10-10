package student;

public class AirportTest {
	
	public static void main(String[] args) {
		testToString();
		testEquals_False();
		testEquals_True();
	}
	
	public static void testToString() {
		System.out.println("--->testAirportToString()");
		Airport a = new Airport("DLT", 3.0, 4.0, "Valdosta", "GA");
		
		//Expected and Actual
		String msgExpected = "Expected: (DLT-Valdosta, GA: 3.0, 4.0)";
		String msgActual = "Actual: "+ a;
		
		//Result
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	public static void testEquals_False() {
		System.out.println("\n--->testEquals_False()");
		Airport a = new Airport("DLT", 3.0, 4.0, "Valdosta", "GA");
		Airport a1 = new Airport("AAL", 3.0, 4.0, "Valdosta", "GA");
		
		//Holds result
		boolean equal = a.equals(a1);
		
		//Expected and actual
		String msgExpected = "Expected: Airport a and Airport a1 are not the same airport.";
		String msgActual = "Actual: ";
		if(equal==true) {
			msgActual += "Airport a and Airport a1 are the same airport.";
		}
		else {
			msgActual+= "Airport a and Airport a1 are not the same airport.";
		}
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
	
	public static void testEquals_True() {
		System.out.println("\n--->testEquals_True()");
		Airport a = new Airport("DLT", 3.0, 4.0, "Valdosta", "GA");
		Airport a1 = new Airport("DLT", 3.6, 5.0, "Altanta", "GA");
		
		//Holds result
		boolean equal = a.equals(a1);
		
		//Expected and actual
		String msgExpected = "Expected: Airport a and Airport a1 are the same airport.";
		String msgActual = "Actual: ";
		if(equal==true) {
			msgActual += "Airport a and Airport a1 are the same airport.";
		}
		else {
			msgActual+= "Airport a and Airport a1 are not the same airport.";
		}
		//Outcome
		System.out.println(msgExpected);
		System.out.println(msgActual);
	}
}