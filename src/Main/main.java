package Main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import CSV.makeCSV;
import Filters.Filter;
import Filters.filterList;
import Filters.FilterGPS;
import Filters.FilterID;
import Filters.FilterMAC;
import Filters.FilterTime;
import Kml.kml;
import Wifi.WiFi;
import weightedCenterPoint.algo1;

public class main {

	private static void run() {
		Filter filter = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a folder to scan for csv files: ");
		String folder = sc.nextLine();

		System.out.println("Enter path to a write folder for CSV file and KML: ");
		String WriteFolder = sc.nextLine();

		ArrayList<WiFi> unionList = makeCSV.readFilesAndAddToUnionList(CSV.makeCSV.getAllcsvFileListFromFolder(folder));
		//writeListToCSVFile(unionList,WriteFolder);
		//writeListToCSVFile2(unionList,WriteFolder);
		//kml.makeKML(writeListToCSVFile2(unionList,WriteFolder),WriteFolder);
		
	
		System.out.println("Create a KML file Sorted by Time / GPS / ID press 1"
				+ "\n"+ " Create a csv file Sorted by MAC press 2");
		int option = Integer.parseInt(sc.nextLine());
		switch (option) {
		case 1: { 	
			
			System.out.println("Create a KML file Sorted by (1)Time, (2)GPS, (3)ID: or Create a csv file Sorted by MAC ?  (4)");
			int optionKML = Integer.parseInt(sc.nextLine());
			
			switch (optionKML) {
		case 1: { // filter by time
		
			System.out.println(
					"Filter by time syntax:\nStart time: yyyy-MM-dd HH:mm:ss" + " \nEnd time: yyyy-MM-dd HH:mm:ss");

			System.out.println("Start time: ");
			Scanner startTimeScan = new Scanner(System.in);
			String startTime = startTimeScan.nextLine();
			System.out.println("End time: ");
			Scanner endTimeScan = new Scanner(System.in);
			String endTime = endTimeScan.nextLine();
			filter = new FilterTime(startTime, endTime);//the new filter 
			
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
			//Sorting the filteredList by signal (WiFi is implementing Comparable)
			Collections.sort(filteredList);

			//writeListToCSVFile(filteredList,WriteFolder);
			kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
			//start time: 2017-11-12  13:06:08
			//end time:   2017-11-12  13:06:48
	
			startTimeScan.close();
			endTimeScan.close();
			System.out.println("Success!");
			break;
		}
		case 2: { // filter by GPS
			System.out.println(
					"Filter by GPS syntax:\nStart lat (bottom right corner of the filtering rectangle): 32.xxxxxx\nStart lon (bottom right corner of the filtering rectangle): 35.xxxxxx"
							+ "\nEnd lat (top left corner of the filtering rectangle): 32.xxxxxx \nEnd lon (top left corner of the filtering rectangle): 35.xxxxxx");

			/////////////////////////////////////////////
			System.out.println("Start lat: ");
			Scanner startLatScan = new Scanner(System.in);
			double startLat = startLatScan.nextDouble();
			/////////////////////////////////////////////
			System.out.println("Start lon: ");
			Scanner startLonScan = new Scanner(System.in);
			double startLon = startLonScan.nextDouble();
			/////////////////////////////////////////////
			System.out.println("End lat: ");
			Scanner endLatScan = new Scanner(System.in);
			double endLat = endLatScan.nextDouble();
			////////////////////////////////////////////
			System.out.println("End lon: ");
			Scanner endLonScan = new Scanner(System.in);
			double endLon = endLonScan.nextDouble();
			////////////////////////////////////////////
			filter = new FilterGPS(startLon, startLat, endLon,  endLat);
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
			//Sorting the filteredList by signal (WiFi is implementing Comparable)
			Collections.sort(filteredList);

			//writeListToCSVFile(filteredList,WriteFolder);
			kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
            //star lat: 32.10329792892698
			//star lon: 35.208765181718555
			//end lat: 32.104193727462615
			//end lon: 35.20922396579133
			
			
			startLatScan.close();
			endLatScan.close();
			startLatScan.close();
			startLonScan.close();
			endLonScan.close();
			System.out.println("Success!");
			break;
		}
		case 3: { // filter by ID
			System.out.println("Enter ID:");
			String ID = sc.nextLine();
			filter = new FilterID(ID);
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);

			//Sorting the filteredList by signal (WiFi is implementing Comparable)
			Collections.sort(filteredList);

			//writeListToCSVFile(filteredList,WriteFolder);
			kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
			makeCSV.writeListToCSVFile2(filteredList,WriteFolder);
            //SM-G935F
			System.out.println("Success!");
			break;
		}
		
		}
		}
		case 2: {
			System.out.println("To create a csv file Sorted by MAC press 1 or by MAC and signal press 2");
			System.out.println("to exit program press 0");
			int optionMAC = Integer.parseInt(sc.nextLine());
			if(optionMAC==0){
				System.out.println("The program closed");
				break;}
			
			switch (optionMAC) {
			case 1:
			{
				System.out.println("Enter MAC:");
			
			String MAC = sc.nextLine();
			System.out.println("Enter number of scans");
			int checks= sc.nextInt();
			filter = new FilterMAC(MAC);
			ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);

			//Sorting the filteredList by signal (WiFi is implementing Comparable)
			Collections.sort(filteredList);
			algo1.avgGPSPoint(filteredList,checks,WriteFolder);

			//Wirte csv and kml file
			//kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
			//makeCSV.writeListToCSVFile2(makeCSV.writeListToCSVFile(filteredList,WriteFolder), WriteFolder);
			
	        //ec:8c:a2:08:94:1c
			//1c:b9:c4:16:28:ec
			System.out.println("Success!");
			break;
			}
			
			case 2: {
				System.out.println("Enter number of MAC:");
				int checks= sc.nextInt();
				String[] MACaddresses = new String[checks];
				double[] signals = new double[checks];
			
				
				for (int i = 0; i < checks; i++) {
					System.out.println("Enter MAC:");
					String MAC = sc.nextLine();
					MACaddresses[i]=MAC;
					System.out.println("Enter signal:");
					double signal = sc.nextDouble();
					signals[i]=signal;
				}
				for (int i = 0; i < MACaddresses.length; i++) {
					filter = new FilterMAC(MACaddresses[i]);
					ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
				}


			//Sorting the filteredList by signal (WiFi is implementing Comparable)
			//Collections.sort(filteredList);
			//algo1.avgGPSPoint(filteredList,checks,WriteFolder);

			//Wirte csv and kml file
			//kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
			//makeCSV.writeListToCSVFile2(makeCSV.writeListToCSVFile(filteredList,WriteFolder), WriteFolder);
			
	        //ec:8c:a2:08:94:1c
			//1c:b9:c4:16:28:ec
			System.out.println("Success!");
			break;
			}
			}
		}
			}
			}

	
	public static void main(String[] args) {
	run();

}
}