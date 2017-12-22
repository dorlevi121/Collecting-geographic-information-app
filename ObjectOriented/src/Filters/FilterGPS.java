package Filters;
import Wifi.WiFi;
import de.micromata.opengis.kml.v_2_2_0.Point;

public class FilterGPS implements Filter{
	double lonStart,  latStart, lonEnd, latEnd;
	
	public FilterGPS( double lonStart, double latStart, double lonEnd, double latEnd){
		
			this.lonStart = lonStart;
			this.latStart = latStart;
			this.latEnd = latEnd;
			this.lonEnd = lonEnd;
	}

	@Override
	public boolean isBelong(WiFi wifi) {
		
			if (((wifi.point.getLon() > this.lonStart) && (wifi.point.getLon() < this.lonEnd))
					&& ((wifi.point.getLat() > this.latStart) && (wifi.point.getLat() < this.latEnd))){
				return true;
			}
		
		return false;
	}
	

}
