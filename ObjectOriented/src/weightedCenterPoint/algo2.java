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
			for (WiFi wifi : filteredList) {
				wifi.setSignalByUser(signal[i]);
			}
			allMAC.addAll(filteredList);
		}
		return allMAC;
	}
	

	public static ArrayList<WiFi> readComb_File(String filesName){

		ArrayList<WiFi> unionList = new ArrayList<WiFi>();
			try{
				FileReader Fr = new FileReader(filesName);
				BufferedReader BR = new BufferedReader(Fr);
				int count = 0;
				String Line = BR.readLine();
				String[] firstLine = Line.split(",");
				String id = "";

				while (Line != null) {
					if (count == 0) {	
						id = firstLine[1].substring(6);
					}

					count++;
					String[] arr = Line.split(",");

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					Date time = sdf.parse(arr[0]);


					double alt = Double.parseDouble(arr[4]);
					double lat = Double.parseDouble(arr[2]);
					double lon = Double.parseDouble(arr[3]);
					GPSPoint wifiPoint = new GPSPoint(lat, lon, alt);
					// Creating WiFi
					for(int i=6;i<arr.length;i+=4){
						String SSID = arr[i], MAC = arr[i+1];
						double freq = Double.parseDouble(arr[i+2]), signal = Double.parseDouble(arr[i+3]);

						WiFi wf = new WiFi(SSID, MAC, freq, signal, wifiPoint, time, id);
						unionList.add(wf);
					}


					Line = BR.readLine(); // next line
				}
				BR.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return unionList;
	}


	public static ArrayList<WiFi> writeListToCSVFile(ArrayList<WiFi> unionList, String writeFolder) {
		ArrayList<WiFi> GetUnionListSorted = new ArrayList<WiFi>();
		PrintWriter pw = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pw = new PrintWriter(new File(writeFolder + "/AllWifiCSV_"+".csv"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "MAC,SSID,Time,Freq,Signal,Signal,Lat,Lon,Alt,moedlID";
		builder.append(ColumnNamesList + "\n");
		for (WiFi wifi : unionList) {
			builder.append(wifi.printAlgo2());
			builder.append('\n');
			GetUnionListSorted.add(wifi);
		}
		pw.write(builder.toString());
		pw.close();
		System.out.println("Union file created successfuly in " + writeFolder);
		return GetUnionListSorted;
	}


}
