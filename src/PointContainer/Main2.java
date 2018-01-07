package PointContainer;

import java.util.Arrays;

import javax.swing.text.AttributeSet.CharacterAttribute;

public class Main2 {

	public static void main(String[] args) {
		PointContainer pc1 = new PointContainer();
		pc1.add(new Point(4,2));
		pc1.add(new Point(5,0));
		pc1.add(new Point(-4,2));
		pc1.add(new Point(22,3));
		pc1.add(new Point(16,-2));
		pc1.add(new Point(4,5));
		pc1.add1(new Point(1.1,1.1));
		pc1.add1(new Point(0.5,0.5));
		System.out.println(pc1.toString());
		PointContainer pc34 = PointContainer.q1(pc1);
		System.out.println(pc34);
		Point p1 = new Point(0.5,0.5);
		Point p2 = new Point(3,3);
		System.out.println(p1.biggest(p2));


	


	}

}
