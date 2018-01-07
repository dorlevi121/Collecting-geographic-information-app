package PointContainer;

public class main {

	public static void main(String[] args) {
		PC cont =new PC(2);
		Point p1 = new Point(3,5);// פתיחת אובייקט חדש 
		Point p2 = new Point(-2,8);
		Point p3 = new Point (0,0.5);
		cont.add(p1);
		System.out.println(cont.toString());
		cont.add(p2);
		System.out.println(cont.toString());
		cont.add(p3);
		System.out.println(cont.toString());
		cont.add(p3);cont.add(p3);cont.add(p3);cont.add(p3);cont.add(p3);cont.add(p3);cont.add(p3);cont.add(p3);
		System.out.println(cont.toString());
		cont.remove(p3);
		System.out.println(cont.toString());
		cont.add(new Point(9,9));
		System.out.println(cont.toString());
	}

}
