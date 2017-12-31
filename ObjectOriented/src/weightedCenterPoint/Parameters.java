package weightedCenterPoint;

public class Parameters {
	/**
	 * this class represents all the constants Parameters
	 * @author Yarden,Dor
	 */
		static final int NO_SIGNAL = -120;
		static final int POWER = 2;
		static final int NORM = 10000;
		static final double SIG_DIFF = 0.4;
		static final int MIN_DIFF = 3;
		static final int DIFF_NO_SIG = 100;
		static final int max_Signals = 4;
		public static int getNoSignal() {
			return NO_SIGNAL;
		}
		public static int getPower() {
			return POWER;
		}
		public static int getNorm() {
			return NORM;
		}
		public static double getSigDiff() {
			return SIG_DIFF;
		}
		public static int getMinDiff() {
			return MIN_DIFF;
		}
		public static int getDiffNoSig() {
			return DIFF_NO_SIG;
		}
		public static int getMaxSignals() {
			return max_Signals;
		}
	}
