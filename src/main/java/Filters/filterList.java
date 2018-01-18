package main.java.Filters;

import java.util.ArrayList;
import java.util.List;

import main.java.Wifi.WiFi;
import main.java.weightedCenterPoint.Line_Algo2;
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


	public static List<Line_Algo2> filterList1(List<Line_Algo2> list, Filter filter){
		List<Line_Algo2> result = new ArrayList<Line_Algo2>();
		for (Line_Algo2 wifi : list){
			if (filter.isBelongsql(wifi))
				result.add(wifi);
		}
		return result;
	}

}
