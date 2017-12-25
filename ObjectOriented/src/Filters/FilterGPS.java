package Filters;
import Wifi.WiFi;
import de.micromata.opengis.kml.v_2_2_0.Point;
/**
* This class compare between 2 wifi by GPS
* @author Dor Levi, Yarden Mizrahi
*/
public class FilterGPS implements Filter{
	double lonStart,  latStart, lonEnd, latEnd;
	
	/**
	 * FilterGPS builder 
	 * @param lonStart, latStart, lonEnd, latEnd GPS coordinate 
	 */
	public FilterGPS( double lonStart, double latStart, double lonEnd, double latEnd){
		
			this.lonStart = lonStart;
			this.latStart = latStart;
			this.latEnd = latEnd;
			this.lonEnd = lonEnd;
	}

	/**
	* This function compare between 2 wifi by GPS
	* @param wifi wifi object
	* @return true if the GPS points same to the other wifi
	*/
	@Override
	public boolean isBelong(WiFi wifi) {
		
			if (((wifi.point.getLon() > this.lonStart) && (wifi.point.getLon() < this.lonEnd))
					&& ((wifi.point.getLat() > this.latStart) && (wifi.point.getLat() < this.latEnd))){
				return true;
			}
		
		return false;
	}
	

}
