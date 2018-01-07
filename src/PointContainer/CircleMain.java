package PointContainer;

public class CircleMain {

	public static void main(String[] args) {
		Circle c = new Circle(4, "red");
		Circle other = new Circle (6, "green");
		c.area(4);
		System.out.println(c.area());
		System.out.println(c.toString());
		System.out.println(other.equals(c));
	
	}

}
