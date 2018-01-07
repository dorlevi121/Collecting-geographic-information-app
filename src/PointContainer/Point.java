package PointContainer;


public class Point {

	private double x =0;
	private double y =0;

	public Point(double x,double y){
		this.x=x;
		this.y=y;
	}
	public Point (Point p){// בנאי מעתיק 
		this.x=p.getX();
		this.y=p.getY();
	}

	public double getDistance(Point other){
		double dis = Math.sqrt((other.x-x)*(other.x-x)+(other.y-y)*(other.y-y));
		return dis;
	}
	public double getDistance(double x, double y){
		double dis = Math.sqrt((x-this.x)*(x-this.x)+(this.y-y)*(this.y-y));
		return dis;
	}
	public boolean eqaul (Point p){
		if(p.x== x && p.y==y){
			return true;
		}
		return false;
	}

	
	public String toString(){
		return "( "+ x+" , "+y+" )"; 
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public boolean biggest(Point p){
		if(this.getDistance(new Point (0,0))<=p.getDistance(0,0)){
			return true;
		}
		return false;

	}

}
