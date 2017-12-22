package Filters;
import Wifi.WiFi;

public class FilterMAC implements Filter{
	public String MAC;
	public FilterMAC (String MAC){
		this.MAC = new String(MAC);
	}
	@Override
	public boolean isBelong(WiFi wifi) {
		return MAC.toUpperCase().equals(wifi.getMAC().toUpperCase());
	}
	
}
