package br.uefs.ecomp.view;

import java.awt.EventQueue;
import java.io.FileNotFoundException;

import br.uefs.ecomp.controller.Controller;

public class Program {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Controller.getInstance().parseFile("../estacoesMetro.spsubsta");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapGUI window = new MapGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
