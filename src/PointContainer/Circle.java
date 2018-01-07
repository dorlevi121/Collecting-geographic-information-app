package PointContainer;

public class Circle {
 private double radius;
 private String  color;
 
 
 public Circle (){ // בנאי ריק
	 this.radius =1;
	 this.color ="black";
 }
 
 public Circle(double radius, String color){//בנאי מקבל
	 this.radius = radius;
	 this.color= color;
 }
 public Circle (Circle c){//בנאי מעתיק
	 this.radius= c.getRadius();
	 this.color =c.getColor();
 }
 public String toString(){
	 return "Color:"+color+"   Radius:"+radius;
	 
 }
 public double area(){
	 return radius*3.14*radius;
 }
 public boolean equals(Circle other){
	 if(other.radius==radius && other.color == color){
		 return true;
	 }
	 else return false;
 }
public double getRadius() {
	return radius;
}

public void setRadius(double radius) {
	this.radius = radius;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}
 
}
