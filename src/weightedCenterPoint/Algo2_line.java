package weightedCenterPoint;

import GPSPoints.GPSPoint;

/**
	 * This class represents an object of algo2 line
	 * @author 
	 */
	public class Algo2_line implements Comparable<Algo2_line>{
		private double _weight; 
		private double _signal;
		private double _diff;
		private GPSPoint _point;
		
	/**
	 * This function get 2 signals and determines the weight and the diff
	 * @param l linefile of the input CSV file
	 * @param input_signal
	 * @param list_signal our data signal
	 */
		public Algo2_line(Line_Algo2 l,double input_signal, double list_signal){
			this._signal = list_signal;
			if(_signal <= Parameters.NO_SIGNAL){
				_diff = Parameters.DIFF_NO_SIG;
			}
			else{
				double dif = Math.abs(input_signal-(list_signal));
				_diff = Math.max(dif,Parameters.MIN_DIFF);
			}
			_weight = Parameters.NORM/(Math.pow(_diff, Parameters.SIG_DIFF)*Math.pow(input_signal, Parameters.POWER));
			_point = l.getGPSPoint();
		
		}
	
		public Algo2_line(String string) {
			string=null;
		}

		public GPSPoint get_point() {
			return _point;
		}

		public void set_point(GPSPoint _p) {
			this._point = _p;
		}

		public double get_signal() {
			return _signal;
		}

		public void set_signal(double _signal) {
			this._signal = _signal;
		}

		public double get_diff() {
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

	
	public int compareTo(Algo2_line o) {
		
			return  Double.compare(-this._signal, -o._signal);
}

		
		public String toString() {
			return "Algo2_line [_weight=" + _weight + ", _signal=" + _signal + ", _diff=" + _diff + "]";
		}


}
