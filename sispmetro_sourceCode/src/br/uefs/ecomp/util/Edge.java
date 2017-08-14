package br.uefs.ecomp.util;

/**
 * Classe Edge que cont�m um v�rtice de destino e o peso da aresta
 * @author jvict
 */
public class Edge {
	private Vertex vertex;
	private float weight;
	
	/**
	 * Construtor da Aresta
	 * @param newVertex	V�rtice de Destino
	 * @param edgeWeight	Peso da Aresta
	 */
	public Edge(Vertex newVertex, float edgeWeight){
		this.vertex = newVertex;
		this.weight = edgeWeight;
	}
	
	/**
	 * M�todo que retorna o v�rtice de Destino
	 * @return	V�rtice de destino da aresta
	 */
	public Vertex getVertex() {
		return this.vertex;
	}
	
	/**
	 * M�todo que define um v�rtice de destino para a aresta
	 * @param vertex	Novo v�rtice de destino da aresta
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	
	/**
	 * M�todo que retorna o peso da aresta
	 * @return	Peso da Aresta
	 */
	public float getWeight() {
		return weight;
	}
	
	/**
	 * M�todo que define um novo valor para o peso da aresta
	 * @param weight	Novo valor do peso da aresta
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
}
