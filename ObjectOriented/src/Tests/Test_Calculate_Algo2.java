package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import GPSPoints.GPSPoint;
import weightedCenterPoint.Calculate_Algo2;

public class Test_Calculate_Algo2 {
	/**
	 * Test getPi in weightedCenterPoint.Calculate_Algo2
	 */
	@Test
	public void test_getPi() {
		 double pi =3.6;
		GPSPoint p2 = new GPSPoint(32.34, 35.41, 705.3);
		Calculate_Algo2 ca = new Calculate_Algo2(p2,pi);
		assertEquals(3.6, ca.getPi(),0);
	}


}
