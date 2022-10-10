package student;

public class Airport {
	
	//Instance variables
	private String city;
	private String code;
	private double latitude;
	private double longitude;
	private String state;
	
	//Constructors
	protected Airport(String code, double latitude, double longitude, String city, String state) {
		this.code=code;
		this.latitude=latitude;
		this.longitude=longitude;
		this.city=city;
		this.state=state;
		
	}
	protected Airport(String code) {
		this(code,0.0,0.0, "city", "tbd");
	}
	
	//Getters
	public String getCity() {
		return city;
	}
	
	public String getCode() {
		return code;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getState() {
		return state;
	}
	
	//Methods
	public boolean equals(Object o) {
		if(o instanceof Airport) {
			Airport a = (Airport)o;
			if(this.code==a.code) {
				return true;
			}
		}
		return false;
	}
	
	//ToString
	@Override
	public String toString() {
		return String.format("(%s-%s, %s: %.1f, %.1f)", getCode(), getCity(), getState(), getLatitude(), getLongitude());
	}

}
