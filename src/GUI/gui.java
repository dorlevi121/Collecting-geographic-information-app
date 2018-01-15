package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import CSV.makeCSV;
import Filters.Filter;
import Filters.FilterGPS;
import Filters.FilterID;
import Filters.FilterMAC;
import Filters.FilterTime;
import Filters.filterList;
import GPSPoints.GPSPoint;
import Kml.kml;
import MainWatch.watch;
import Wifi.WiFi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Panel;
import weightedCenterPoint.Algo_2Function;
import weightedCenterPoint.algo1;
import weightedCenterPoint.algo2Network;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

public class gui extends JFrame implements ActionListener{

	private GPSPoint _point;
	private String userMAC1, userSignal1, userMAC2, userSignal2, userMAC3, userSignal;
	private String IP,URL,User,Table,Password;
	private JTextField btnBrowserCombFile;
	private String path, startTime, endTime, IDinfo, MAC;
	private JFrame frmCollectinggeographicInformationApp;
	private double StartLon,StartLat,EndLat,EndLon;
	private ArrayList<WiFi> wifiList;
	private ArrayList<WiFi> noFilter;
	private ArrayList<String> names;
	private JCheckBox gpsBox;
	private JCheckBox timeBox;
	private JCheckBox idBox;
	private JPanel panel;
	private JButton btnRun;
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
	public JTextPane whiteTextBox;
	private JTextPane txtpnEndTime;
	private JTextPane txtpnStartTime;
	private JTextField starTimeBox;
	private JTextField endTimeBox;
	private JTextPane txtpnId;
	private JTextField ID;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmFolder;
	private JMenu mnEdit;
	private JMenuItem mntmDeleteData;
	private JMenuItem mntmNoFilter;
	private JMenuItem mntmFile_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private Panel panel_2;
	private JRadioButton algo1Box;
	private JTextPane txtpnMacAddress;
	private JTextField textField_5;
	private JSlider slider;
	private JPanel panel_3;
	private Panel panel_4;
	private JPanel panel_5;
	private JRadioButton algo2Box;
	private JButton btnBrowserFile;
	private JMenuItem mntmAbout;
	private JButton btnBrowserComdFile;
	private JRadioButton algo2ByMac;
	private JTextField byMAC1;
	private JTextPane txtpnMac;
	private JTextField byMAC2;
	private JTextPane txtpnMac_1;
	private JTextField byMAC3;
	private JTextPane txtpnMac_2;
	private JTextField signal1;
	private JTextField signal2;
	private JTextField signal3;
	private JPanel panel_6;
	private JTextField IPtext;
	private JTextField URLtext;
	private JTextField tableText;
	private JTextField passwordText;
	private JTextField userText;
	private JScrollBar scrollBar;
	private JScrollBar scrollBar_1;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmCollectinggeographicInformationApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */

	public gui() {
		initialize();
		events();
	}

	/**
	 * Function
	 */

	private void events() {

		//User choose sorted by csv folder
		mntmFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userDir = System.getProperty("user.home");
				JFileChooser Filechoose=new JFileChooser(userDir +"/Desktop");
				Filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retval=Filechoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					//... The user selected a file, get it, use it.
					File file = Filechoose.getSelectedFile();
					path = file.getPath();
					names = makeCSV.getAllcsvFileListFromFolder(path);
				}}
		});


		//User choose sorted by csv file

		mntmFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userDir = System.getProperty("user.home");
				JFileChooser csvFilechoose=new JFileChooser(userDir +"/Desktop");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
				csvFilechoose.setFileFilter(filter);				int returnValue = csvFilechoose.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = csvFilechoose.getSelectedFile();
					ArrayList<String> d = new ArrayList<>();
					d.add(selectedFile.getPath());
					names=d;
					whiteTextBox.setText("File path:"+selectedFile.getPath());
				}

			}
		});



		//User choose save the file by .csv
		csvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifiList =  makeCSV.readFilesAndAddToUnionList(names);
				for (int i = 0; i < wifiList.size(); i++) {
					if(i+1==wifiList.size()){
						whiteTextBox.setText("Number of wifi:"+wifiList.get(i).getNumOfWifi());
					} 
				}


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

		///////////////Data Base ////////////////////
		
		IPtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IP = IPtext.getText();
			}
		});

		URLtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL = URLtext.getText();
			}
		});
		
		tableText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table = tableText.getText();
			}
		});
		
		passwordText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Password = passwordText.getText();
			}
		});
		
		userText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = userText.getText();
			}
		});
		
		////////////////////Run button ////////////////////
		
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Filter filter = null;

				//User choose filter by GPS
				if(gpsBox.isSelected()==true && csvButton.isSelected()==true && !kmlButton.isSelected()==true
						&& !idBox.isSelected() && !timeBox.isSelected()){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by GPS filter created successfuly in"+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				//User choose filter by time
				else if(timeBox.isSelected() == true &&csvButton.isSelected()==true && !kmlButton.isSelected()==true
						&& !idBox.isSelected() && !gpsBox.isSelected() ){

					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by time filter created successfuly in"+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				//User choose filter by ID
				else if(idBox.isSelected() == true &&csvButton.isSelected()==true && !kmlButton.isSelected()==true
						&& !timeBox.isSelected() && !gpsBox.isSelected()){

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					makeCSV.writeListToCSVFile(filteredList,path);
					whiteTextBox.setText("Csv file by ID filter created successfuly in"+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				//////////////////////////////////////////////////////


				//Only csv file
				else if(csvButton.isSelected()==true && !gpsBox.isSelected() && !kmlButton.isSelected()
						&& !idBox.isSelected() && !timeBox.isSelected()){
					makeCSV.writeListToCSVFile2(wifiList,path);	
					whiteTextBox.setText("All wifi file created successfuly in " + path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				////////////////////////KML//////////////////////////

				//By GPS
				else if(gpsBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true 
						&& !idBox.isSelected() && !timeBox.isSelected()){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(filteredList,path);
					whiteTextBox.setText("kml file by GPS filter created successfuly in"+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				//By time
				else if(timeBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true
						&& !idBox.isSelected() && !gpsBox.isSelected()){

					filter = new FilterTime(startTime, endTime);//the new filter 

					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(filteredList,path);
					whiteTextBox.setText("kml file by time filter created successfuly in"+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				//By ID
				else if(idBox.isSelected()==true && !csvButton.isSelected() && kmlButton.isSelected()==true
						&& !gpsBox.isSelected() && !timeBox.isSelected()){

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml file by time filter created successfuly in "+ path);
					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				/////////////////// KML and CSV /////////////////////////////////

				//By GPS
				else if(gpsBox.isSelected()==true && csvButton.isSelected() && kmlButton.isSelected()==true){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml and csv files by GPS filter created successfuly in "+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				//By time
				else if(timeBox.isSelected()==true && csvButton.isSelected() && kmlButton.isSelected()==true){

					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml and csv files by time filter created successfuly in "+ path);
					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				//By ID
				else if(idBox.isSelected()==true && csvButton.isSelected() && kmlButton.isSelected()==true){

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList);
					kml.makeKML(makeCSV.writeListToCSVFile(filteredList,path),path);
					whiteTextBox.setText("kml and csv files by time filter created successfuly in "+ path);

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				/////////////////////////// OR NOT AND //////////////////////////////////

				//By GPS and time
				else if(gpsBox.isSelected()==true && timeBox.isSelected()==true && csvButton.isSelected()){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList2 = filterList.filterList(filteredList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList2);

					if(kmlButton.isSelected()==true){
						kml.makeKML(makeCSV.writeListToCSVFile(filteredList2,path), path);
						whiteTextBox.setText("kml and csv files by GPS and time filter created successfuly in "+ path);
					}
					else{
						makeCSV.writeListToCSVFile(filteredList2,path);
						whiteTextBox.setText("csv file by GPS and time filter created successfuly in "+ path);

					}
					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				///GPS and ID
				else if(gpsBox.isSelected()==true && idBox.isSelected()==true && csvButton.isSelected()){

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList2 = filterList.filterList(filteredList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList2);
					if(kmlButton.isSelected()==true){
						kml.makeKML(makeCSV.writeListToCSVFile(filteredList2,path), path);
						whiteTextBox.setText("kml and csv files by GPS and id filter created successfuly in "+ path);
					}
					else{
						makeCSV.writeListToCSVFile(filteredList2,path);
						whiteTextBox.setText("csv file by GPS and id filter created successfuly in "+ path);

					}
					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				///ID and time
				else if(idBox.isSelected()==true && timeBox.isSelected()==true && csvButton.isSelected()){

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);


					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList2 = filterList.filterList(filteredList,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList2);

					if(kmlButton.isSelected()==true){
						kml.makeKML(makeCSV.writeListToCSVFile(filteredList2,path), path);
						whiteTextBox.setText("kml and csv files by ID and time filters created successfuly in "+ path);
					}
					else{
						makeCSV.writeListToCSVFile(filteredList2,path);
						whiteTextBox.setText("csv file by ID and time filters created successfuly in "+ path);
					}

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}


				///GPS,ID and time
				else if(idBox.isSelected()==true && timeBox.isSelected()==true && (csvButton.isSelected() || kmlButton.isSelected())
						&& gpsBox.isSelected()==true){

					filter = new FilterID(IDinfo);
					ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

					filter = new FilterGPS(StartLon, StartLat, EndLon,  EndLat);
					ArrayList<WiFi> filteredList1 = filterList.filterList(filteredList,filter);


					filter = new FilterTime(startTime, endTime);//the new filter 
					ArrayList<WiFi> filteredList2 = filterList.filterList(filteredList1,filter);
					//Sorting the filteredList by signal (WiFi is implementing Comparable)
					Collections.sort(filteredList2);

					if(kmlButton.isSelected()==true){
						kml.makeKML(makeCSV.writeListToCSVFile(filteredList2,path), path);
						whiteTextBox.setText("kml and csv files by GPS,ID and time filter created successfuly in\n"+ path);
					}
					else{
						makeCSV.writeListToCSVFile(filteredList2,path);
						whiteTextBox.setText("csv file by GPS,ID and time filter created successfuly in"+ path);

					}

					try {
						watch folderWatch = new watch(path);
						Thread watchThread = new Thread(folderWatch);
						watchThread.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}


			}
		});

		////////////////////////// Delete /////////////////////////////

		//Delete all wifi list
		mntmDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < wifiList.size(); i++) {
					noFilter.add(wifiList.get(i));
					wifiList.remove(i);
				}
				whiteTextBox.setText("All data has been deleted.");
			}
		});

		//No filter
		mntmNoFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifiList.addAll(noFilter);

			}
		});



		//////////////////// Algorithms ///////////////////////////


		//algo1:
		algo1Box.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				wifiList =  makeCSV.readFilesAndAddToUnionList(names);
				textField_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MAC = textField_5.getText();

						int check =	slider.getValue();
						Filter filter = new FilterMAC(MAC);
						ArrayList<WiFi> filteredList = filterList.filterList(wifiList,filter);

						//Sorting the filteredList by signal (WiFi is implementing Comparable)
						Collections.sort(filteredList);
						algo1.avgGPSPoint(filteredList,check,path);
						whiteTextBox.setText("Router Point  file created successfuly in " + path);
					}
				});

				try {
					watch folderWatch = new watch(path);
					Thread watchThread = new Thread(folderWatch);
					watchThread.start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		///////////////////////////////////


		//algo 2:
		algo2Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBrowserFile.setVisible(!btnBrowserFile.isVisible());
				btnBrowserComdFile.setVisible(!btnBrowserComdFile.isVisible());


				btnBrowserFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String userDir = System.getProperty("user.home");
						JFileChooser csvFilechoose=new JFileChooser(userDir +"/Desktop");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
						csvFilechoose.setFileFilter(filter);			
						int returnValue = csvFilechoose.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = csvFilechoose.getSelectedFile();
							whiteTextBox.setText("File path:"+selectedFile.getPath());

							Algo_2Function a = new Algo_2Function();
							a.readFile(selectedFile.getAbsolutePath());

							btnBrowserComdFile.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									String userDir = System.getProperty("user.home");
									JFileChooser comdFile=new JFileChooser(userDir +"/Desktop");
									FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
									comdFile.setFileFilter(filter);			
									int returnValue = comdFile.showOpenDialog(null);
									if (returnValue == JFileChooser.APPROVE_OPTION) {
										File selectedFile1 = comdFile.getSelectedFile();
										whiteTextBox.setText("File path:"+selectedFile1.getPath());
										a.readCombAllFile(selectedFile1.getAbsolutePath());
										a.search_Mac();
										String userHome = System.getProperty("user.home");
										File testFile = new File(userHome+"/Desktop", "complete_File.csv");
										a.toCsv(testFile.getPath());
										whiteTextBox.setText("File path:"+testFile.getPath());

									}
								}});


							try {
								watch folderWatch = new watch(path);
								Thread watchThread = new Thread(folderWatch);
								watchThread.start();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}


				});


			}
		});


		algo2ByMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtpnMac.setVisible(!txtpnMac.isVisible());
				byMAC1.setVisible(!byMAC1.isVisible());
				byMAC2.setVisible(!byMAC2.isVisible());
				txtpnMac_1.setVisible(!txtpnMac_1.isVisible());
				byMAC3.setVisible(!byMAC3.isVisible());
				txtpnMac_2.setVisible(!txtpnMac_2.isVisible());
				signal1.setVisible(!signal1.isVisible());
				signal2.setVisible(!signal2.isVisible());
				signal3.setVisible(!signal3.isVisible());
				btnBrowserFile.setVisible(!btnBrowserFile.isVisible());
				btnBrowserComdFile.setVisible(!btnBrowserComdFile.isVisible());

				Algo_2Function b = new Algo_2Function();
				 ArrayList<algo2Network> threeList = new ArrayList<algo2Network>();

				btnBrowserFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String userDir = System.getProperty("user.home");
						JFileChooser csvFilechoose=new JFileChooser(userDir +"/Desktop");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
						csvFilechoose.setFileFilter(filter);			
						int returnValue = csvFilechoose.showOpenDialog(null);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							File selectedFile = csvFilechoose.getSelectedFile();
							whiteTextBox.setText("File path:"+selectedFile.getPath());
							b.readFile(selectedFile.getAbsolutePath());
					
							btnBrowserComdFile.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									String userDir = System.getProperty("user.home");
									JFileChooser comdFile=new JFileChooser(userDir +"/Desktop");
									FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
									comdFile.setFileFilter(filter);			
									int returnValue = comdFile.showOpenDialog(null);
									if (returnValue == JFileChooser.APPROVE_OPTION) {
										File selectedFile1 = comdFile.getSelectedFile();
										whiteTextBox.setText("File path:"+selectedFile1.getPath());
										b.readCombAllFile(selectedFile1.getAbsolutePath());
										String userHome = System.getProperty("user.home");
										File testFile = new File(userHome+"/Desktop", "complete_File.csv");
										whiteTextBox.setText("File path:"+testFile.getPath());
									}
								}});

						}
					}
				});

				byMAC1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						signal1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(signal1!=null)
									threeList.add(new algo2Network(byMAC1.getText(),Double.parseDouble(signal1.getText())));
								else
									threeList.add(new algo2Network(byMAC1.getText(),Double.parseDouble(signal1.getText())));
							}
						});
					}
				});




				byMAC2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						signal2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(signal2!=null)
									threeList.add(new algo2Network(byMAC2.getText(),Double.parseDouble(signal2.getText())));
								else
									threeList.add(new algo2Network(byMAC2.getText(),Double.parseDouble(signal2.getText())));
							}
						});
					}
				});


				byMAC3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						signal3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(signal3!=null)
									threeList.add(new algo2Network(byMAC3.getText(),Double.parseDouble(signal3.getText())));
								else
									threeList.add(new algo2Network(byMAC3.getText(),Double.parseDouble(signal3.getText())));
								
								b.search_mac(threeList);
								String path1 = System.getProperty("user.home");
								path =path1 +"/Desktop" + "/_complete_File_Algo_2-BM2_ts1.csv";
								b.PointToCsv(path);
								whiteTextBox.setText("Path file:" + path);
							}

							
						});
			
					

						try {

							watch folderWatch = new watch(path);
							Thread watchThread = new Thread(folderWatch);
							watchThread.start();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

			


			}
		});

		////////////////////////// About ////////////////////////////

		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clickFilter aboutClick = new clickFilter();
				clickFilter.main();
			}
		});




	}


	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {


		frmCollectinggeographicInformationApp = new JFrame();
		frmCollectinggeographicInformationApp.setFont(new Font("Arial", Font.BOLD, 12));
		frmCollectinggeographicInformationApp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-wi-fi-50.png"));
		frmCollectinggeographicInformationApp.setTitle("Collecting geographic information app");
		frmCollectinggeographicInformationApp.setBounds(100, 100, 850, 500);
		frmCollectinggeographicInformationApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//filters panel
		panel = new JPanel();
		panel.setBounds(286, 11, 116, 160);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		gpsBox = new JCheckBox("GPS");
		gpsBox.setFont(new Font("Arial", Font.PLAIN, 11));
		gpsBox.setToolTipText("lat: 32.xxxxxx || lon: 35.xxxxxx");

		timeBox = new JCheckBox("Time");
		timeBox.setFont(new Font("Arial", Font.PLAIN, 11));
		timeBox.setToolTipText("Start time: yyyy-MM-dd HH:mm:ss" + " || End time: yyyy-MM-dd HH:mm:ss");

		idBox = new JCheckBox("ID");
		idBox.setFont(new Font("Arial", Font.PLAIN, 11));
		idBox.setToolTipText("Enter ID");

		btnRun = new JButton("Run");
		btnRun.setFont(new Font("Arial", Font.PLAIN, 11));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(gpsBox)
						.addComponent(idBox)
						.addComponent(timeBox)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
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
					.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		panel.setLayout(gl_panel);
		frmCollectinggeographicInformationApp.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(807, 152, 17, 260);
		frmCollectinggeographicInformationApp.getContentPane().add(scrollPane);
		
		scrollBar_1 = new JScrollBar();
		scrollPane.setViewportView(scrollBar_1);
		frmCollectinggeographicInformationApp.getContentPane().add(panel);

		//gps jump box
		textField_1 = new JTextField();

		textField_1.setVisible(false);
		textField_1.setBounds(510, 28, 86, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();

		textField_2.setVisible(false);
		textField_2.setBounds(510, 59, 86, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();

		textField_3.setVisible(false);
		textField_3.setBounds(510, 90, 86, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();

		textField_4.setVisible(false);
		textField_4.setBounds(510, 121, 86, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textField_4);
		textField_4.setColumns(10);


		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Arial", Font.BOLD, 11));
		textPane_1.setVisible(false);
		textPane_1.setText("Start LON:");
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(425, 59, 75, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textPane_1);

		textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("Arial", Font.BOLD, 11));
		textPane_2.setVisible(false);
		textPane_2.setText("End LAT:");
		textPane_2.setBackground(SystemColor.menu);
		textPane_2.setBounds(425, 90, 75, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textPane_2);


		textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Arial", Font.BOLD, 11));
		textPane_3.setVisible(false);
		textPane_3.setText("End LON:");
		textPane_3.setBackground(SystemColor.menu);
		textPane_3.setBounds(425, 121, 75, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textPane_3);

		textPane = new JTextPane();
		textPane.setFont(new Font("Arial", Font.BOLD, 11));
		textPane.setVisible(false);
		textPane.setText("Start LAT:");
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(425, 28, 75, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(textPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Save as", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 75, 160);
		frmCollectinggeographicInformationApp.getContentPane().add(panel_1);

		kmlButton = new JRadioButton(".kml");
		kmlButton.setFont(new Font("Arial", Font.PLAIN, 11));

		csvButton = new JRadioButton(".csv");
		csvButton.setFont(new Font("Arial", Font.PLAIN, 11));

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

		whiteTextBox = new JTextPane();

		whiteTextBox.setBackground(SystemColor.controlLtHighlight);
		whiteTextBox.setBounds(613, 152, 211, 260);
		frmCollectinggeographicInformationApp.getContentPane().add(whiteTextBox);

		
		txtpnStartTime = new JTextPane();
		txtpnStartTime.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnStartTime.setVisible(false);
		txtpnStartTime.setBackground(SystemColor.menu);
		txtpnStartTime.setText("Start time:");
		txtpnStartTime.setBounds(613, 28, 68, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(txtpnStartTime);

		txtpnEndTime = new JTextPane();
		txtpnEndTime.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnEndTime.setVisible(false);
		txtpnEndTime.setText("End time:");
		txtpnEndTime.setBackground(SystemColor.menu);
		txtpnEndTime.setBounds(606, 59, 75, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(txtpnEndTime);

		starTimeBox = new JTextField();
		starTimeBox.setVisible(false);
		starTimeBox.setBounds(691, 28, 116, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(starTimeBox);
		starTimeBox.setColumns(10);

		endTimeBox = new JTextField();
		endTimeBox.setVisible(false);
		endTimeBox.setColumns(10);
		endTimeBox.setBounds(691, 59, 116, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(endTimeBox);

		txtpnId = new JTextPane();
		txtpnId.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnId.setVisible(false);
		txtpnId.setBackground(SystemColor.menu);
		txtpnId.setText("ID:");
		txtpnId.setBounds(614, 90, 31, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(txtpnId);

		ID = new JTextField();
		ID.setVisible(false);
		ID.setBounds(691, 90, 116, 20);
		frmCollectinggeographicInformationApp.getContentPane().add(ID);
		ID.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "algo 1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 263, 213, 152);
		frmCollectinggeographicInformationApp.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		panel_2 = new Panel();
		panel_2.setBounds(6, 16, 201, 129);
		panel_3.add(panel_2);
		panel_2.setBackground(SystemColor.menu);
		panel_2.setFont(new Font("Aharoni", Font.PLAIN, 12));

		algo1Box = new JRadioButton("Algorithm 1");

		algo1Box.setFont(new Font("Arial", Font.PLAIN, 13));

		txtpnMacAddress = new JTextPane();
		txtpnMacAddress.setBackground(SystemColor.menu);
		txtpnMacAddress.setForeground(Color.BLACK);
		txtpnMacAddress.setFont(new Font("Arial", Font.BOLD, 12));
		txtpnMacAddress.setText("MAC address:");

		textField_5 = new JTextField();

		textField_5.setColumns(10);

		slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMinorTickSpacing(1);
		slider.setFont(new Font("Arial", Font.BOLD, 11));
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(1);
		slider.setMaximum(5);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addGap(7)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(algo1Box)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(3)
										.addComponent(txtpnMacAddress, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
										.addGap(3)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
						.addGap(6)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
				);
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addGap(7)
						.addComponent(algo1Box)
						.addGap(19)
						.addComponent(txtpnMacAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup()
						.addGap(12)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
				);
		panel_2.setLayout(gl_panel_2);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{algo1Box}));

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "algo 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(223, 263, 386, 152);
		frmCollectinggeographicInformationApp.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		panel_4 = new Panel();
		panel_4.setBounds(10, 20, 366, 122);
		panel_5.add(panel_4);

		algo2Box = new JRadioButton("By file");

		algo2Box.setFont(new Font("Arial", Font.PLAIN, 13));

		btnBrowserFile = new JButton("Browser no gps file");
		btnBrowserFile.setVisible(false);


		btnBrowserFile.setFont(new Font("Arial", Font.PLAIN, 11));

		btnBrowserComdFile = new JButton("Browser comd file");
		btnBrowserComdFile.setVisible(false);
		btnBrowserComdFile.setFont(new Font("Arial", Font.PLAIN, 11));

		byMAC1 = new JTextField();

		byMAC1.setColumns(10);
		byMAC1.setVisible(false);
		txtpnMac = new JTextPane();
		txtpnMac.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnMac.setBackground(SystemColor.menu);
		txtpnMac.setText("MAC 1:");
		txtpnMac.setVisible(false);

		algo2ByMac = new JRadioButton("By 3 MAC and signals");

		algo2ByMac.setFont(new Font("Arial", Font.PLAIN, 13));

		byMAC2 = new JTextField();

		byMAC2.setColumns(10);
		byMAC2.setVisible(false);

		txtpnMac_1 = new JTextPane();
		txtpnMac_1.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnMac_1.setBackground(SystemColor.menu);
		txtpnMac_1.setText("MAC 2:");
		txtpnMac_1.setVisible(false);

		byMAC3 = new JTextField();

		byMAC3.setColumns(10);
		byMAC3.setVisible(false);

		txtpnMac_2 = new JTextPane();
		txtpnMac_2.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnMac_2.setBackground(SystemColor.menu);
		txtpnMac_2.setText("MAC 3:");
		txtpnMac_2.setVisible(false);

		signal1 = new JTextField();

		signal1.setColumns(10);
		signal1.setVisible(false);

		signal2 = new JTextField();

		signal2.setColumns(10);
		signal2.setVisible(false);

		signal3 = new JTextField();

		signal3.setColumns(10);
		signal3.setVisible(false);

		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
				gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(algo2Box, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(algo2ByMac))
								.addGroup(gl_panel_4.createSequentialGroup()
										.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnBrowserComdFile, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnBrowserFile, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
														.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																.addComponent(txtpnMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtpnMac_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																.addComponent(byMAC2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(byMAC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_panel_4.createSequentialGroup()
														.addComponent(txtpnMac_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(byMAC3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGap(18)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(signal1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(signal2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(signal3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_panel_4.setVerticalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
								.addComponent(algo2Box, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(algo2ByMac, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(btnBrowserFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnBrowserComdFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(26))
								.addGroup(gl_panel_4.createSequentialGroup()
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addComponent(txtpnMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
														.addComponent(byMAC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(signal1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addComponent(txtpnMac_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
														.addComponent(byMAC2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(signal2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addComponent(txtpnMac_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
														.addComponent(byMAC3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(signal3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())))
				);
		panel_4.setLayout(gl_panel_4);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "From DB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(95, 11, 181, 160);
		frmCollectinggeographicInformationApp.getContentPane().add(panel_6);
		
		JTextPane txtpnIp = new JTextPane();
		txtpnIp.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnIp.setBackground(SystemColor.menu);
		txtpnIp.setText("IP:");
		
		JTextPane txtpnUrl = new JTextPane();
		txtpnUrl.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnUrl.setBackground(SystemColor.menu);
		txtpnUrl.setText("URL:");
		
		JTextPane txtpnUser = new JTextPane();
		txtpnUser.setBackground(SystemColor.menu);
		txtpnUser.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnUser.setText("User:");
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBackground(SystemColor.menu);
		txtpnPassword.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnPassword.setText("Password:");
		
		JTextPane txtpnTable = new JTextPane();
		txtpnTable.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnTable.setBackground(SystemColor.menu);
		txtpnTable.setText("Table:");
		
		IPtext = new JTextField();

		IPtext.setColumns(10);
		
		URLtext = new JTextField();

		URLtext.setColumns(10);
		
		tableText = new JTextField();

		tableText.setColumns(10);
		
		passwordText = new JTextField();

		passwordText.setColumns(10);
		
		userText = new JTextField();

		userText.setColumns(10);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
							.addComponent(txtpnTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addComponent(tableText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnIp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING, false)
								.addComponent(URLtext)
								.addComponent(IPtext)))
						.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
							.addComponent(txtpnPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(txtpnUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(txtpnIp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(IPtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(URLtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnPassword, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordText, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(tableText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
	
		 
		panel_6.setLayout(gl_panel_6);
		

		
		menuBar = new JMenuBar();
		frmCollectinggeographicInformationApp.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmFolder = new JMenuItem("Folder");
		mntmFolder.setIcon(new ImageIcon("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-Folder-25.png"));

		mnFile.add(mntmFolder);

		separator = new JSeparator();
		mnFile.add(separator);

		mntmFile_1 = new JMenuItem("File");
		mntmFile_1.setIcon(new ImageIcon("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-Microsoft Excel-25.png"));

		mnFile.add(mntmFile_1);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmDeleteData = new JMenuItem("Delete data");
		mntmDeleteData.setIcon(new ImageIcon("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-Trash-25.png"));

		mnEdit.add(mntmDeleteData);

		separator_1 = new JSeparator();
		mnEdit.add(separator_1);

		mntmNoFilter = new JMenuItem("No filter");
		mntmNoFilter.setIcon(new ImageIcon("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-Cancel-25.png"));

		mnEdit.add(mntmNoFilter);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon("C:\\Users\\dorle\\Documents\\GitHub\\Collecting-geographic-information-app\\GUI icons\\icons8-About-25.png"));

		mnAbout.add(mntmAbout);


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
