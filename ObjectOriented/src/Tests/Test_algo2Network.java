package Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import GPSPoints.GPSPoint;
import junit.framework.TestCase;
import weightedCenterPoint.algo2Network;

public class Test_algo2Network extends TestCase {
	String SSID= "Ariel_University" ;
	String MAC = "8c:0c:90:6e:16:88";
	double Signal1 = -83;
	private algo2Network a2n=new algo2Network( MAC, Signal1);

	/**
	 * Test for  getMac in weightedCenterPoint.algo2Network
	 */
	@Test
	public void test_getMac() {
		assertEquals("8c:0c:90:6e:16:88", a2n.getMac());
	}
	/**
	 * Test for getSignal in weightedCenterPoint.algo2Network
	 */
	@Test
	public void test_getSignal() {
		assertEquals(-83, a2n.getSignal(),0);
	}


}
