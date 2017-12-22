package weightedCenterPoint;

import java.util.ArrayList;

import Filters.Filter;
import Filters.FilterMAC;
import Filters.filterList;
import Wifi.WiFi;

public class algo2 {

	public static void makeLocation(String[] MAC, String[] signal, ArrayList<WiFi> unionList){
		Filter filter = null;
		double diff=0;
		for (int i = 0; i < MAC.length; i++) {
			filter = new FilterMAC(MAC[i]);
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
		}
		
	}
}
