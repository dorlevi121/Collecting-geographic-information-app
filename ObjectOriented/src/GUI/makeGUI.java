package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import CSV.makeCSV;
import Wifi.WiFi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class makeGUI extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnLoadCsvFile;
	private JTextArea textArea_1;
	private ArrayList<WiFi> wifiList;
	private JCheckBox gpsBox;
	private JCheckBox timeBox;
	private JCheckBox idBox;
	private JPanel panel;
	private JButton btnRun;
	private JTextField textField_1;
	
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

	private void events() {
		
		btnLoadCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		          JFileChooser Filechoose=new JFileChooser();
		          Filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                int retval=Filechoose.showOpenDialog(null);
	                if (retval == JFileChooser.APPROVE_OPTION) {
	                    //... The user selected a file, get it, use it.
	                    File file = Filechoose.getSelectedFile();
	                    String s = file.getPath();
	                    System.out.println(s);
	                 //   ArrayList<String> send = new ArrayList<>();	   
	                    //send.add(s);
	               wifiList =  makeCSV.readFilesAndAddToUnionList(makeCSV.getAllcsvFileListFromFolder(s));
	         String print = makeCSV.writeListToCSVFile2(wifiList, s);
	            	   textArea_1.setText(print);
	                textArea_1.setForeground(Color.BLUE);
	                   // kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
	                }
	                
	                
			}
		});
		
		gpsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.getText();
				System.out.println();
			}
		});
		
		
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(gpsBox.isSelected()==true){
					
				}
					
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
		 

		btnLoadCsvFile = new JButton("Choose Folder",file);
		textArea_1 = new JTextArea();
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(btnLoadCsvFile, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(414, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLoadCsvFile, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		gpsBox = new JCheckBox("GPS");

		
		timeBox = new JCheckBox("Time");
		
		idBox = new JCheckBox("ID");
		
		btnRun = new JButton("Run");
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(gpsBox)
						.addComponent(idBox))
					.addContainerGap(153, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(timeBox)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnRun)
					.addGap(44))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(gpsBox)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeBox)
						.addComponent(btnRun))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(idBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
