package br.uefs.ecomp.util;

/**
 * Classe v�rtice que armazena dados e um vetor de arestas adjacentes ao v�rtice 
 * @author João Victor Oliveira Couto & Resemblinck Freitas
 */
public class Vertex {
	private Edge[] edges; /* Arestas do grafo */
	private final String vertexName; /* nome dos vertices do grafo */
	private int next;

	/**
	 * Construtor do vertice.
	 * 
	 * @param nameOfVertex
	 */
	public Vertex(String nameOfVertex) {
		this.edges = new Edge[1];
		this.vertexName = nameOfVertex;
		this.next = 0;
	}

	/**
	 * M�todo privado para expandir o tamanho do array de arestas contido dentro do
	 * v�rtice.
	 */
	private void expandVectors() {
		Edge[] temporaryV = new Edge[this.edges.length + 1];
		for (int position = 0; position < this.edges.length; position += 1)
			temporaryV[position] = this.edges[position];

		this.edges = temporaryV;
	}

	/**
	 * M�todo que adiciona um v�rtice adjacente e uma aresta de liga��o.
	 * 
	 * @param newLigation
	 *            Vertex - novo v�rtice adjacente ao vertice atual.
	 * @param ligationCost
	 *            float - Peso da aresta de liga��o.
	 */
	public void addEdge(Vertex newLigation, float ligationCost) {
		int newEdgePosition = this.next;
		for (int position = 0; position < this.next; position += 1) {
			Edge search = this.edges[position];
			if (search.getVertex().equals(newLigation)) { /* verifica se j� foi adicionado aquele v�rtice */
				newEdgePosition = position;
				position = this.next + 1;
			}
		}
		if (this.next == this.edges.length)
			this.expandVectors(); /* caso primeira adi��o, expande o vetor */

		this.edges[newEdgePosition] = new Edge(newLigation, ligationCost); /* adiciona a aresta */
		if (newEdgePosition == this.next)
			this.next += 1;
	}

	/**
	 * M�todo que remove um v�rtice adjacente e sua aresta de liga��o.
	 * 
	 * @param edgeThis
	 *            Vertex - V�rtice adjacente que ser� removido.
	 * @return Boolean - True se remover, False caso contr�rio.
	 */
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

	/**
	 * M�todo que remove todos os v�rtices adjacentes e as arestas de liga��o.
	 */
	public void removeAllEdges() {
		while (this.next > 0) {
			this.edges[0].getVertex().removeEdge(this);
			this.removeEdge(this.edges[0].getVertex());
		}
	}

	/**
	 * M�todo que retorna o peso de uma aresta de liga��o entre o v�rtice atual e
	 * outro v�rtice requisitado.
	 * 
	 * @param destiny
	 *            Vertex - V�rtice adjacente.
	 * @return float - Peso da aresta de liga��o.
	 */
	public float getEdge(Vertex destiny) {
		for (int position = 0; position < this.vertexDegree(); position += 1) {
			if (this.edges[position].getVertex().equals(destiny)) {
				return this.edges[position].getWeight();
			}
		}
		return -1f;
	}

	/**
	 * M�todo que retorna o grau do v�rtice.
	 * 
	 * @return int - Grau do v�rtice.
	 */
	public int vertexDegree() {
		return this.next;
	}

	/**
	 * M�todo que retorna o nome do v�rtice.
	 * 
	 * @return String - Nome do v�rtice.
	 */
	public String getVertexName() {
		return this.vertexName;
	}

	/**
	 * M�todo que retorna todas as arestas ligadas ao v�rtice.
	 * 
	 * @return Edge[] - Vetor contendo todas as arestas do v�rtice.
	 */
	public Edge[] getEdges() {
		return this.edges;
	}

	/**
	 * M�todo toString do v�rtice.
	 */
	@Override
	public String toString() {
		return this.vertexName;
	}

}
