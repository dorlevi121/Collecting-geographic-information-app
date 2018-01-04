package weightedCenterPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Wifi.WiFi;

public class algo1 {
	
	public static void avgGPSPoint(ArrayList<WiFi> filteredList,int check , String path) {
		double AVGLAT = 0, AVGLON = 0, AVGALT = 0;
		double  weight, sumWeight=0;
		if(filteredList.size()<check) check = filteredList.size();
		for (int i = 0; i <check; i++) {

			weight=1.0/(Math.pow(filteredList.get(i).getSignal(), 2));
			sumWeight +=weight; 
			AVGLAT+=filteredList.get(i).point.getLat()*weight;
			AVGLON+=filteredList.get(i).point.getLon()*weight;
			AVGALT+=filteredList.get(i).point.getAlt()*weight;

		}
		
		writeToCSV(filteredList, path, check, AVGLAT, AVGLON, AVGALT, sumWeight);
	}
	
	private static void writeToCSV(ArrayList<WiFi> filteredList, String path, int check, double AVGLAT, double AVGLON, double AVGALT, double sumWeight){
		
		PrintWriter pw = null;
		try {

			pw = new PrintWriter(new File(path + "/WeightedCenterPoint"+".csv"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnMACandSSID =  "SSID:,MAC:";
		builder.append(","+","+","+ColumnMACandSSID+"\n");
		builder.append(","+","+","+filteredList.get(0).getSSID()+","+filteredList.get(0).getMAC());
		builder.append("\n");
		builder.append("\n");
		String firstColumn = "Signal:,Alt:,Lon:,Lat:";
		builder.append(","+firstColumn + "\n");
		for (int i = 0; i <check; i++) {
			builder.append(","+filteredList.get(i).getSignal()+","+filteredList.get(i).point.getAlt()+","
		+filteredList.get(i).point.getLon()+","+filteredList.get(i).point.getLat());
			builder.append("\n");
		}
		builder.append("\n");
		builder.append("\n");

		String ColumnNamesList = "weight,w_Lat,w_Lon,w_Alt";
		builder.append(","+ColumnNamesList + "\n");
		builder.append(","+sumWeight+","+AVGLAT+","+AVGLON+","+AVGALT);
		String lastColumn = "w-center:";
		builder.append("\n");
		builder.append("\n");
		builder.append(","+","+AVGALT/sumWeight+","+AVGLON/sumWeight+","+AVGLAT/sumWeight+","+ lastColumn);

		
		pw.write(builder.toString());
		pw.close();
		System.out.println("Router Point  file created successfuly in " + path);
	}
		}
	


