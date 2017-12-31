package GUI;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

public class GUI_ex3 extends Frame{
	
	public GUI_ex3(){
		
		setTitle("Yarden");
		setSize(300, 300);
		addWindowListener(new WindowAdapter() {
			public void winclosing(WindowEvent win){
				System.exit(0);
			}
		});
		
		setLayout(new FlowLayout());
		add(new Label("Label A"));
		add(new Label("Label B"));
		add(new Button("Yaden test bu"));
		
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUI_ex3 d = new GUI_ex3();
		d.setVisible(true);
	}

}
