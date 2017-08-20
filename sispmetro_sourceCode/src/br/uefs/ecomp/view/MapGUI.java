package br.uefs.ecomp.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
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
	public MapGUI() {	/*construtor padrão que vai iniciar todos os componentes*/
		initialize();
	}
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();	/*inicializa o frame principal*/
		frame.getContentPane().setLayout(null);	/*define o layout como nulo para manipulação livre da posição dos componentes*/
		frame.setVisible(true);	/*seta a visibilidade como verdadeiro para exibir*/
		
		frame.setBounds(100, 100, 890, 650);	/*define o tamanho da tela*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	/*define operação de fechamento de tela*/
		int framePosition = ((frame.getWidth()/2) - 250);
		
		JLabel lblStationOrigin = new JLabel("Esta\u00E7\u00E3o de Origem:");	/*label com o texto Estação de Origem*/
		lblStationOrigin.setBounds(framePosition + 54, 495, 121, 14);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(lblStationOrigin);	/*adiciona o componente na tela*/
		
		JComboBox<String> originStation = new JComboBox<String>();	/*cria um JComboBox para seleção das estações*/
		originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));	/*inclui os nomes das estações no JComboBox*/
		originStation.setBounds(framePosition + 10, 520, 190, 20);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(originStation);	/*adiciona o componente na tela*/
		
		JLabel lblStationDestiny = new JLabel("Esta\u00E7\u00E3o de Destino:");	/*label com o texto Estação de Destino*/
		lblStationDestiny.setBounds(framePosition + 270, 495, 121, 14);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(lblStationDestiny);	/*adiciona o componente na tela*/
		
		JComboBox<String> destinyStation = new JComboBox<String>();	/*cria um JComboBox para seleção das estações*/
		destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));	/*inclui os nomes das estações no JComboBox*/
		destinyStation.setBounds(framePosition + 230, 520, 190, 20);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(destinyStation);	/*adiciona o componente na tela*/
		
		JLabel lblTempoDeEspera = new JLabel("Tempo de Espera da Esta\u00E7\u00E3o:");	/*label com o texto Tempo de Espera da Estação*/
		lblTempoDeEspera.setBounds(framePosition + 170, 555, 171, 14);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(lblTempoDeEspera);	/*adiciona o componente na tela*/
		
		txtWaitTimeInput = new JTextField();	/*cria um componente para entrada de textos*/
		txtWaitTimeInput.setBounds(framePosition + 351, 552, 28, 20);	/*define o tamanho e posição do componente*/
		txtWaitTimeInput.setColumns(10);	/*define a largura do textField*/
		frame.getContentPane().add(txtWaitTimeInput);	/*adiciona o componente na tela*/
		
		JLabel lblminutos = new JLabel("(minutos)");	/*label com o texto (minutos)*/
		lblminutos.setBounds(framePosition + 385, 555, 63, 14);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(lblminutos);	/*adiciona o componente na tela*/
		
		JLabel lblTotalTravelTime = new JLabel("Tempo Total de Viagem: ");	/*label que contém o tempo de viagem*/
		lblTotalTravelTime.setBounds(630, 12, 220, 14);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(lblTotalTravelTime);	/*adiciona o componente na tela*/
		
		MapJPanel map = new MapJPanel(); /*define um MapJPanel que herda de JPanel. Contém a imagem do mapa e permite realizar o desenho*/
		map.setPreferredSize(new Dimension(1440, 1080)); /*define as dimensões do MapJPanel*/
		
		JScrollPane scrollPaneMap = new JScrollPane(map);	/*define um scroll pane para por o mapa*/
		scrollPaneMap.setBounds(10, 32, 854, 452);	/*define o tamanho e posição do componente*/
		frame.getContentPane().add(scrollPaneMap);	/*adiciona o componente na tela*/
		
		originStation.removeItemAt(1);	/*remove o item selecionado no segundo JComboBox*/
		destinyStation.removeItemAt(0);	/*remove o item selecionado no primeiro JComboBox*/
		
		destinyStation.addActionListener(new ActionListener() {	/*ação de mudança do conteudo da JComboBox*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedOther = originStation.getSelectedItem();
				originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
				originStation.removeItem(destinyStation.getSelectedItem());
				originStation.setSelectedItem(selectedOther);
			}
			});
		
		originStation.addActionListener(new ActionListener() {	/*ação de mudança do conteudo da JComboBox*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedOther = destinyStation.getSelectedItem();
				destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
				destinyStation.removeItem(originStation.getSelectedItem());
				destinyStation.setSelectedItem(selectedOther);
			}
			});
	
		JButton btnSearchMinorWay = new JButton("Tra\u00E7ar Rota");	/*botão para definir o evento de traçar rota e definir tempo de viagem*/
		btnSearchMinorWay.setBounds(framePosition + 20, 551, 129, 23);	/*define o tamanho e posição do componente*/
		btnSearchMinorWay.addActionListener(new ActionListener() {	/*adiciona a ação que será executada*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				/*here came the event to search the minor way and draw in the screen*/
				IStack<String> wayFound = Controller.getInstance().wayBetween((String) originStation.getSelectedItem(), (String) destinyStation.getSelectedItem());
				try {
					float timeOfWay = Controller.getInstance().totalTime(((Stack<String>) wayFound).copy(), Float.parseFloat(txtWaitTimeInput.getText()));
					lblTotalTravelTime.setText("Tempo Total de Viagem: " + timeOfWay + " minutos");
					
					map.setDrawMinorWay(true); /*indica quando o menor caminho deve ser desenhado*/
					map.setWayMinor(wayFound); /*passa uma pilha contendo o menor caminho*/
					map.repaint(); /*pinta novamente o componente, agora com o menor caminho*/
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(new JPopupMenu(),"Insira um valor para tempo de espera!","ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
					lblTotalTravelTime.setText("Tempo Total de Viagem: ");
					txtWaitTimeInput.setText("");
				}
			}
		});
		
		frame.getContentPane().add(btnSearchMinorWay);	/*adiciona o componente na tela*/

	}
}
