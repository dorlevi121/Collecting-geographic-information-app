package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GPSPoints.GPSPoint;
import Wifi.WiFi;

/**
* This class unifies the CSV files and prints them into an ArrayList
* @author Dor Levi, Yarden Mizrahi
*/


public class makeCSV {

	/**
	 * 
	 * @param directoryName
	 *            directory to search csv files
	 * @return list with all the csv files paths
	 */
	public static ArrayList<String> getAllcsvFileListFromFolder(String directoryName) {
//this function from stackoverflow.
		ArrayList<String> fileList = new ArrayList<String>();
		File directory = new File(directoryName);

		// get all the files from a directory

		if (!directory.isDirectory()) {
			return fileList;
		}
		File[] fList = directory.listFiles();

		for (File file : fList) {

			if (file.isFile()) {

				if (file.getAbsolutePath().endsWith(".csv")) {
					fileList.add(file.getAbsolutePath());
					System.out.println("Fetching data from: " + file.getAbsolutePath());
				}

			} else if (file.isDirectory()) {

				fileList.addAll(getAllcsvFileListFromFolder(file.getAbsolutePath()));

			}

		}
		return fileList;

	}

	/**
	* This function unifies all CSV files to the ArrayList
	* @param csvReadPaths ArrayList of String with all the csv's path
	* @return ArrayList with all wifi list
	*/

	public static ArrayList<WiFi> readFilesAndAddToUnionList(ArrayList<String> csvReadPaths) {
		ArrayList<WiFi> unionList = new ArrayList<WiFi>();
		for (String csvPath : csvReadPaths) {
			try {
				FileReader Fr = new FileReader(csvPath);
				BufferedReader BR = new BufferedReader(Fr);
				int count = 0;
				String Line = BR.readLine();
				String[] firstLine = Line.split(",");
				String id = "";
				boolean notWigleFlag = false;
				while (Line != null && !notWigleFlag) {
					if (count == 0) {
						if (!Line.contains("WigleWifi")) {
							System.err.println("Error: File must be from WigleWifi (" + csvPath + ").");
							notWigleFlag = true;
						} else
							id = firstLine[2].substring(6);
					}
					count++;
					String[] arr = Line.split(",");
					if (count > 2 && arr[10].equals("WIFI")) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date time = sdf.parse(arr[3]);
						double alt = Double.parseDouble(arr[8]);
						double lat = Double.parseDouble(arr[6]);
						double lon = Double.parseDouble(arr[7]);
						GPSPoint wifiPoint = new GPSPoint(lat, lon, alt);
						// Creating WiFi
						String SSID = arr[1], MAC = arr[0];
						double freq = Double.parseDouble(arr[4]), signal = Double.parseDouble(arr[5]);
						WiFi wf = new WiFi(SSID, MAC, freq, signal, wifiPoint, time, id);
						unionList.add(wf);
					}
					Line = BR.readLine(); // next line
				}
				BR.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return unionList;
	}

	/**
	* This function unifies all CSV files to the ArrayList
	* @param csvReadPaths ArrayList of String with all the csv's path
	* @param writeFolder Address save the file
	* @return ArrayList with all wifi list
	*/
	
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
		String ColumnNamesList = "MAC,SSID,Time,Freq,Signal,Lat,Lon,Alt,moedlID";
		builder.append(ColumnNamesList + "\n");
		for (WiFi wifi : unionList) {
			builder.append(wifi.printUnion());
			builder.append('\n');
			GetUnionListSorted.add(wifi);
		}
		pw.write(builder.toString());
		pw.close();
		System.out.println("Union file created successfuly in " + writeFolder);
		return GetUnionListSorted;
	}


	public static void writeListToCSVFile2(ArrayList<WiFi> unionList, String writeFolder) {
		ArrayList<WiFi> best10Wifi = new ArrayList<WiFi>();
		PrintWriter pw = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pw = new PrintWriter(new File(writeFolder + "/sortedCSV_"+".csv"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}


		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "TIME,ID,LAT,LON,ALT,WIFI NETWORK,SSID1,MAC1,FREQNCY1,SIGNAL1,SSID2,MAC2,FREQNCY2,SIGNAL2,SSID3,MAC3,FREQNCY3,SIGNAL3,SSID4,MAC4,FREQNCY4,SIGNAL4,SSID5,MAC5,FREQNCY5,SIGNAL5,SSID6,MAC6,FREQNCY6,SIGNAL6,SSID7,MAC7,FREQNCY7,SIGNAL7,SSID,MAC8,FREQNCY8,SIGNAL8,SSID9,MAC9,FREQNCY9,SIGNAL9,SSID10,MAC10,FREQNCY10,SIGNAL10";
		builder.append(ColumnNamesList + "\n");

		for (int i = 0; i < unionList.size(); i++) {
			best10Wifi.add(unionList.get(i));
			for (int j = 0; j < unionList.size(); j++) {
				if(i!= j){
					double alt = unionList.get(j).point.getAlt(), lat = unionList.get(j).point.getLat(), lon = unionList.get(j).point.getLon();
					String ID = unionList.get(j).getModelID();
					Date time = unionList.get(j).getTime();
					best10Wifi.add(unionList.get(j));
					if(unionList.get(i).equalsID(ID) && unionList.get(i).equalsTime(time) && unionList.get(i).equalsGPS(alt,lon,lat)){
						unionList.remove(j--);
					}
				}
				
			} //End for j
			String best10 = WiFi.printWiFiList(best10Wifi);
			builder.append(best10);
			builder.append('\n');
			unionList.remove(i--);
			best10Wifi.clear();
		}//End for i
		pw.write(builder.toString());
		pw.close();
		System.out.println("All wifi file created successfuly in " + writeFolder);
	}


	
}
