package Filters;

import java.util.ArrayList;

import Wifi.WiFi;
/**
* This class compare between 2 wifi by filter
* @author Dor Levi, Yarden Mizrahi
*/
public class filterList {


	public static ArrayList<WiFi> filterList(ArrayList<WiFi> list, Filter filter){
		ArrayList<WiFi> result = new ArrayList<WiFi>();
		for (WiFi wifi : list){
			if (filter.isBelong(wifi))
				result.add(wifi);
		}
		return result;
	}


}
