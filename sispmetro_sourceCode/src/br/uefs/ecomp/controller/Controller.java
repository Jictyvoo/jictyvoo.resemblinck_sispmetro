package br.uefs.ecomp.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import br.uefs.ecomp.util.Graph;
import br.uefs.ecomp.util.IStack;
import br.uefs.ecomp.util.Vertex;

public class Controller {
	
	private static Controller controllerInstance;
	private Graph stations;
	private String[] stationsNames;
	
	private ImageIcon map;
	private Hashtable<String, Point> points;
	
	private Controller() {	/*construtor privado do Controller para Singletone*/
		this.stations = new Graph();
		this.stationsNames = null;
		//this.points = new LinearProbingHashTable<String, Point>();
		this.points = new Hashtable<String, Point>();
		this.map = null;
	}
	
	public static Controller getInstance() {	/*M�todo est�tico para adquirir o objeto Singletone*/
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}
	
	public void parseFile(String fileNamePath) throws FileNotFoundException {
		this.parseFile(fileNamePath, this.stations);
		this.stationsNames = this.saveStationNames();	/*salva o nome do arquivo na primeira vez para n�o pesquisar os nomes novamente*/
	}
	
	public void parseFile(String fileNamePath, Graph saveVertexEdgesGraph) throws FileNotFoundException {
		FileReader openingFile = new FileReader(fileNamePath);
		BufferedReader informationReaded = new BufferedReader(openingFile);
		
		String lineReaded = null;
		try {
			while ((lineReaded = informationReaded.readLine()) != null) {	/*L� cada linha do arquivo at� chegar no final*/
				if(lineReaded.charAt(0) == '#')
					continue;
				String[] edgesInformations = lineReaded.split(",\\s|,");
				saveVertexEdgesGraph.addVertex(edgesInformations[0]);	/*adiciona os vertices no grafo*/
				saveVertexEdgesGraph.addVertex(edgesInformations[1]);	/*adiciona os vertices no grafo*/
				try {
					saveVertexEdgesGraph.addEdge(edgesInformations[0], edgesInformations[1], Integer.parseInt(edgesInformations[2]));	/*adiciona a aresta no grafo*/
				} catch (NumberFormatException exception) {	/*caso capture a exce��o, converte a string para float manualmente*/
					float num = 0;
					int decimals = 1;
					boolean decimal = false;
					for(int position = 0 ; position < edgesInformations[2].length(); position += 1) {
						if(edgesInformations[2].charAt(position) == '.')
							decimal = true;
						else if(edgesInformations[2].charAt(position) >= '0' && edgesInformations[2].charAt(position) <= '9') {
							if(!decimal)
								num *= 10;
							num += Integer.parseInt("" + edgesInformations[2].charAt(position)) / (decimal ? Math.pow(10, decimals++) : 1);
						}
					}
					saveVertexEdgesGraph.addEdge(edgesInformations[0], edgesInformations[1], num);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openMap() {	/*m�todo para carregamento da imagem do mapa, e dados das coordenadas*/
		this.map = new ImageIcon("initialize/subwayNamesMap.jpeg");
		/*Function to read the input coordinates file*/
		FileReader openingFile = null;
		try {
			openingFile = new FileReader("initialize/coordinateOfStations.csv");
		} catch (FileNotFoundException notFoundException) {
			notFoundException.printStackTrace();
		}
		BufferedReader informationReaded = new BufferedReader(openingFile);
		
		String lineReaded = null;
		try {
			while ((lineReaded = informationReaded.readLine()) != null) {	/*L� cada linha do arquivo at� chegar no final*/
				String[] linesInformations = lineReaded.split(";|;\\s");
				this.points.put(linesInformations[0], new Point((int) Float.parseFloat(linesInformations[1]), (int) Float.parseFloat(linesInformations[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getMap() {	/*retorna o mapa armazenado*/
		return this.map;
	}
	
	public Hashtable<String, Point> getPoints(){	/*retorna a hash de pontos*/
		return this.points;
	}
	
	public String[] getStationNames() {	/*retorna os nomes j� armazenados do arquivo de texto*/
		return this.stationsNames;
	}
	
	private String[] saveStationNames() {	/*pega todos os v�rtices e armazena o nome das esta��es*/
		String[] nameStations = this.stations.getAllData();
		Arrays.sort(nameStations);
		return nameStations;
	}
	
	public IStack<String> wayBetween(String origin, String destiny) {	/*retorna a pilha com o caminho m�nimo*/
		return this.stations.minorWay(origin, destiny);
	}
	
	public float totalTime(IStack<String> wayFound, float waitingTime) {	/*calcula o tempo total a partir de uma pilha contendo o caminho*/
		int stops = wayFound.size();
		String station = wayFound.pop();
		float timeFound = 0f;
		while(!wayFound.isEmpty()) {
			String temporary = station;
			station = wayFound.pop();
			timeFound += this.stations.getEdge(temporary, station);
		}
		timeFound = (waitingTime * (stops - 2)) + timeFound;
		return timeFound;
	}
	
	/**
	 * M�todo que retorna um vetor com todos os v�rtices do grafo
	 * @return Vetor com todos os v�rtices do grafo
	 */
	public Vertex[] getAllVertex() {
		return this.stations.getAllVertex();
	}
}
