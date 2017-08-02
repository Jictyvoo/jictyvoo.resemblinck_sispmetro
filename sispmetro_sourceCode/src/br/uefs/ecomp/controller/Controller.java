package br.uefs.ecomp.controller;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;

import br.uefs.ecomp.util.Graph;
import br.uefs.ecomp.util.IStack;
import br.uefs.ecomp.util.LinearProbingHashTable;

public class Controller {
	
	private static Controller controllerInstance;
	private Graph stations;
	private String[] stationsNames;
	
	private ImageIcon map;
	private LinearProbingHashTable<String, Point> points;
	
	private Controller() {
		this.stations = new Graph();
		this.stationsNames = null;
		//this.points = new LinearProbingHashTable<String, Point>();
		this.map = null;
	}
	
	public static Controller getInstance() {
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}
	
	public void parseFile(String fileNamePath) throws FileNotFoundException {
		this.parseFile(fileNamePath, this.stations);
		this.stationsNames = this.saveStationNames();
	}
	
	public void parseFile(String fileNamePath, Graph saveVertexEdgesGraph) throws FileNotFoundException {
		FileReader openingFile = new FileReader(fileNamePath);
		BufferedReader informationReaded = new BufferedReader(openingFile);
		
		String lineReaded = null;
		try {
			while ((lineReaded = informationReaded.readLine()) != null) {	/*Lê cada linha do arquivo até chegar no final*/
				if(lineReaded.charAt(0) == '#')
					continue;
				String[] edgesInformations = lineReaded.split(",\\s|,");
				saveVertexEdgesGraph.addVertex(edgesInformations[0]);	/*adiciona os vertices no grafo*/
				saveVertexEdgesGraph.addVertex(edgesInformations[1]);	/*adiciona os vertices no grafo*/
				try {
					saveVertexEdgesGraph.addEdge(edgesInformations[0], edgesInformations[1], Integer.parseInt(edgesInformations[2]));	/*adiciona a aresta no grafo*/
				} catch (NumberFormatException exception) {
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
	
	public void openMap() {
		this.map = new ImageIcon("../SubwayMap/subwayMapSimplified.png");
		/*Function to read the input coordinates file*/
	}
	
	public ImageIcon getMap() {
		return this.map;
	}
	
	public LinearProbingHashTable<String, Point> getPoints(){
		return this.points;
	}
	
	public String[] getStationNames() {
		return this.stationsNames;
	}
	
	private String[] saveStationNames() {
		String[] nameStations = this.stations.getAllData();
		Arrays.sort(nameStations);
		return nameStations;
	}
	
	public IStack<String> wayBetween(String origin, String destiny) {
		return this.stations.minorWay(origin, destiny);
	}
	
	public float totalTime(IStack<String> wayFound, float waitingTime) {
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
}
