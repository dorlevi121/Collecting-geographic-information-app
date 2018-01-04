package weightedCenterPoint;

public class Parameters {
	/**
	 * this class represents all the constants Parameters
	 * @author Yarden,Dor
	 */

		static final double NO_SIGNAL = -120;
		static final double POWER = 2.0;
		static final double NORM = 10000;
		static final double SIG_DIFF = 0.4;
		static final double MIN_DIFF = 3.0;
		static final double DIFF_NO_SIG = 100;
		static final double max_Signals = 4;
		public static double getNoSignal() {
			return NO_SIGNAL;
		}
		public static double getPower() {
			return POWER;
		}
		public static double getNorm() {
			return NORM;
		}
		public static double getSigDiff() {
			return SIG_DIFF;
		}
		public static double getMinDiff() {
			return MIN_DIFF;
		}
		public static double getDiffNoSig() {
			return DIFF_NO_SIG;
		}
		public static double getMaxSignals() {
			return max_Signals;
		}
	}
