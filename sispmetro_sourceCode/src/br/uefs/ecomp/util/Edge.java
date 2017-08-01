package br.uefs.ecomp.util;

/**
 * Classe Edge que contém um vértice de destino e o peso da aresta
 * @author jvict
 */
class Edge {
	private Vertex vertex;
	private float weight;
	
	/**
	 * Construtor da Aresta
	 * @param newVertex	Vértice de Destino
	 * @param edgeWeight	Peso da Aresta
	 */
	public Edge(Vertex newVertex, float edgeWeight){
		this.vertex = newVertex;
		this.weight = edgeWeight;
	}
	
	/**
	 * Método que retorna o vértice de Destino
	 * @return	Vértice de destino da aresta
	 */
	public Vertex getVertex() {
		return vertex;
	}
	
	/**
	 * Método que define um vértice de destino para a aresta
	 * @param vertex	Novo vértice de destino da aresta
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	
	/**
	 * Método que retorna o peso da aresta
	 * @return	Peso da Aresta
	 */
	public float getWeight() {
		return weight;
	}
	
	/**
	 * Método que define um novo valor para o peso da aresta
	 * @param weight	Novo valor do peso da aresta
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
}
