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
import br.uefs.ecomp.util.IStack;

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
		
		frame.setBounds(100, 100, 890, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSubwaymap = new JLabel();
		lblSubwaymap.setBounds(10, 36, 854, 397);
		lblSubwaymap.setIcon(new ImageIcon(Controller.getInstance().getMap().getImage().getScaledInstance(lblSubwaymap.getWidth(),lblSubwaymap.getHeight(), Image.SCALE_DEFAULT)));
		
		frame.getContentPane().add(lblSubwaymap);
		
		JLabel lblStationOrigin = new JLabel("Esta\u00E7\u00E3o de Origem:");
		lblStationOrigin.setBounds(259, 491, 121, 14);
		frame.getContentPane().add(lblStationOrigin);
		
		JComboBox<String> originStation = new JComboBox<String>();
		originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		originStation.setBounds(215, 516, /*190*/(frame.getWidth()/490)*190, 20);
		frame.getContentPane().add(originStation);
		
		JLabel lblStationDestiny = new JLabel("Esta\u00E7\u00E3o de Destino:");
		lblStationDestiny.setBounds(475, 491, 121, 14);
		frame.getContentPane().add(lblStationDestiny);
		
		JComboBox<String> destinyStation = new JComboBox<String>();
		destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		destinyStation.setBounds(435, 516, 190, 20);
		frame.getContentPane().add(destinyStation);
		
		JButton btnSearchMinorWay = new JButton("Tra\u00E7ar Rota");
		btnSearchMinorWay.setBounds(225, 547, 129, 23);
		btnSearchMinorWay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				/*here came the event to search the minor way and draw in the screen*/
				int originSelected = originStation.getSelectedIndex();
				int destinySelected = destinyStation.getSelectedIndex();
				IStack<String> wayFound = Controller.getInstance().wayBetween(Controller.getInstance().getStationNames()[originSelected], Controller.getInstance().getStationNames()[destinySelected]);
				while(!wayFound.isEmpty())
					System.out.println(wayFound.pop());	/*Only for test*/
			}
		});
		frame.getContentPane().add(btnSearchMinorWay);
		
		JLabel lblTempoDeEspera = new JLabel("Tempo de Espera da Esta\u00E7\u00E3o:");
		lblTempoDeEspera.setBounds(375, 551, 171, 14);
		frame.getContentPane().add(lblTempoDeEspera);
		
		txtWaitTimeInput = new JTextField();
		txtWaitTimeInput.setBounds(556, 548, 28, 20);
		frame.getContentPane().add(txtWaitTimeInput);
		txtWaitTimeInput.setColumns(10);
		
		JLabel lblminutos = new JLabel("(minutos)");
		lblminutos.setBounds(590, 551, 63, 14);
		frame.getContentPane().add(lblminutos);
		
		JLabel lblTotalTravelTime = new JLabel("Total Travel Time: ");
		lblTotalTravelTime.setBounds(679, 11, 140, 14);
		frame.getContentPane().add(lblTotalTravelTime);
		
	}
}
