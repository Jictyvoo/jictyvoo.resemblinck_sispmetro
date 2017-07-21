package br.uefs.ecomp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class MapGUI {

	private JFrame frame;
	private JComboBox originStation;
	private JComboBox destinyStation;
	private JButton btnCalcularRota;
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
		frame.setBounds(100, 100, 490, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		originStation = new JComboBox();
		frame.getContentPane().add(originStation, BorderLayout.WEST);
		
		destinyStation = new JComboBox();
		frame.getContentPane().add(destinyStation, BorderLayout.CENTER);
		
		btnCalcularRota = new JButton("Calcular Rota");
		frame.getContentPane().add(btnCalcularRota, BorderLayout.EAST);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
	}

}
