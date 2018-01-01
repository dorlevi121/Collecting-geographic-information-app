package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import CSV.makeCSV;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class fd extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fd window = new fd();
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
	public fd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		 ImageIcon file = new ImageIcon("https://www.github.com/BarZamsky/OO_Project/blob/master/file.png");
		 

		JButton btnLoadCsvFile = new JButton("load csv file",file);
		btnLoadCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		          JFileChooser Filechoose=new JFileChooser();
	                int retval=Filechoose.showOpenDialog(null);
	                if (retval == JFileChooser.APPROVE_OPTION) {
	                    //... The user selected a file, get it, use it.
	                    File file = Filechoose.getSelectedFile();
	                    String s = file.getPath();
	                    System.out.println(s);
	                    ArrayList<String> send = new ArrayList<>();	   
	                    send.add(s);
	                    String save = "C:\\Users\\dorle\\Desktop";
	                makeCSV.writeListToCSVFile2(makeCSV.readFilesAndAddToUnionList(send), save);
	                   // kml.makeKML(makeCSV.writeListToCSVFile(filteredList,WriteFolder),WriteFolder);
	                }
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLoadCsvFile, 39, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLoadCsvFile, 32, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnLoadCsvFile);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
