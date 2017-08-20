package br.uefs.ecomp.util;

/**
 * Classe vértice que armazena dados e um vetor de arestas adjacentes ao vértice
<<<<<<< HEAD
 * 
 * @author JoÃ£o Victor Oliveira Couto
=======
 * @author JoÃ£o Victor Oliveira Couto & Resemblinck Freitas
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
 */
public class Vertex {
	private Edge[] edges; /* Arestas do grafo */
	private final String vertexName; /* nome dos vertices do grafo */
	private int next;
<<<<<<< HEAD

	public Vertex(String nameOfVertex) { /* construtor do vertice */
=======
	
	/**
	 * Construtor do vertice.
	 * @param nameOfVertex
	 */
	public Vertex(String nameOfVertex){	
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
		this.edges = new Edge[1];
		this.vertexName = nameOfVertex;
		this.next = 0;
	}
<<<<<<< HEAD

	private void expandVectors() { /* funcao para expandir o tamanho do vetor de arestas */
=======
	
	/**
	 * Método privado para expandir o tamanho do array de arestas contido dentro do vértice.
	 */
	private void expandVectors(){
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
		Edge[] temporaryV = new Edge[this.edges.length + 1];
		for (int position = 0; position < this.edges.length; position += 1)
			temporaryV[position] = this.edges[position];

		this.edges = temporaryV;
	}
<<<<<<< HEAD

	public void addEdge(Vertex newLigation, float ligationCost) {
=======
	
	/**
	 * Método que adiciona um vértice adjacente e uma aresta de ligação.
	 * @param newLigation Vertex - novo vértice adjacente ao vertice atual.
	 * @param ligationCost float - Peso da aresta de ligação.
	 */
	public void addEdge(Vertex newLigation, float ligationCost){
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
		int newEdgePosition = this.next;
		for (int position = 0; position < this.next; position += 1) {
			Edge search = this.edges[position];
			if (search.getVertex().equals(newLigation)) { /* verifica se já foi adicionado aquele vértice */
				newEdgePosition = position;
				position = this.next + 1;
			}
		}
		if (this.next == this.edges.length)
			this.expandVectors(); /* caso primeira adição, expande o vetor */

		this.edges[newEdgePosition] = new Edge(newLigation, ligationCost); /* adiciona a aresta */
		if (newEdgePosition == this.next)
			this.next += 1;
	}
<<<<<<< HEAD

=======
	
	/**
	 * Método que remove um vértice adjacente e sua aresta de ligação.
	 * @param edgeThis Vertex - Vértice adjacente que será removido.
	 * @return Boolean - True se remover, False caso contrário.
	 */
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
	public boolean removeEdge(Vertex edgeThis) {
		Edge foundEdge = null;
		int position = 0;
		for (position = 0; position < this.vertexDegree(); position += 1) {
			Edge search = this.edges[position];
			if (search.getVertex().equals(edgeThis)) { /* verifica se o vertice existe no conjunto de arestas */
				foundEdge = search;
				break;
			}
		}
		if (foundEdge == null)
			return false;
		this.edges[position].setVertex(null); /* limpa referencia para o garbage collector */
		this.edges[position] = this.edges[this.next - 1];
		this.next -= 1;

		return true;
	}
<<<<<<< HEAD

=======
	
	/**
	 * Método que remove todos os vértices adjacentes e as arestas de ligação.
	 */
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
	public void removeAllEdges() {
		while (this.next > 0) {
			this.edges[0].getVertex().removeEdge(this);
			this.removeEdge(this.edges[0].getVertex());
		}
	}
<<<<<<< HEAD

=======
	
	/**
	 * Método que retorna o peso de uma aresta de ligação entre o vértice atual e outro vértice requisitado.
	 * @param destiny Vertex - Vértice adjacente.
	 * @return float - Peso da aresta de ligação.
	 */
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
	public float getEdge(Vertex destiny) {
		for (int position = 0; position < this.vertexDegree(); position += 1) {
			if (this.edges[position].getVertex().equals(destiny)) {
				return this.edges[position].getWeight();
			}
		}
		return -1f;
	}
<<<<<<< HEAD

	public int vertexDegree() {
		return this.next;
	}

	public String getVertexName() {
		return this.vertexName;
	}

	public Edge[] getEdges() {
=======
	
	/**
	 * Método que retorna o grau do vértice.
	 * @return int - Grau do vértice.
	 */
	public int vertexDegree(){
		return this.next;
	}
	
	/**
	 * Método que retorna o nome do vértice.
	 * @return String - Nome do vértice.
	 */
	public String getVertexName(){
		return this.vertexName;
	}
	
	/**
	 * Método que retorna todas as arestas ligadas ao vértice.
	 * @return Edge[] - Vetor contendo todas as arestas do vértice.
	 */
	public Edge[] getEdges(){
>>>>>>> 4ae2998f0b2b5a4af38b1491893f8df960d7cc52
		return this.edges;
	}

	/**
	 * Método toString do vértice.
	 */
	@Override
	public String toString() {
		return this.vertexName;
	}

}
