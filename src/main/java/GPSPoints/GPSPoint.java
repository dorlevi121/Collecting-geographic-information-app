package main.java.GPSPoints;

public class GPSPoint {
	private double lat,lon,alt;
	
	public GPSPoint(double lat, double lon, double alt){
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}
	public GPSPoint(GPSPoint other){
		this(other.lat,other.lon,other.alt);
	}
	
	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getAlt() {
		return alt;
	}

	public String toString(){
		String ans = lat + "," + lon +"," +alt;
		return ans;
	}
	
}
