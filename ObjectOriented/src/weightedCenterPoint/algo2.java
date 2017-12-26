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

public class algo2 implements Comparable<algo2>{

	private String MAC;
	private double signal;
	private String modelID;

	public algo2(String mac, double Signal1,String ID) {
		this.MAC=mac;
		this.signal= Signal1;
		this.modelID=ID;
	}

	@Override
	public int compareTo(algo2 o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMAC() {
		return MAC;
	}

	public double getSignal() {
		return signal;
	}

	public String getModelID() {
		return modelID;
	}



}
