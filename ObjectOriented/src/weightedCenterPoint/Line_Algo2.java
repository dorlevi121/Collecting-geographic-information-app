package weightedCenterPoint;

import java.util.Date;
import java.util.List;
/**
 * This class represent the form of the lines in the new arranged CSV file  
 * @author 
 *
 */

import GPSPoints.GPSPoint;
import javafx.geometry.Point3D;
public class Line_Algo2 {
	private Date time;
	private String modelID;
	private GPSPoint point3D;
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


}