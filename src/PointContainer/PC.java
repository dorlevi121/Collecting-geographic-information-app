package PointContainer;

import java.util.Arrays;

public class PC {
	private Point [] arr;// container
	private int len=10; //אורך המערך
	private int size; //כמה תאים באמת מאוכלסים
	
	public PC (){
		arr=new Point [len];
		size=0;
	}
	
	public PC (int len){
		arr=new Point [len];
		size=0;
		this.len=len;
	}
	
	
	}
	public void remove(Point p){
		for (int i = 0; i < size; i++) {
			if(p.eqaul(arr[i])==true){
				for (int j = i+1; j < size; j++) {
				arr[j-1]=arr[j];
				}
				size--;
				arr[size]=null;
				return;
			}
		}
	}
    
	public String toString(){
		return "arr: "+ Arrays.toString(arr)+" size: "+size; 
	}
}
