package Filters;
import Wifi.WiFi;

/**
* This class compare between 2 wifi by SSID
* @author Dor Levi, Yarden Mizrahi
*/

public class FilterSSID implements Filter{
	public String SSID;
	public FilterSSID (String SSID){
		this.SSID = new String(SSID);
	}
	
	/**
	* This function compare between 2 wifi by SSID
	* @param wifi wifi object
	* @return true if the SSID points same to the other wifi
	*/
	@Override
	public boolean isBelong(WiFi wifi) {
		return SSID.toUpperCase().equals(wifi.getSSID().toUpperCase()) || wifi.getSSID().toUpperCase().contains(SSID.toUpperCase());
	}
	
}
