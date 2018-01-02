package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import CSV.makeCSV;
import Filters.Filter;
import Filters.FilterGPS;
import Filters.FilterID;
import Filters.FilterTime;
import Filters.filterList;
import Kml.kml;
import Wifi.WiFi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class makeGUI extends JFrame implements ActionListener{

	private String path, startTime, endTime, IDinfo;
	private JFrame frame;
	private double StartLon,StartLat,EndLat,EndLon;
	private JButton btnLoadCsvFolder;
	private ArrayList<WiFi> wifiList;
	private ArrayList<String> names;
	private JCheckBox gpsBox;
	private JCheckBox timeBox;
	private JCheckBox idBox;
	private JPanel panel;
	private JButton btnRun;
	private JButton btnChooseCsvFile;
	private JTextField textField_1;
	private JTextPane textPane_1;
	private JTextField textField_2;
	private JTextPane textPane_2;
	private JTextField textField_3;
	private JTextPane textPane_3;
	private JTextField textField_4;
	private JTextPane textPane;
	private JRadioButton kmlButton;
	private JRadioButton csvButton;
	private JButton Delete;
	private JTextPane whiteTextBox;
	private JTextPane txtpnEndTime;
	private JTextPane txtpnStartTime;
	private JTextField starTimeBox;
	private JTextField endTimeBox;
	private JTextPane txtpnId;
	private JTextField ID;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeGUI window = new makeGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	
	public makeGUI() {
		initialize();
		events();
	}

	/**
	 * Function
	 */
	
	private void events() {

		//User choose sorted by csv folder
		btnLoadCsvFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser Filechoose=new JFileChooser();
				Filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retval=Filechoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					//... The user selected a file, get it, use it.
					File file = Filechoose.getSelectedFile();
					path = file.getPath();
					names = makeCSV.getAllcsvFileListFromFolder(path);
				}
			}
		});


		//User choose sorted by csv file
		btnChooseCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser csvFileChoose=new JFileChooser();
				csvFileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retval=csvFileChoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					//... The user selected a file, get it, use it.
					File file = csvFileChoose.getSelectedFile();
					path = file.getPath();
				}
			}
		});

		//User choose save the file by .csv
		csvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifiList =  makeCSV.readFilesAndAddToUnionList(names);
				//makeCSV.writeListToCSVFile2(wifiList, path);

			}
		});

		//User choose save the file by .kml
		kmlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifiList =  makeCSV.readFilesAndAddToUnionList(names);
				kml.makeKML(wifiList, path);
			}
		});



		//Sort by GPS
		gpsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setVisible(!textField_1.isVisible());
				textField_2.setVisible(!textField_2.isVisible());
				textField_3.setVisible(!textField_3.isVisible());
				textField_4.setVisible(!textField_4.isVisible());
				textPane.setVisible(!textPane.isVisible());
				textPane_1.setVisible(!textPane_1.isVisible());
				textPane_2.setVisible(!textPane_2.isVisible());
				textPane_3.setVisible(!textPane_3.isVisible());

				textField_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String t = textField_1.getText();
						StartLat = Double.parseDouble(t);
					}
				});

				textField_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StartLon = Double.parseDouble(textField_2.getText());
					}
				});

				textField_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EndLat = Double.parseDouble(textField_3.getText());
					}
				});

				textField_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EndLon = Double.parseDouble(textField_4.getText());
					}
				});


			}
		});


		//Sort by time
		timeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtpnStartTime.setVisible(!txtpnStartTime.isVisible());
				txtpnEndTime.setVisible(!txtpnEndTime.isVisible());
				starTimeBox.setVisible(!starTimeBox.isVisible());
				endTimeBox.setVisible(!endTimeBox.isVisible());
				
				starTimeBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startTime = starTimeBox.getText();
					}
				});

				endTimeBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						endTime = endTimeBox.getText();
					}
				});
			}
		});


		//Sort by ID
		idBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtpnId.setVisible(!txtpnId.isVisible());
				ID.setVisible(!ID.isVisible());

				ID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IDinfo = ID.getText();
						System.out.println(IDinfo.toString());

					}
				});

			}
		});



		//Run button 
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Filter filter = null;
				
				//User choose filter by GPS
				if(gpsBox.isSelected()==true && csvButton.isSelected()==true){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by GPS filter created successfuly in"+ path);
				}

				//User choose filter by time
				else if(timeBox.isSelected() == true &&csvButton.isSelected()==true ){
					
					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by time filter created successfuly in"+ path);

				}

				//User choose filter by ID
				else if(idBox.isSelected() == true &&csvButton.isSelected()==true){
				
					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by ID filter created successfuly in"+ path);
				}

				//////////////////////////////////////////////////////
				
				
				//Only csv file
				else if(csvButton.isSelected()==true && !gpsBox.isSelected() && !kmlButton.isSelected() ){
					makeCSV.writeListToCSVFile2(wifiList,path);	
					whiteTextBox.setText("All wifi file created successfuly in " + path);
				}
				
				////////////////////////KML//////////////////////////
				
				//By GPS
				else if(gpsBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true){
					
					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml file by GPS filter created successfuly in"+ path);
				}
				
				//By time
				else if(timeBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true){
					
					filter = new FilterTime(startTime, endTime);//the new filter 

					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml file by time filter created successfuly in"+ path);
				}
				
				//By ID
				else if(idBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true){
					
					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml file by time filter created successfuly in"+ path);
				}

			}
		});

		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < wifiList.size(); i++) {
					wifiList.remove(i);
				}

				whiteTextBox.setText("All data has been deleted.");

			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {


		frame = new JFrame();
		frame.setBounds(100, 100, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon file = new ImageIcon("C:/Users/dorle/Desktop/file.png");

		//folder
		btnLoadCsvFolder = new JButton("Choose Folder",file);
		btnLoadCsvFolder.setBounds(44, 11, 166, 51);

		//filters panel
		panel = new JPanel();
		panel.setBounds(132, 78, 134, 146);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		//csv file
		btnChooseCsvFile = new JButton("Choose csv file");
		btnChooseCsvFile.setBounds(263, 11, 166, 51);

		gpsBox = new JCheckBox("GPS");

		timeBox = new JCheckBox("Time");

		idBox = new JCheckBox("ID");

		btnRun = new JButton("Run");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(gpsBox)
								.addComponent(idBox)
								.addComponent(timeBox)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(43)
										.addComponent(btnRun)))
						.addContainerGap(80, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(gpsBox)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(timeBox)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(idBox)
						.addGap(18)
						.addComponent(btnRun)
						.addGap(50))
				);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnLoadCsvFolder);
		frame.getContentPane().add(btnChooseCsvFile);
		frame.getContentPane().add(panel);

		//gps jump box
		textField_1 = new JTextField();

		textField_1.setVisible(false);
		textField_1.setBounds(354, 91, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();

		textField_2.setVisible(false);
		textField_2.setBounds(354, 122, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();

		textField_3.setVisible(false);
		textField_3.setBounds(354, 153, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();

		textField_4.setVisible(false);
		textField_4.setBounds(354, 184, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);


		textPane_1 = new JTextPane();
		textPane_1.setVisible(false);
		textPane_1.setText("Start LON:");
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(289, 122, 75, 20);
		frame.getContentPane().add(textPane_1);

		textPane_2 = new JTextPane();
		textPane_2.setVisible(false);
		textPane_2.setText("End LAT:");
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setBounds(289, 153, 75, 20);
		frame.getContentPane().add(textPane_2);


		textPane_3 = new JTextPane();
		textPane_3.setVisible(false);
		textPane_3.setText("End LON:");
		textPane_3.setBackground(SystemColor.menu);
		textPane_3.setBounds(289, 184, 75, 20);
		frame.getContentPane().add(textPane_3);

		textPane = new JTextPane();
		textPane.setVisible(false);
		textPane.setText("Start LAT:");
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(289, 91, 75, 20);
		frame.getContentPane().add(textPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Save as", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(47, 78, 75, 146);
		frame.getContentPane().add(panel_1);

		kmlButton = new JRadioButton(".kml");

		csvButton = new JRadioButton(".csv");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(csvButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(kmlButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(csvButton)
						.addGap(3)
						.addComponent(kmlButton)
						.addContainerGap(66, Short.MAX_VALUE))
				);
		panel_1.setLayout(gl_panel_1);

		Delete = new JButton("Delete data");

		Delete.setBounds(478, 11, 134, 51);
		frame.getContentPane().add(Delete);

		whiteTextBox = new JTextPane();
		whiteTextBox.setBounds(44, 235, 221, 115);
		frame.getContentPane().add(whiteTextBox);

		txtpnStartTime = new JTextPane();
		txtpnStartTime.setVisible(false);
		txtpnStartTime.setBackground(SystemColor.menu);
		txtpnStartTime.setText("Start time:");
		txtpnStartTime.setBounds(442, 91, 75, 20);
		frame.getContentPane().add(txtpnStartTime);

		txtpnEndTime = new JTextPane();
		txtpnEndTime.setVisible(false);
		txtpnEndTime.setText("End time:");
		txtpnEndTime.setBackground(SystemColor.menu);
		txtpnEndTime.setBounds(442, 122, 75, 20);
		frame.getContentPane().add(txtpnEndTime);

		starTimeBox = new JTextField();
		starTimeBox.setVisible(false);
		starTimeBox.setBounds(508, 91, 116, 20);
		frame.getContentPane().add(starTimeBox);
		starTimeBox.setColumns(10);

		endTimeBox = new JTextField();
		endTimeBox.setVisible(false);
		endTimeBox.setColumns(10);
		endTimeBox.setBounds(508, 122, 116, 20);
		frame.getContentPane().add(endTimeBox);

		txtpnId = new JTextPane();
		txtpnId.setVisible(false);
		txtpnId.setBackground(SystemColor.menu);
		txtpnId.setText("ID:");
		txtpnId.setBounds(450, 153, 31, 20);
		frame.getContentPane().add(txtpnId);

		ID = new JTextField();
		ID.setVisible(false);
		ID.setBounds(508, 153, 116, 20);
		frame.getContentPane().add(ID);
		ID.setColumns(10);


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
