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
import java.awt.Font;
import java.awt.Toolkit;

public class clickFilter {

	private JFrame frmGpsFilter;
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
		frmGpsFilter.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\ObjectOriented\\GUI icons\\icons8-wi-fi-50.png"));
		frmGpsFilter.setTitle("About");
		frmGpsFilter.setBounds(100, 100, 461, 230);
		frmGpsFilter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmGpsFilter.getContentPane().setLayout(springLayout);
		JTextPane txtpnThisSoftwareWas = new JTextPane();
		txtpnThisSoftwareWas.setBackground(SystemColor.menu);
		txtpnThisSoftwareWas.setFont(new Font("Arial", Font.PLAIN, 13));
		txtpnThisSoftwareWas.setText("This software was written by Dor Levy and Yarden Mizrahi");
		springLayout.putConstraint(SpringLayout.NORTH, txtpnThisSoftwareWas, 22, SpringLayout.NORTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtpnThisSoftwareWas, 42, SpringLayout.WEST, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnThisSoftwareWas, -10, SpringLayout.SOUTH, frmGpsFilter.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtpnThisSoftwareWas, 389, SpringLayout.WEST, frmGpsFilter.getContentPane());
		frmGpsFilter.getContentPane().add(txtpnThisSoftwareWas);
	}
}
