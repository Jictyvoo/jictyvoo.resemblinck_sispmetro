package br.uefs.ecomp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MapGUI {

	private JFrame frame;
	private JTextField StationOrigin;
	private JTextField StationDestiny;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapGUI window = new MapGUI();
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
	public MapGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		StationOrigin = new JTextField();
		frame.getContentPane().add(StationOrigin, BorderLayout.WEST);
		StationOrigin.setColumns(5);
		
		StationDestiny = new JTextField();
		frame.getContentPane().add(StationDestiny, BorderLayout.CENTER);
		StationDestiny.setColumns(5);
	}

}
