package main.java.Filters;
import main.java.Wifi.WiFi;
import main.java.weightedCenterPoint.Line_Algo2;

/**
* This class compare between 2 wifi by MAC address
* @author Dor Levi, Yarden Mizrahi
*/

public class FilterMAC implements Filter{
	public String MAC;
	public FilterMAC (String MAC){
		this.MAC = new String(MAC);
	}
	
	/**
	* This function compare between 2 wifi by MAC
	* @param wifi wifi object
	* @return true if the MAC points same to the other wifi
	*/
	@Override
	public boolean isBelong(WiFi wifi) {
		return MAC.toUpperCase().equals(wifi.getMAC().toUpperCase());
	}

	@Override
	public boolean isBelongsql(Line_Algo2 wifi) {
		return false;
	}
	
}
