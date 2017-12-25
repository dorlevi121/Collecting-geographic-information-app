package weightedCenterPoint;

public class main {

	public static void main(String[] args) {
		String a = "C/Users/dorle/Desktop/test1";
		String[] mac = {"00:1a:dd:e3:06:e4", "d8:fe:e3:03:5a:31", "ec:8c:a2:08:78:cc", "1c:b9:c4:14:2b:d8"};
		double[] signal = {-60, -80, 75, -15};
	algo2.makeLocation(mac, signal, algo2.readComb_File(a));
	}

}
