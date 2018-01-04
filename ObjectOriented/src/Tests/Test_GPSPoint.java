package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import GPSPoints.GPSPoint;

public class Test_GPSPoint {
	private double Alt =705.3,Lon=35.41,Lat=32.34;
	/**
	 * Test for getLat in GPSPoints.GPSPoint
	 */
	@Test
	public void test_getLat() {
		GPSPoint p1 = new GPSPoint(Lat, Lon, Alt);
		assertEquals(32.34, p1.getLat(),0);
	}
	/**
	 * Test for getLon in GPSPoints.GPSPoint
	 */
	@Test
	public void test_getLon() {
		GPSPoint p1 = new GPSPoint(Lat, Lon, Alt);
		assertEquals(35.41, p1.getLon(),0);
	}
	/**
	 * Test for getAlt in GPSPoints.GPSPoint
	 */
	@Test
	public void test_getAlt() {
		GPSPoint p1 = new GPSPoint(Lat, Lon, Alt);
		assertEquals(705.3, p1.getAlt(),0);
	}
}
