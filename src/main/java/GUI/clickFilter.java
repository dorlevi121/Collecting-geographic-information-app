package main.java.GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class clickFilter {


	/**
	 * Launch the application.
	 */
	public static void main() {
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



	private JFrame frmGpsFilter;

	/**
	 * Create the application.
	 */
	public clickFilter() {
		initialize();

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
