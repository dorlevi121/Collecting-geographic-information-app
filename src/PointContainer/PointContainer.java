package PointContainer;

public class PointContainer {
	private static final int INIT_SIZE = 10;
	private static final int RESIZE = 10;
	private int size;
	private Point[] points;
	
	public PointContainer(){
		points = new Point[INIT_SIZE];
		size = 0;
	}
	public PointContainer(PointContainer other){ ///////////////// העתקה עמוקה
		this.points = new Point[other.points.length];
		size = other.size;
		for (int i = 0; i < size; i++) {
			this.points[i] = new Point(other.points[i]);
		}
	}
	///////////////מטודות / שיטות
	public void add(Point p){
		if (points.length == size)
			resize();
		points[size] = new Point(p);
		size++;
	}
	private void resize(){
		Point[] temp = new Point[points.length + RESIZE];
		for (int i = 0; i < size; i++) {
			temp[i] = points[i];
		}
		points = temp;
	}
	public void add1(Point p){
		if (p.getDistance(new Point(0,0)) <= 1){
			add(p);
		}
	}
	public String toString(){
		String ans = "";
		for (int i = 0; i < size; i++) {
			ans += points[i] + " ";
		}
		return ans;
	}
	public static PointContainer q1(PointContainer pc) {
		PointContainer t = new PointContainer();
		for (int i = 0; i < pc.size; i++) {
			if(pc.points[i].getX()>=0 && pc.points[i].getY()>=0){
				t.add(pc.points[i]);
			}
		}
		return t;
	}
	
}
