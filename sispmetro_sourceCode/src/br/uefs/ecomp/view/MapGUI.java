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
import javax.swing.JPanel;
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
	public MapGUI() {	/*construtor padr�o que vai iniciar todos os componentes*/
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
		frame.getContentPane().setLayout(null);	/*define o layout como nulo para manipula��o livre da posi��o dos componentes*/
		frame.setVisible(true);	/*seta a visibilidade como verdadeiro para exibir*/
		
		frame.setBounds(100, 100, 890, 650);	/*define o tamanho da tela*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	/*define opera��o de fechamento de tela*/
		
		JLabel lblStationOrigin = new JLabel("Esta\u00E7\u00E3o de Origem:");	/*label com o texto Esta��o de Origem*/
		lblStationOrigin.setBounds(54, 495, 121, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblStationOrigin);	/*adiciona o componente na tela*/
		
		JComboBox<String> originStation = new JComboBox<String>();	/*cria um JComboBox para sele��o das esta��es*/
		originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));	/*inclui os nomes das esta��es no JComboBox*/
		originStation.setBounds(10, 520, 190, 20);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(originStation);	/*adiciona o componente na tela*/
		
		JLabel lblStationDestiny = new JLabel("Esta\u00E7\u00E3o de Destino:");	/*label com o texto Esta��o de Destino*/
		lblStationDestiny.setBounds(270, 495, 121, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblStationDestiny);	/*adiciona o componente na tela*/
		
		JComboBox<String> destinyStation = new JComboBox<String>();	/*cria um JComboBox para sele��o das esta��es*/
		destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));	/*inclui os nomes das esta��es no JComboBox*/
		destinyStation.setBounds(230, 520, 190, 20);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(destinyStation);	/*adiciona o componente na tela*/
		
		JLabel lblTempoDeEspera = new JLabel("Tempo de Espera da Esta\u00E7\u00E3o:");	/*label com o texto Tempo de Espera da Esta��o*/
		lblTempoDeEspera.setBounds(170, 555, 171, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblTempoDeEspera);	/*adiciona o componente na tela*/
		
		txtWaitTimeInput = new JTextField();	/*cria um componente para entrada de textos*/
		txtWaitTimeInput.setBounds(351, 552, 28, 20);	/*define o tamanho e posi��o do componente*/
		txtWaitTimeInput.setColumns(10);	/*define a largura do textField*/
		frame.getContentPane().add(txtWaitTimeInput);	/*adiciona o componente na tela*/
		
		JLabel lblminutos = new JLabel("(minutos)");	/*label com o texto (minutos)*/
		lblminutos.setBounds(385, 555, 63, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblminutos);	/*adiciona o componente na tela*/
		
		JLabel lblTotalTravelTime = new JLabel("Tempo Total de Viagem: ");	/*label que cont�m o tempo de viagem*/
		lblTotalTravelTime.setBounds(630, 12, 220, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblTotalTravelTime);	/*adiciona o componente na tela*/
		
		JLabel lblEstacoesNoCaminho = new JLabel("Esta\u00E7\u00F5es no Caminho:");	/*label com o texto Esta��es no Caminho:*/
		lblEstacoesNoCaminho.setBounds(629, 495, 129, 14);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(lblEstacoesNoCaminho);	/*adiciona o componente na tela*/
		
		JPanel jPanelRoute = new JPanel();	/*panel que armazenar� as esta��es pelas quais a rota passa*/
		jPanelRoute.setBounds(519, 520, 345, 80);	/*define o tamanho e posi��o do componente*/
		
		JScrollPane scrollPane = new JScrollPane(jPanelRoute);	/*scroll panel para por as esta��es da rota*/
		scrollPane.setBounds(519, 520, 345, 80);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(scrollPane);	/*adiciona o componente na tela*/
		
		/*JLabel wirteWay = new JLabel();
		scrollPane.setViewportView(wirteWay);*/
		
		MapJPanel map = new MapJPanel();
		map.setPreferredSize(new Dimension(1440, 1080));
		
		JScrollPane scrollPaneMap = new JScrollPane(map);	/*define um scroll pane para por o mapa*/
		scrollPaneMap.setBounds(10, 32, 854, 452);	/*define o tamanho e posi��o do componente*/
		frame.getContentPane().add(scrollPaneMap);	/*adiciona o componente na tela*/
		
		//JLabel lblSubwaymap = new JLabel();	/*label que ser� usada como sendo a imagem do mapa*/
		//lblSubwaymap.setIcon(Controller.getInstance().getMap());	/*seta a imagem do mapa com a carregada anteriormente pelo Controller*/
		//scrollPaneMap.setViewportView(lblSubwaymap);	/*adiciona a label do mapa no scroll pane*/
		//lblSubwaymap.setIcon(new ImageIcon(Controller.getInstance().getMap().getImage().getScaledInstance(scrollPane.getWidth(),scrollPane.getHeight(), Image.SCALE_DEFAULT)));
		
		originStation.removeItemAt(1);	/*remove o item selecionado no segundo JComboBox*/
		destinyStation.removeItemAt(0);	/*remove o item selecionado no primeiro JComboBox*/
		
		destinyStation.addActionListener(new ActionListener() {	/*a��o de mudan�a do conteudo da JComboBox*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedOther = originStation.getSelectedItem();
				originStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
				originStation.removeItem(destinyStation.getSelectedItem());
				originStation.setSelectedItem(selectedOther);
			}
			});
		
		originStation.addActionListener(new ActionListener() {	/*a��o de mudan�a do conteudo da JComboBox*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedOther = destinyStation.getSelectedItem();
				destinyStation.setModel(new DefaultComboBoxModel<String>(Controller.getInstance().getStationNames()));
				destinyStation.removeItem(originStation.getSelectedItem());
				destinyStation.setSelectedItem(selectedOther);
			}
			});
	
		JButton btnSearchMinorWay = new JButton("Tra\u00E7ar Rota");	/*bot�o para definir o evento de tra�ar rota e definir tempo de viagem*/
		btnSearchMinorWay.setBounds(20, 551, 129, 23);	/*define o tamanho e posi��o do componente*/
		btnSearchMinorWay.addActionListener(new ActionListener() {	/*adiciona a a��o que ser� executada*/
			
			@Override
			public void actionPerformed(ActionEvent event) {
				/*here came the event to search the minor way and draw in the screen*/
				IStack<String> wayFound = Controller.getInstance().wayBetween((String) originStation.getSelectedItem(), (String) destinyStation.getSelectedItem());
				try {
					float timeOfWay = Controller.getInstance().totalTime(((Stack<String>) wayFound).copy(), Float.parseFloat(txtWaitTimeInput.getText()));
					lblTotalTravelTime.setText("Tempo Total de Viagem: " + timeOfWay + " minutos");
					
					map.setDrawMinorWay(true);
					map.setWayMinor(wayFound);
					map.repaint();
					
					/*int initialPosition = 10;
					jPanelRoute.removeAll();
					while(!wayFound.isEmpty()) {
						JLabel additting = new JLabel(wayFound.pop());
						additting.setBounds(10, initialPosition, 300, 20);
						jPanelRoute.add(additting);
						initialPosition += 20;
					}*/
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(new JPopupMenu(),"Insira um valor para tempo de espera!","ATEN��O", JOptionPane.INFORMATION_MESSAGE);
					lblTotalTravelTime.setText("Tempo Total de Viagem: ");
					txtWaitTimeInput.setText("");
				}
			}
		});
		
		frame.getContentPane().add(btnSearchMinorWay);	/*adiciona o componente na tela*/
		
	}
}
