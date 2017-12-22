package Filters;
import Wifi.WiFi;

public class FilterSSID implements Filter{
	public String SSID;
	public FilterSSID (String SSID){
		this.SSID = new String(SSID);
	}
	@Override
	public boolean isBelong(WiFi wifi) {
		return SSID.toUpperCase().equals(wifi.getSSID().toUpperCase()) || wifi.getSSID().toUpperCase().contains(SSID.toUpperCase());
	}
	
}
