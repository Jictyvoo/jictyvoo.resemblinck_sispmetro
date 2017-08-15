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

public class MapJPanel extends JPanel{
	
	private boolean drawMinorWay = false;
	private String[] wayMinor;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1999969049240617025L;
	
	public void setDrawMinorWay(boolean b) {
		this.drawMinorWay = b;
	}
	
	public void setWayMinor(IStack<String> wayFound) {
		this.wayMinor = new String[wayFound.size()];
		int i = 0;
		while(!wayFound.isEmpty()) {
			wayMinor[i++] = wayFound.pop();
		}
	}

	private void drawLines(Graphics2D g) {
		
		BasicStroke dashed = new BasicStroke ((float) 6);
		g.setStroke(dashed);
		g.setColor(Color.gray);
		Vertex[] allVertex = Controller.getInstance().getAllVertex();
		Hashtable<String, Point> points = Controller.getInstance().getPoints();
		
		for (int i = 0; i < allVertex.length; i++) {
			
			Point p1 = points.get(allVertex[i].getVertexName());
			g.fillOval(p1.x, p1.y, 12, 12);
			Edge[] edges = allVertex[i].getEdges();
			
			for (int j = 0; j < edges.length; j++) {
				
				if(edges[j] != null && edges[j].getVertex() != null) {
				Point p2 = points.get(edges[j].getVertex().getVertexName());
				g.drawLine(p1.x + 6, p1.y + 6, p2.x + 6, p2.y + 6);
				}
				
			}
			
		}
	}
	
	private void drawMinorWay(Graphics g) {
		
		Hashtable<String, Point> points = Controller.getInstance().getPoints();
		BasicStroke dashed = new BasicStroke ((float) 6);
		((Graphics2D) g).setStroke(dashed);
		g.setColor(Color.RED);

		for (int i = 0; i < this.wayMinor.length - 1; i++) {
			
			Point p1 = points.get(this.wayMinor[i]);
			g.fillOval(p1.x, p1.y, 12, 12);
			Point p2 = points.get(this.wayMinor[i+1]);
			g.fillOval(p2.x, p2.y, 12, 12);
			g.drawLine(p1.x + 6, p1.y + 6, p2.x + 6, p2.y + 6);
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		Image img = tkit.getImage("initialize/subwayNamesMap.jpeg");
		
		g.drawImage(img, 0, 0, this);
		
		drawLines((Graphics2D)g);
		
		if(drawMinorWay) {
			drawMinorWay(g);
		}
		
		((Graphics2D)g).dispose();
	
	}

}
