package weightedCenterPoint;
import GPSPoints.GPSPoint;
/**
 * This class defines for every WIFI network that was scanned by the wigleWifi app it's 4 unique elements 
 * @author
 *
 */
public class algo2Network implements Comparable<algo2Network> {
	public String SSID;
	public String MAC;
	public int Signal;
	private boolean isTaken ;

/**
 * @param sSID the WIFI name
 * @param mac the MAC address
 * @param signal 
 *
 */
	 public algo2Network(String SSID, String Mac, int signal) {
	this.SSID = SSID;
		this.MAC = Mac;
		this.Signal = signal;
		isTaken=false;
	}

	public boolean isTaken(){
		return isTaken;
	}

public void setTaken(boolean isTaken) {
	this.isTaken = isTaken;
}

	public String getSSID() {
		return SSID;
	}
	public void setSSID(String SSID1) {
		SSID = SSID1;
	}
	public String getMac() {
		return MAC;
	}
	public void setMac(String mac) {
		MAC = mac;
	}
	public int getSignal() {
		return Signal;
	}
	public void setSignal(int signal) {
		Signal = signal;
	}

	@Override
	public int compareTo(algo2Network other) {
		return Integer.compare(-this.Signal, -other.Signal);
	}

	@Override
	public String toString() {
		return SSID+","+MAC+","+Signal;
	}



}