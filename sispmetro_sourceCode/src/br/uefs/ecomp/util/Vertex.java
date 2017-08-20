package br.uefs.ecomp.util;

/**
 * Classe vértice que armazena dados e um vetor de arestas adjacentes ao vértice
 * 
 * @author JoÃ£o Victor Oliveira Couto
 */
public class Vertex {
	private Edge[] edges; /* Arestas do grafo */
	private final String vertexName; /* nome dos vertices do grafo */
	private int next;

	public Vertex(String nameOfVertex) { /* construtor do vertice */
		this.edges = new Edge[1];
		this.vertexName = nameOfVertex;
		this.next = 0;
	}

	private void expandVectors() { /* funcao para expandir o tamanho do vetor de arestas */
		Edge[] temporaryV = new Edge[this.edges.length + 1];
		for (int position = 0; position < this.edges.length; position += 1)
			temporaryV[position] = this.edges[position];

		this.edges = temporaryV;
	}

	public void addEdge(Vertex newLigation, float ligationCost) {
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

	public void removeAllEdges() {
		while (this.next > 0) {
			this.edges[0].getVertex().removeEdge(this);
			this.removeEdge(this.edges[0].getVertex());
		}
	}

	public float getEdge(Vertex destiny) {
		for (int position = 0; position < this.vertexDegree(); position += 1) {
			if (this.edges[position].getVertex().equals(destiny)) {
				return this.edges[position].getWeight();
			}
		}
		return -1f;
	}

	public int vertexDegree() {
		return this.next;
	}

	public String getVertexName() {
		return this.vertexName;
	}

	public Edge[] getEdges() {
		return this.edges;
	}

	@Override
	public String toString() {
		return this.vertexName;
	}

}
