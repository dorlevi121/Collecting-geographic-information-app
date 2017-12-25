package Kml;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Wifi.WiFi;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
* This class responsible of writing the final kml file 
* @author Dor Levi, Yarden Mizrahi
*/

public class kml {
/**
 * creating the kml and sending it to directory
 * @param list ArrayList of wifi
 * @param writeFolder Address save the file
 */
	public static void makeKML(ArrayList<WiFi> list , String writeFolder) {
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		for (int i = 0; i < list.size(); i++) {
			WiFi wifi = list.get(i);
			 String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss";
			 Date date = new Date();
			 date= list.get(i).getTime();
			 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			 String time = sdf.format(date);
		//	String time = (wifi.getTime().);
				convertTimeFormat(time);
			TimeStamp ts = new TimeStamp();
			ts.setWhen(time);
			doc.createAndAddPlacemark().withName(wifi.getSSID()).withOpen(Boolean.TRUE).withTimePrimitive(ts)
					.withDescription("mac: " + wifi.getMAC() + " freq: " + wifi.getFreq() + " signal: " + wifi.getSignal())
					.createAndSetPoint().addToCoordinates(wifi.point.getLon(),
							wifi.point.getLat(), wifi.point.getAlt());
		}
		try {
			kml.marshal(new File(writeFolder + "/placrmark" +".kml"));
		} catch (IOException ex) {
			System.err.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}
	private static String convertTimeFormat(String oldTimeFormat) {
		String[] dateTime = oldTimeFormat.split(" ");
		return dateTime[0] + 'T' + dateTime[1];
	}

}