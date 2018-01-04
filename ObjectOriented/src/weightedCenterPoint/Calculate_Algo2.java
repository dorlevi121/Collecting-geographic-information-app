package weightedCenterPoint;

import GPSPoints.GPSPoint;

public class Calculate_Algo2 implements Comparable<Calculate_Algo2> {
	
	/**
	* This class represents an object of Location and PI for Algo2
	 * @author Yarden , Dor
	 *
	 */

		private double pi;
		private GPSPoint  point;
	    
		public Calculate_Algo2(){};
		
	    public Calculate_Algo2(GPSPoint p1,double pi){
	    	this.point = p1;
	    	this.pi = pi;
	   }

		public double getPi() {
			return pi;
		}

		public void setPi(double _pi) {
			this.pi = _pi;
		}

		public void setPoint(GPSPoint p1) {
			this.point = p1;
		}

		public GPSPoint getPoint() {
			return point;
		}

	
		@Override
		public int compareTo(Calculate_Algo2 other) {
			return Double.compare(-this.pi, -other.pi);
		}

		@Override
		public String toString() {
			return "comb [_pi=" + pi + ", point=" + point + "]";
		}

}
