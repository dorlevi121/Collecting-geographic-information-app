package weightedCenterPoint;

import GPSPoints.GPSPoint;

/**
	 * This class represents an object of algo2 line
	 * @author 
	 */
	public class Algo2_line implements Comparable<Algo2_line>{
		private double _weight; 
		private int _signal,_diff;
		private GPSPoint _point;
		
	/**
	 * This function get 2 signals and determines the weight and the diff
	 * @param l linefile of the input CSV file
	 * @param input_signal
	 * @param list_signal our data signal
	 */
		public Algo2_line(Line_Algo2 l,int input_signal, int list_signal){
			this._signal = list_signal;
			if(_signal <= Parameters.NO_SIGNAL){
				_diff = Parameters.DIFF_NO_SIG;
			}
			else{
				int dif = Math.abs(input_signal-(list_signal));
				_diff = Math.max(dif,Parameters.MIN_DIFF);
			}
			_weight = Parameters.NORM/(Math.pow(_diff, Parameters.SIG_DIFF)*Math.pow(input_signal, Parameters.POWER));
			_point = l.getGPSPoint();
		
		}

		public Algo2_line(String string) {
			string=null;
		}

		public GPSPoint get_p() {
			return _point;
		}

		public void set_p(GPSPoint _p) {
			this._point = _p;
		}

		public int get_signal() {
			return _signal;
		}

		public void set_signal(int _signal) {
			this._signal = _signal;
		}

		public int get_diff() {
			return _diff;
		}

		public void set_diff(int _diff) {
			this._diff = _diff;
		}

		public double get_weight() {
			return _weight;
		}

		public void set_weight(double _weight) {
			this._weight = _weight;
		}

		@Override
		public int compareTo(Algo2_line o) {
			return Integer.compare(-this._signal, -o._signal);
		}

		@Override
		public String toString() {
			return "Algo2_line [_weight=" + _weight + ", _signal=" + _signal + ", _diff=" + _diff + "]";
		}


}
