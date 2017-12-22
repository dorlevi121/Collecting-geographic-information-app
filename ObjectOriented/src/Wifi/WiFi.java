package Wifi;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import GPSPoints.GPSPoint;


public class WiFi implements Comparable<WiFi> {
	public GPSPoint point;
	private Date Time;
	private String SSID;
	private String MAC;
	private double freq, signal;
	private String modelID;

	public WiFi(String SSID, String MAC, double freq, double signal, double LAT, double LON, double ALT, Date Time,String modelID) {
		this.signal = signal;
		this.MAC = MAC;
		this.SSID = SSID;
		this.freq = freq;
		point = new GPSPoint(LAT, LON, ALT);
		this.Time = new Date(Time.getTime());
		this.modelID = modelID;
	}
	public WiFi(String SSID, String MAC, double freq, double signal, GPSPoint point, Date Time,String modelID) {
		this.signal = signal;
		this.MAC = MAC;
		this.SSID = SSID;
		this.freq = freq;
		this.point = new GPSPoint(point);
		this.Time = new Date(Time.getTime());
		this.modelID = modelID;
	}
	
	public WiFi(WiFi copy) {
		this(copy.SSID,copy.MAC, copy.freq, copy.signal, copy.point, copy.Time, copy.modelID);
	}

	

	public String getModelID() {
		return new String(modelID);
	}

	public Date getTime() {
		return new Date(Time.getTime());
	}

	public double getFreq() {
		return freq;
	}

	
	public String getSSID() {
		return new String(SSID);
	}

	public String getMAC() {
		return new String(MAC);
	}

	public double getSignal() {
		return signal;
	}
	public String printUnion(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ans = MAC +","+SSID+","+sdf.format(Time)+","+ freq+ ","+signal+"," + point.getLat() + ","+ point.getLon() + ","+ point.getAlt()+"," + modelID;
		return ans;
	}

	@Override
	public int compareTo(WiFi other) {
		if (this.signal < other.signal)
			return 1;
		else if (this.signal > other.signal)
			return -1;
		return 0;
	}
	
	
	public String printSort(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ans = sdf.format(Time)+"," + modelID + "," +point.getLat() + ","+ point.getLon() + ","+ point.getAlt() ;
		return ans;
	}
	
	public String printSort2(){
		String ans = "," + SSID + "," + MAC + "," + freq + "," + signal ;
		return ans;
	}
	
	public boolean equalsGPS(double alt, double lon, double lat) {
		boolean ans = false;
		if(alt==this.point.getAlt() && lon==this.point.getLon() && lat==this.point.getLat())
			ans = true;
		
		return ans;
	}
	
	public boolean equalsID(String ID) {
		boolean ans = false;
		if(this.modelID.equals(ID))
			ans = true;
		
		return ans;
	}
	
	public boolean equalsTime(Date time) {
		boolean ans = false;
		if(this.Time.equals(time))
			ans = true;
		
		return ans;
	}
	
	public static String printWiFiList(ArrayList<WiFi> list){
		Collections.sort(list);
		int count =0;
	        WiFi  wifi = new WiFi(list.get(0));
		String ans = wifi.printSort() + "," + Math.min(10, list.size());
		for (WiFi wifi2 : list){
			if(count==10)
				return ans;
			else{
			ans += wifi2.printSort2();
			count++;
			}
		}
		return ans;
	}
}