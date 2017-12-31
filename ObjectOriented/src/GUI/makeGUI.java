package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CSV.makeCSV;
import Wifi.WiFi;

public class makeGUI extends JPanel implements ActionListener {

	private JPanel contentPane;
	private JFileChooser fileChooser;
	private JButton openButton;
	private BufferedReader br;
	private File file;
	int returnVal;
	String currentLine;



	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		JFrame frame = new JFrame("My GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new makeGUI());
		frame.pack();
		frame.setVisible(true);
	}


	public makeGUI(){
		fileChooser = new JFileChooser();
		openButton = new JButton("Select file");

		setPreferredSize(new Dimension(278, 179));
		setLayout(null);

		add(openButton);

		openButton.setBounds(84, 145, 100, 25);
		openButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == openButton){
			returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				makeCSV.getAllcsvFileListFromFolder(getName());
				file = fileChooser.getSelectedFile();
				
				//Read the file!
				try{
					br = new BufferedReader(new FileReader(file));

					while((currentLine = br.readLine()) != null){
						
					}
				} catch (Exception error){
					error.printStackTrace();
				}
			}
		}
	}
}

