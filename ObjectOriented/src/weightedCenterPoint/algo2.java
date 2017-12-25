package weightedCenterPoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Filters.Filter;
import Filters.FilterMAC;
import Filters.filterList;
import GPSPoints.GPSPoint;
import Wifi.WiFi;

public class algo2 {
	
	public static ArrayList<WiFi> makeLocation(String[] MAC, double[] signal, ArrayList<WiFi> unionList){
		Filter filter = null;
		ArrayList<WiFi> allMAC = new ArrayList<WiFi>();
		double diff=0;
		for (int i = 0; i < MAC.length; i++) {
			filter = new FilterMAC(MAC[i]);
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
			allMAC.addAll(filteredList);
		}
		return allMAC;
	}
	


}
