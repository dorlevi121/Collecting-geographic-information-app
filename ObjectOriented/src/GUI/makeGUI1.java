package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CSV.makeCSV;
import Kml.kml;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;

public class makeGUI1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeGUI1 frame = new makeGUI1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public makeGUI1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnOpenFile = new JButton("Browser ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOpenFile, 27, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOpenFile, 40, SpringLayout.WEST, contentPane);
		btnOpenFile.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
		          JFileChooser Filechoose=new JFileChooser();
	                int retval=Filechoose.showOpenDialog(null);
	                if (retval == JFileChooser.APPROVE_OPTION) {
	                    //... The user selected a file, get it, use it.
	                    File file = Filechoose.getSelectedFile();
	                    String s = file.getPath();
	                    System.out.println(s);
	                    ArrayList<String> send = new ArrayList<>();	   
	                    send.add(s);
	                    String save = "C/Users/dorle/Desktop";
	                  //  makeCSV.writeListToCSVFile(makeCSV.readFilesAndAddToUnionList(send), save);
	                   // kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
	                    
	                }
			}
		});
		contentPane.add(btnOpenFile);

	}
}
