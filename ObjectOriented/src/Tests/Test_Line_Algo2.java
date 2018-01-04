package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import weightedCenterPoint.Line_Algo2;
import weightedCenterPoint.algo2Network;
import GPSPoints.GPSPoint;

public class Test_Line_Algo2 {
Date time = new Date();
	String ModalId = "YardenID";
	int numberOfnet= 4;
	double lat=32.2;
	double lon=35.6;
	double alt=705.6;
	GPSPoint p1 = new GPSPoint(lat, lon, alt);
	List<algo2Network> listTest = new ArrayList<algo2Network>();
	algo2Network wifi1= new algo2Network("yarden","1c:b9:c4:15:ed:bc", -82);
	algo2Network wifi2= new algo2Network("mizrahi", "8c:0c:90:2e:16:88", -90.0);
	algo2Network wifi3= new algo2Network("dor","1c:b9:c4:16:e5:a8",-78);
	algo2Network wifi4= new algo2Network("levi", "14:ae:db:58:0d:6d",-80);
	Line_Algo2 A2= new Line_Algo2(time, ModalId, p1,  numberOfnet, listTest);
	/**
	 * Test for  getModelID in weightedCenterPoint.Line_Algo2
	 */
	@Test
	public void test_getModel() {
		assertEquals("YardenID", A2.getModelID());	
	}
	/**
	 * Test for  getNumberOfNetworks in weightedCenterPoint.Line_Algo2
	 */
	@Test
	public void test_getNumberOfNetworks() {
		
		assertEquals(4, A2.getNumberOfNetworks());	
	}
	/**
	 * Test for  getListOfNetwork in weightedCenterPoint.Line_Algo2
	 */
	@Test
	public void test_getListOfNetwork() {
		listTest.add(wifi1);listTest.add(wifi1);listTest.add(wifi1);listTest.add(wifi1);
		 assertEquals(listTest, A2.getListOfNetwork());
	}

}
