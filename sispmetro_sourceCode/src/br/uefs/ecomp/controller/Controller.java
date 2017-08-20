package br.uefs.ecomp.controller;

import java.awt.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

import br.uefs.ecomp.util.Graph;
import br.uefs.ecomp.util.IStack;
import br.uefs.ecomp.util.Vertex;

/**
 * Classe que controla o sistema.
 * @author Joao Victor & Resemblinck Freitas
 */
public class Controller {
	
	private static Controller controllerInstance;
	private Graph stations;
	private String[] stationsNames;
	private Hashtable<String, Point> points;
	
	/**
	 * Construtor privado do Controller para Singletone.
	 */
	private Controller() {
		this.stations = new Graph();
		this.stationsNames = null;
		//this.points = new LinearProbingHashTable<String, Point>();
		this.points = new Hashtable<String, Point>();
	}
	
	/**
	 * M�todo est�tico para adquirir o objeto Singletone.
	 * @return Um objeto do tipo Controller.
	 */
	public static Controller getInstance() {
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}
	
	/**
	 * M�todo auxiliar para leitura do arquivo e que salva o nome dos v�rtices em um vetor de strings. 
	 * @param fileNamePath String - Nome ou diret�rio do arquivo 
	 * @throws FileNotFoundException
	 */
	public void parseFile(String fileNamePath) throws FileNotFoundException {
		this.parseFile(fileNamePath, this.stations);
		this.stationsNames = this.saveStationNames();	/*salva o nome do arquivo na primeira vez para n�o pesquisar os nomes novamente*/
	}
	
	/**
	 * M�todo que realiza a leitura do arquivo transformando os dados lidos em v�rtices (Esta��es)
	 * e adicionando no Grafo.
	 * 
	 * @param fileNamePath String - Nome ou diret�rio do arquivo.
	 * @param saveVertexEdgesGraph Graph - Grafo onde os v�rtices (Esta��es) ser�o salvos.
	 * @throws FileNotFoundException
	 */
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
	
	/**
	 * M�todo para carregamento dos dados das coordenadas dos v�rtices e salva em uma Hashtable, 
	 * para possibilitar o desenho na tela.
	 */
	public void getCoordinates() {
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
				this.points.put(linesInformations[0], new Point((int) Float.parseFloat(linesInformations[1]), 
						(int) Float.parseFloat(linesInformations[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo que retorna uma Hashtable contendo as coordenadas de cada esta��o do mapa.
	 * @return Hashtable de coordenadas.
	 */
	public Hashtable<String, Point> getPoints(){
		return this.points;
	}
	
	/**
	 * M�todo que retorna os nomes de todas as esta��es, armazenado anteriormente no momento
	 * da leitura do arquivo.
	 * @return String[] - Vetor de Strings contendo os nomes.
	 */
	public String[] getStationNames() {
		return this.stationsNames;
	}
	
	/**
	 * M�todo privado que armazena o nome das esta��es no vetor de Strings a ser retornado.
	 * @return
	 */
	private String[] saveStationNames() {
		String[] nameStations = this.stations.getAllData();
		Arrays.sort(nameStations);
		return nameStations;
	}
	
	/**
	 * M�todo que solicita ao Grafo pelo caminho m�nimo entre dois v�rtices (Esta��es) e retorna
	 * uma pilha contendo esse caminho.
	 * @param origin String - Nome da esta��o de origem.
	 * @param destiny String - Nome da esta��o de destino.
	 * @return IStack - Pilha contendo o menor caminho entre os dois v�rtices.
	 */
	public IStack<String> wayBetween(String origin, String destiny) {
		return this.stations.minorWay(origin, destiny);
	}
	
	/**
	 * M�todo que calcula o tempo total de percursso a partir de uma pilha contendo o menor caminho
	 * entre duas esta��es.
	 * 
	 * @param wayFound IStack - Pilha contendo menor caminho.
	 * @param waitingTime float - Tempo que o m�tro fica parado nas esta��es.
	 * @return float - Tempo total do percurso.
	 */
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
