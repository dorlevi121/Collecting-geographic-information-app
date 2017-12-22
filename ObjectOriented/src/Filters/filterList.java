package Filters;

import java.util.ArrayList;

import Wifi.WiFi;

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
