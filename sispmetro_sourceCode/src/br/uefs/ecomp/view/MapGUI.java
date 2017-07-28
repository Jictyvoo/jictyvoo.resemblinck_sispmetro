package br.uefs.ecomp.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.uefs.ecomp.controller.Controller;

public class MapGUI {

	private JFrame frame;
	private JTextField txtWaitTimeInput;

	/**
	 * Create the application.
	 */
	public MapGUI() {
		initialize();
	}
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblSubwaymap = new JLabel();
		ImageIcon map = new ImageIcon("../SUBWAYMAP.png");
		lblSubwaymap.setBounds(10, 28, 454, 176);
		lblSubwaymap.setIcon(new ImageIcon(map.getImage().getScaledInstance(lblSubwaymap.getWidth(),lblSubwaymap.getHeight(), Image.SCALE_DEFAULT)));
		
		frame.getContentPane().add(lblSubwaymap);
		
		JLabel lblStationOrigin = new JLabel("Esta\u00E7\u00E3o de Origem:");
		lblStationOrigin.setBounds(70, 231, 121, 14);
		frame.getContentPane().add(lblStationOrigin);
		
		JComboBox<String> originStation = new JComboBox<String>();
		originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		originStation.setBounds(26, 256, 190, 20);
		frame.getContentPane().add(originStation);
		
		JLabel lblStationDestiny = new JLabel("Esta\u00E7\u00E3o de Destino:");
		lblStationDestiny.setBounds(286, 231, 121, 14);
		frame.getContentPane().add(lblStationDestiny);
		
		JComboBox<String> destinyStation = new JComboBox<String>();
		destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		destinyStation.setBounds(246, 256, 190, 20);
		frame.getContentPane().add(destinyStation);
		
		JButton btnSearchMinorWay = new JButton("Tra\u00E7ar Rota");
		btnSearchMinorWay.setBounds(36, 287, 129, 23);
		btnSearchMinorWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*here came the event to search the minor way and draw in the screen*/
			}
		});
		frame.getContentPane().add(btnSearchMinorWay);
		
		JLabel lblTempoDeEspera = new JLabel("Tempo de Espera da Esta\u00E7\u00E3o:");
		lblTempoDeEspera.setBounds(186, 291, 171, 14);
		frame.getContentPane().add(lblTempoDeEspera);
		
		txtWaitTimeInput = new JTextField();
		txtWaitTimeInput.setBounds(367, 288, 28, 20);
		frame.getContentPane().add(txtWaitTimeInput);
		txtWaitTimeInput.setColumns(10);
		
		JLabel lblminutos = new JLabel("(minutos)");
		lblminutos.setBounds(401, 291, 63, 14);
		frame.getContentPane().add(lblminutos);
		
		JLabel lblTotalTravelTime = new JLabel("Total Travel Time: ");
		lblTotalTravelTime.setBounds(324, 11, 140, 14);
		frame.getContentPane().add(lblTotalTravelTime);
		frame.setBounds(100, 100, 490, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
