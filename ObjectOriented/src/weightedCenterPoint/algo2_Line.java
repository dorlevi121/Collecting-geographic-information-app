package weightedCenterPoint;

import java.util.Date;

import GPSPoints.GPSPoint;

public class algo2_Line{ 
	private int NumberOFNetwork;
	 algo2[] algo2Line;
	public algo2 network;
	
	public algo2_Line(algo2 net, int numOfNetwork){
		this.NumberOFNetwork = numOfNetwork;
		this.network = net;
	algo2Line = new algo2[numOfNetwork];
	for (int i = 0; i < algo2Line.length; i++) {
		algo2Line[i]=net;
	}
	}
	
	public static void add(algo2 al){
		
		for (int i = 0; i <algo2Line.l ; i++) {
			algo2_Line[i]=al.getMAC();
		}
	}
	 
}
