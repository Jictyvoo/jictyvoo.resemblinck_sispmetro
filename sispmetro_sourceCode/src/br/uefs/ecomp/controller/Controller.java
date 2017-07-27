package br.uefs.ecomp.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.uefs.ecomp.util.Graph;

public class Controller {
	
	private static Controller controllerInstance;
	private Graph stations;
	
	private Controller() {
		this.stations = new Graph();
	}
	
	public static Controller getInstance() {
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}
	
	public void parseFile(String fileNamePath) throws FileNotFoundException {
		this.parseFile(fileNamePath, this.stations);
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

				saveVertexEdgesGraph.addEdge(edgesInformations[0], edgesInformations[1], Integer.parseInt(edgesInformations[2]));	/*adiciona a aresta no grafo*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getStationNames() {
		return this.stations.getAllData();
	}
}
