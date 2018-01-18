package main.java.weightedCenterPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * This class represent the form of the lines in the new arranged CSV file  
 * @author 
 *
 */

import main.java.GPSPoints.GPSPoint;
public class Line_Algo2 {
	private Date time;
	private String modelID, MAC;
	public GPSPoint point3D;
	private int numberOfNetworks;
	private List<algo2Network> listOfnetwork;

	public Line_Algo2(Date time, String modelID,GPSPoint Point_3D,int numOfNetworks, List<algo2Network> listOfnetwork) {
		this.time = time;
		this.modelID = modelID;
		this.point3D = Point_3D;
		this.numberOfNetworks = numOfNetworks;
		this.listOfnetwork = listOfnetwork;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getModelID() {
		return modelID;
	}
	public void setModel(String model) {
		this.modelID = model;
	}
	public GPSPoint getGPSPoint() {
		return point3D;
	}
	public void setGPSPoint(GPSPoint point_3D) {
		this.point3D = point_3D;
	}
	public int getNumberOfNetworks() {
		return numberOfNetworks;
	}
	public void setNumberOfNetworks(int numberOfNetworks) {
		this.numberOfNetworks = numberOfNetworks;
	}
	public List<algo2Network> getListOfNetwork() {
		return listOfnetwork ;
	}
	public void setNetwork(List<algo2Network> network) {
		this.listOfnetwork = network;
	}

	@Override
	public String toString() {
		return time + ","+ modelID+ ","+ point3D+","+ numberOfNetworks+","+ listOfnetwork;
	}
	
	
	public static void toCsv(List<Line_Algo2> network, String writeFolder){
		PrintWriter pw = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pw = new PrintWriter(new File(writeFolder + "/DataBase"+".csv"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String ColumnNamesList = "Time,ID,Lat,Lon,Alt,Num of network,MAC0,Signal0,MAC1,Signal1,MAC2,Signal,MAC3,Signal3,MAC4,Signal4"
				+ ",MAC5,Signal5,MAC6,Signal6,MAC7,Signal7,MAC8,Signal8,MAC9,Signal9";
		builder.append(ColumnNamesList + "\n");
		for (Line_Algo2 line : network) {
			builder.append(line.printLine());
			int y =0;
			for (int i = 8; i < line.numberOfNetworks+8; i++) {
				builder.append(line.printMAC(y));
y++;

			}
			builder.append('\n');
		}
		pw.write(builder.toString());
		pw.close();
		System.out.println("Data base file created successfuly in " + writeFolder);
		
	}
	
	public String printLine(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ans =sdf.format(time)+","+ modelID+"," + point3D.getLat() + ","+ point3D.getLon() + ","+ point3D.getAlt()+"," + numberOfNetworks+",";
		return ans;
	}
	
public String printMAC(int y){
	String ans = listOfnetwork.get(y) + ",";
	return ans;
	
}

}