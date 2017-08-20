package br.uefs.ecomp.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Hashtable;

import javax.swing.JPanel;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.util.Edge;
import br.uefs.ecomp.util.IStack;
import br.uefs.ecomp.util.Vertex;

/**
 * Classe que herda de JPanel e sobrescreve o m�todo paintComponent(),
 * permitindo gravar a imagem no painel e desenhar em cima da mesma.
 * 
 * @author Jo�o Victor Oliveira Couto & Resemblinck Freitas
 *
 */
public class MapJPanel extends JPanel {

	private boolean drawMinorWay = false; // Indica quando o menor caminho deve ser pintado.
	private String[] wayMinor; // Nomes das esta��es do menor caminho a ser pintado.
	private static final long serialVersionUID = -1999969049240617025L;

	/**
	 * M�todo que altera o estado da vari�vel drawMinorWay, para indicar o momento
	 * certo que o menor caminho � requisitado.
	 */
	public void setDrawMinorWay(boolean b) {
		this.drawMinorWay = b;
	}

	/**
	 * M�todo que recebe uma pilha contendo o menor caminho entre duas esta��es e
	 * guarda o nome das esta��es em um vetor de Strings.
	 * 
	 * @param wayFound
	 *            IStack - Pilha contendo o menor caminho.
	 */
	public void setWayMinor(IStack<String> wayFound) {
		this.wayMinor = new String[wayFound.size()];
		int i = 0;
		while (!wayFound.isEmpty()) {
			wayMinor[i++] = wayFound.pop();
		}
	}

	/**
	 * M�todo que desenha todas as linhas do mapa, ligando todas as esta��es
	 * adequadamente.
	 * 
	 * @param g
	 *            Graphics2D - Componente que desenha as linhas na imagem.
	 */
	private void drawLines(Graphics2D g) {
		BasicStroke dashed = new BasicStroke((float) 6); // Objeto para engrossar as linhas que ser�o desenhadas.
		g.setStroke(dashed); // Engrossa as linhas
		g.setColor(Color.gray); // Define a cor como cinza
		Vertex[] allVertex = Controller.getInstance().getAllVertex(); // Pega todos os v�rtices do grafo.
		Hashtable<String, Point> points = Controller.getInstance().getPoints(); // Pega todas as coordenadas do mapa.

		for (int i = 0; i < allVertex.length; i++) {

			Point p1 = points.get(allVertex[i].getVertexName()); // Pega as coordenadas de determinado v�rtice.
			g.fillOval(p1.x, p1.y, 12, 12); // Desenha um ponto nessa coordenada
			Edge[] edges = allVertex[i].getEdges(); // Verifica as liga��es que este v�rtice tem

			for (int j = 0; j < edges.length; j++) { // Desenha as liga��es entre os v�rtice adjacentes

				if (edges[j] != null && edges[j].getVertex() != null) {
					Point p2 = points.get(edges[j].getVertex().getVertexName()); // Pega o v�rtice adjacente
					g.drawLine(p1.x + 6, p1.y + 6, p2.x + 6, p2.y + 6); // Liga os v�rtices
				}

			}

		}
	}

	/**
	 * M�todo que desenha o menor caminho entre duas esta��es
	 * 
	 * @param g
	 *            Graphics - Componente que desenha as linhas na imagem.
	 */
	private void drawMinorWay(Graphics g) {

		Hashtable<String, Point> points = Controller.getInstance().getPoints(); // Pega todas as coordenadas das
																				// esta��es no mapa
		BasicStroke dashed = new BasicStroke((float) 6); // Objeto para engrossar as linhas que ser�o desenhadas.
		((Graphics2D) g).setStroke(dashed); // Engrossa as linhas
		g.setColor(Color.RED); // Define a cor como vermelho
		int i = 0;
		for (i = 0; i < this.wayMinor.length - 1; i++) {

			Point p1 = points.get(this.wayMinor[i]); // Pega as coordenadas de uma esta��o
			g.fillOval(p1.x, p1.y, 12, 12); // Desenha um ponto
			Point p2 = points.get(this.wayMinor[i + 1]); // Pega as coordenadas de outra esta��o
			g.fillOval(p2.x, p2.y, 12, 12); // Desenha um ponto
			g.drawLine(p1.x + 6, p1.y + 6, p2.x + 6, p2.y + 6); // Liga as duas esta��es
		}

	}

	/**
	 * Sobrescrita do m�todo paintComponent.
	 */
	@Override
	public void paintComponent(Graphics g) {
		Toolkit tkit = Toolkit.getDefaultToolkit(); // Objeto para capturar a imagem
		Image img = tkit.getImage("initialize/subwayNamesMap.jpeg"); // Objeto para gravar a imagem

		g.drawImage(img, 0, 0, this); // Grava a imagem para desenhar por cima

		drawLines((Graphics2D) g); // Desenha as liga��es entre as esta��es

		if (drawMinorWay) { // Verifica se deve desenhar o menor caminho
			drawMinorWay(g); // Desenha o menor caminho
		}

		((Graphics2D) g).dispose(); // Destr�i o objeto
	}

}
