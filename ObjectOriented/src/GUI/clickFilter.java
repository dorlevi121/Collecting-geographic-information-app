package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import CSV.makeCSV;
import Filters.Filter;
import Filters.FilterGPS;
import Filters.filterList;
import Kml.kml;
import Wifi.WiFi;

import javax.swing.SpringLayout;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class clickFilter {

	private JFrame frmGpsFilter;
	private JTextPane txtpnDd;
	private JEditorPane editorPane;
	private JTextPane txtpnStartLon;
	private JEditorPane editorPane_1;
	private JTextPane txtpnEndLat;
	private JTextPane txtpnEndLon;
	private JEditorPane editorPane_2;
	private JEditorPane editorPane_3;
	double StartLon,StartLat,EndLat,EndLon;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clickFilter window = new clickFilter();
					window.frmGpsFilter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clickFilter() {
		initialize();
		function();
	}

	public void function(){

		 
		
		
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 String a =editorPane.getText();
				 StartLon = Double.parseDouble(a);
				System.out.println(StartLon);
			}
		});


		editorPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String b =editorPane_1.getText();
				 StartLat = Double.parseDouble(b);
				System.out.println(StartLat);

			}
		});


		editorPane_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String c = editorPane_2.getText();
				 EndLat = Double.parseDouble(c);
				System.out.println(EndLat);
			}
		});


		editorPane_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String d = editorPane_3.getText();
				 EndLon = Double.parseDouble(d);
				System.out.println(EndLon);

			}
		});

		Filter filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
		//ArrayList<WiFi> filteredList = filterList.filterList(unionList,filter);
		//Sorting the filteredList by signal (WiFi is implementing Comparable)
		//Collections.sort(filteredList);

		//writeListToCSVFile(filteredList,WriteFolder);
	//	kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGpsFilter = new JFrame();
		frmGpsFilter.setTitle("GPS Filter");
		frmGpsFilter.setBounds(100, 100, 461, 230);
		frmGpsFilter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmGpsFilter.getContentPane().setLayout(springLayout);

		editorPane = new JEditorPane();

		springLayout.putConstraint(SpringLayout.SOUTH, editorPane, -152, SpringLayout.SOUTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, editorPane, -220, SpringLayout.EAST, frmGpsFilter.getContentPane());
		frmGpsFilter.getContentPane().add(editorPane);

		txtpnDd = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, editorPane, 0, SpringLayout.NORTH, txtpnDd);
		springLayout.putConstraint(SpringLayout.WEST, editorPane, 6, SpringLayout.EAST, txtpnDd);
		springLayout.putConstraint(SpringLayout.NORTH, txtpnDd, 19, SpringLayout.NORTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtpnDd, 10, SpringLayout.WEST, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnDd, -152, SpringLayout.SOUTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtpnDd, -380, SpringLayout.EAST, frmGpsFilter.getContentPane());
		txtpnDd.setBackground(SystemColor.menu);
		txtpnDd.setText("Start LAT:");
		frmGpsFilter.getContentPane().add(txtpnDd);

		txtpnStartLon = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, txtpnStartLon, 10, SpringLayout.SOUTH, txtpnDd);
		springLayout.putConstraint(SpringLayout.WEST, txtpnStartLon, 0, SpringLayout.WEST, txtpnDd);
		txtpnStartLon.setText("Start LON:");
		txtpnStartLon.setBackground(SystemColor.menu);
		frmGpsFilter.getContentPane().add(txtpnStartLon);

		editorPane_1 = new JEditorPane();

		springLayout.putConstraint(SpringLayout.NORTH, editorPane_1, 0, SpringLayout.NORTH, txtpnStartLon);
		springLayout.putConstraint(SpringLayout.WEST, editorPane_1, 0, SpringLayout.WEST, editorPane);
		springLayout.putConstraint(SpringLayout.EAST, editorPane_1, 0, SpringLayout.EAST, editorPane);
		frmGpsFilter.getContentPane().add(editorPane_1);

		txtpnEndLat = new JTextPane();
		springLayout.putConstraint(SpringLayout.EAST, txtpnEndLat, 0, SpringLayout.EAST, txtpnDd);
		txtpnEndLat.setText("End LAT:");
		txtpnEndLat.setBackground(SystemColor.menu);
		frmGpsFilter.getContentPane().add(txtpnEndLat);

		txtpnEndLon = new JTextPane();
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnEndLat, -6, SpringLayout.NORTH, txtpnEndLon);
		springLayout.putConstraint(SpringLayout.EAST, txtpnEndLon, 0, SpringLayout.EAST, txtpnDd);
		txtpnEndLon.setText("End LON:");
		txtpnEndLon.setBackground(SystemColor.menu);
		frmGpsFilter.getContentPane().add(txtpnEndLon);

		editorPane_2 = new JEditorPane();

		springLayout.putConstraint(SpringLayout.WEST, editorPane_2, 0, SpringLayout.WEST, editorPane);
		springLayout.putConstraint(SpringLayout.EAST, editorPane_2, 0, SpringLayout.EAST, editorPane);
		frmGpsFilter.getContentPane().add(editorPane_2);

		editorPane_3 = new JEditorPane();



		springLayout.putConstraint(SpringLayout.NORTH, editorPane_3, 125, SpringLayout.NORTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, editorPane_2, -6, SpringLayout.NORTH, editorPane_3);
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnEndLon, 0, SpringLayout.SOUTH, editorPane_3);
		springLayout.putConstraint(SpringLayout.WEST, editorPane_3, 0, SpringLayout.WEST, editorPane);
		springLayout.putConstraint(SpringLayout.EAST, editorPane_3, 0, SpringLayout.EAST, editorPane);
		frmGpsFilter.getContentPane().add(editorPane_3);
	}

}
