package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Parameters{
	static final double NO_SIGNAL = -120;
	static final double POWER = 2.0;
	static final double NORM = 10000;
	static final double SIG_DIFF = 0.4;
	static final double MIN_DIFF = 3.0;
	static final double DIFF_NO_SIG = 100;
	static final double max_Signals = 4;
	/**
	 * Test for getNoSignal in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getNoSignal() {
		assertEquals(-120, NO_SIGNAL,0);
	}
	/**
	 * Test for getPower in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getPower() {
		assertEquals(2.0, POWER,0);
	}
	/**
	 * Test for getNorm in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getNorm() {
		assertEquals(10000,  NORM,0);
	}
	/**
	 * Test for getSigDiff in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getSigDiff() {
		assertEquals(0.4,SIG_DIFF ,0);
	}
	/**
	 * Test for getMinDiff in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getMinDiff() {
		assertEquals(4, max_Signals ,0);
	}
	/**
	 * Test for getDiffNoSig in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getDiffNoSig() {
		assertEquals(100,DIFF_NO_SIG ,0);
	}
	/**
	 * Test for getMaxSignals in weightedCenterPoint.Parameters
	 */
	@Test
	public void test_getMaxSignals() {
		assertEquals(-120, NO_SIGNAL,0);
	}
}

