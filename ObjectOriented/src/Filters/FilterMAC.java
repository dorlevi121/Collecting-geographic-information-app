package Filters;
import Wifi.WiFi;

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
	
}
