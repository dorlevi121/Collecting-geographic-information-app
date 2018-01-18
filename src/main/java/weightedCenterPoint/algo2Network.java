package main.java.weightedCenterPoint;

/**
 * This class defines for every WIFI network that was scanned by the wigleWifi app it's 4 unique elements 
 * @author
 *
 */
public class algo2Network implements Comparable<algo2Network> {

	public String MAC;
	public double Signal;



	/**
	 * @param sSID the WIFI name
	 * @param mac the MAC address
	 * @param signal 
	 *
	 */
	public algo2Network(String Mac, double signal) {

		this.MAC = Mac;
		this.Signal = signal;
	}
	public algo2Network(){
		this.MAC= null;
		this.Signal=0;

	}
	public String getMac() {
		return MAC;
	}
	public void setMac(String mac) {
		MAC = mac;
	}
	public double getSignal() {
		return Signal;
	}
	public void setSignal(double signal) {
		Signal = signal;
	}


	public int compareTo(algo2Network other) {
		return Double.compare(-this.Signal, -other.Signal);
	}

	public String toString() {
		return MAC+","+Signal;
	}



}