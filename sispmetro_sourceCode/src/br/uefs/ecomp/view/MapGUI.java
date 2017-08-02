package br.uefs.ecomp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.util.IStack;
import br.uefs.ecomp.util.Stack;

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
		
		JLabel lblStationOrigin = new JLabel("Esta\u00E7\u00E3o de Origem:");
		lblStationOrigin.setBounds(54, 495, 121, 14);
		frame.getContentPane().add(lblStationOrigin);
		
		JComboBox<String> originStation = new JComboBox<String>();
		originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		originStation.setBounds(10, 520, /*190*/(frame.getWidth()/490)*190, 20);
		frame.getContentPane().add(originStation);
		
		JLabel lblStationDestiny = new JLabel("Esta\u00E7\u00E3o de Destino:");
		lblStationDestiny.setBounds(270, 495, 121, 14);
		frame.getContentPane().add(lblStationDestiny);
		
		JComboBox<String> destinyStation = new JComboBox<String>();
		destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
		destinyStation.setBounds(230, 520, 190, 20);
		frame.getContentPane().add(destinyStation);
		
		JLabel lblTempoDeEspera = new JLabel("Tempo de Espera da Esta\u00E7\u00E3o:");
		lblTempoDeEspera.setBounds(170, 555, 171, 14);
		frame.getContentPane().add(lblTempoDeEspera);
		
		txtWaitTimeInput = new JTextField();
		txtWaitTimeInput.setBounds(351, 552, 28, 20);
		frame.getContentPane().add(txtWaitTimeInput);
		txtWaitTimeInput.setColumns(10);
		
		JLabel lblminutos = new JLabel("(minutos)");
		lblminutos.setBounds(385, 555, 63, 14);
		frame.getContentPane().add(lblminutos);
		
		JLabel lblTotalTravelTime = new JLabel("Total Travel Time: ");
		lblTotalTravelTime.setBounds(630, 11, 200, 14);
		frame.getContentPane().add(lblTotalTravelTime);
		
		JLabel lblEstacoesNoCaminho = new JLabel("Esta\u00E7\u00F5es no Caminho:");
		lblEstacoesNoCaminho.setBounds(629, 495, 129, 14);
		frame.getContentPane().add(lblEstacoesNoCaminho);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 520, 345, 80);
		frame.getContentPane().add(scrollPane);
		
		JLabel wirteWay = new JLabel();
		scrollPane.setViewportView(wirteWay);
		
		JButton btnSearchMinorWay = new JButton("Tra\u00E7ar Rota");
		btnSearchMinorWay.setBounds(20, 551, 129, 23);
		btnSearchMinorWay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				/*here came the event to search the minor way and draw in the screen*/
				int originSelected = originStation.getSelectedIndex();
				int destinySelected = destinyStation.getSelectedIndex();
				IStack<String> wayFound = Controller.getInstance().wayBetween(Controller.getInstance().getStationNames()[originSelected], Controller.getInstance().getStationNames()[destinySelected]);
				try {
					float timeOfWay = Controller.getInstance().totalTime(((Stack<String>) wayFound).copy(), Float.parseFloat(txtWaitTimeInput.getText()));
					lblTotalTravelTime.setText("Total Travel Time: " + timeOfWay + " minutes");
				
					String print = "";
					while(!wayFound.isEmpty())
						print += wayFound.pop() + "\n";	/*Organize the text*/
					wirteWay.setText(print);
				}
				catch (NumberFormatException exception) {
					wirteWay.setText("Insira um valor para tempo de espera!");
					lblTotalTravelTime.setText("Total Travel Time: ");
					txtWaitTimeInput.setText("");
				}
			}
		});
		
		frame.getContentPane().add(btnSearchMinorWay);
		
		JScrollPane scrollPaneMap = new JScrollPane();
		scrollPaneMap.setBounds(10, 32, 854, 452);
		frame.getContentPane().add(scrollPaneMap);
		
		JLabel lblSubwaymap = new JLabel();
		scrollPaneMap.setViewportView(lblSubwaymap);
		lblSubwaymap.setIcon(Controller.getInstance().getMap());
		//lblSubwaymap.setIcon(new ImageIcon(Controller.getInstance().getMap().getImage().getScaledInstance(scrollPane.getWidth(),scrollPane.getHeight(), Image.SCALE_DEFAULT)));
		
	}
}
