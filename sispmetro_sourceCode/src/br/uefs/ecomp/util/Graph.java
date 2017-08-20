package br.uefs.ecomp.util;

/**
 * Classe Grafo que armazena uma lista de v�rtices e os conecta atrav�s de arestas
 * @author Jo�o Victor Oliveira Couto & Resemblinck Freitas
 */
public class Graph {
	private Vertex[] allVertex;
	private int next;
	
	/**
	 * Construtor padr�o do Grafo que inicia o Grafo de forma vazia
	 */
	public Graph(){
		this.allVertex = new Vertex[1];
		this.next = 0;
	}
	
	/**
	 * Construtor do Grafo que permite a constru��o automatizada de um Grafo Especial
	 * @param type	Tipo de Grafo Especial: C- Circular, K- Completo
	 * @param quantity	Quantidade de Vertices desse grafo
	 */
	public Graph(char type, int quantity) {
		this.allVertex = new Vertex[quantity];
		this.next = quantity;
		char previous = 'A';
			for(int position = 0; position < quantity; position += 1) {
				this.allVertex[position] = new Vertex("" + previous);
				previous += 1;
			}

		if(type == 'K' || type == 'k') {
			for(int position = 0; position < quantity; position += 1) 
				for(int additing = position + 1; additing < quantity; additing += 1) {
					this.allVertex[position].addEdge(this.allVertex[additing], 1);
					this.allVertex[additing].addEdge(this.allVertex[position], 1);
				}
			
		}
		else if(type == 'C' || type == 'c') {
			for(int position = 0; position < quantity - 1; position += 1) {
				this.allVertex[position].addEdge(this.allVertex[position + 1], 1);
				this.allVertex[position + 1].addEdge(this.allVertex[position], 1);
			}
			this.allVertex[0].addEdge(this.allVertex[quantity - 1], 1);
			this.allVertex[quantity - 1].addEdge(this.allVertex[0], 1);
		}
	}
	
	/**
	 * M�todo privado para expandir o tamanho do array de vertices contido dentro do grafo
	 */
	private void expandVertex(){
		Vertex[] temporaryV = new Vertex[this.allVertex.length + 1];
		for(int position = 0; position < this.allVertex.length; position += 1)
			temporaryV[position] = this.allVertex[position];
		
		this.allVertex = temporaryV;
	}
	
	/**
	 * M�todo privado para procurar o v�rtice que est� armazenando determinada informa��o
	 * @param vertexNameSearch	Informa��o armazenada no v�rtice que est� a procura
	 * @return	Retorna o v�rtice encontrado, caso n�o tenha sido encontrado retorna null
	 */
	private Vertex foundVertex(String vertexNameSearch){
		for(int position = 0; position < this.next; position += 1) {
			Vertex search = this.allVertex[position];
			if(search.getVertexName().equals(vertexNameSearch))
				return search;
		}
		return null;
	}
	
	/**
	 * Adiciona um v�rtice ao grafo
	 * @param newVertexName	Informa��o contida no v�rtice
	 */
	public void addVertex(String newVertexName){
		if(this.foundVertex(newVertexName) == null){
			if(this.next == this.allVertex.length)
				this.expandVertex();
			
			this.allVertex[this.next] = new Vertex(newVertexName);
			this.next += 1;
		}
	}
	
	/**
	 * Remove um v�rtice do grafo baseado na informa��o contida nele
	 * @param vertexName	Informa��o contida no v�rtice a ser deletado
	 * @return	Retorna true caso o v�rtice tenha sido deletado, e false caso contr�rio
	 */
	public boolean removeVertex(String vertexName) {
		Vertex deleted = this.foundVertex(vertexName);
		if(deleted == null)
			return false;
		deleted.removeAllEdges();
		for(int position = 0; position < this.next; position += 1) {
			if(this.allVertex[position].equals(deleted)) {
				this.allVertex[position] = this.allVertex[this.next - 1];
				this.next -= 1;
				break;
			}
		}
		return true;
	}
	
	/**
	 * Adiciona uma aresta ao Grafo
	 * @param firstVertex	O v�rtice de origem da aresta a ser adicionada
	 * @param secondVertex	O v�rtice de destino da aresta a ser adicionada
	 * @param edgeCost	O peso da Aresta a ser adicionada
	 * @return	Retorna se foi poss�vel adicionar a aresta
	 */
	public boolean addEdge(String firstVertex, String secondVertex, float edgeCost){
		Vertex first = this.foundVertex(firstVertex);
		Vertex second = this.foundVertex(secondVertex);
		if(first == null || second == null)
			return false;
		first.addEdge(second, edgeCost);
		second.addEdge(first, edgeCost);
		return true;
	}
	
	/**
	 * Remove uma aresta existente no Grafo
	 * @param firstVertex	O v�rtice de origem da aresta a ser removida
	 * @param secondVertex	O v�rtice de destino da aresta a ser removida
	 * @return
	 */
	public boolean removeEdge(String firstVertex, String secondVertex) {
		Vertex first = this.foundVertex(firstVertex);
		Vertex second = this.foundVertex(secondVertex);
		if(first == null || second == null)
			return false;
		boolean returnBoolean =  first.removeEdge(second);
		second.removeEdge(first);
		return returnBoolean;
	}
	
	/**
	 * M�todo que retorna o peso de uma aresta entre dois v�rtices
	 * @param firstVertex	V�rtice de Origem
	 * @param secondVertex	V�rtice de Destino
	 * @return	Peso da Aresta
	 */
	public float getEdge(String firstVertex, String secondVertex){
		Vertex first = this.foundVertex(firstVertex);
		Vertex second = this.foundVertex(secondVertex);
		if(first == null || second == null)
			return 0f;
		return first.getEdge(second);
	}
	 /**
	  * M�todo que retorna o n�mero de v�rtices que existem no Grafo
	  * @return	O n�mero de v�rtices existentes no Grafo
	  */
	public int getNumOfVertex() {
		return this.next;
	}
	
	/**
	  * M�todo que retorna o n�mero de arestas que existem no Grafo
	  * @return	O n�mero de arestas existentes no Grafo
	 */
	public int getNumOfEdges() {
		int returnNumber = 0;
		for(int position = 0; position < this.next; position += 1) {
			Vertex counting = this.allVertex[position];
			returnNumber += counting.vertexDegree();
		}
		return returnNumber/2;
	}
	
	/**
	 * M�todo p�blico para o c�lculo do menor caminho, o m�todo ir� verificar e preparar os dados para dar inicio ao algoritmo de busca
	 * @param origin	Informa��o contida no v�rtice de origem
	 * @param destiny	Informa��o contida no v�rtice de destino
	 * @return	Retorna uma pilha com todas as informa��es contidas em todos os v�rtices dentro do caminho, indo da origem at� o destino
	 */
	public IStack<String> minorWay(String origin, String destiny) {
		return new Dijkstra().minorWay(this.foundVertex(origin), this.foundVertex(destiny), this);
	}
	
	/**
	 * Iterador para andar pelas informa��es contidas nos v�rtices do Grafo
	 * @return Iterador de V�rtices
	 */
	public Iterator<Vertex> vertexIterator(){
		return new Iterator<Vertex>() {	/*Classe Anonima de um Iterador*/
			
			private int positionIterator;
			
			{
				this.positionIterator = 0;
			}

			@Override
			public boolean hasNext() {
				return this.positionIterator < next;
			}

			@Override
			public Vertex next() {
				Vertex returnVertex = allVertex[this.positionIterator];
				this.positionIterator += 1;
				return returnVertex;
			}
			
		};
	}
	
	/**
	 * Iterador para andar pelas informa��es contidas nas arestas do Grafo
	 * @return Iterador de Float
	 */
	public Iterator<Float> edgeIterator(){
		return new Iterator<Float>() {	/*Classe Anonima de um Iterador*/
			
			private int positionIterator;
			private int positionEdge;
			
			{
				this.positionIterator = 0;
				this.positionEdge = 0;
			}

			@Override
			public boolean hasNext() {
				return this.positionIterator < next ? allVertex[this.positionIterator].vertexDegree() > this.positionEdge : false;
			}

			@Override
			public Float next() {
				Float returnEdgeWeight = allVertex[this.positionIterator].getEdges()[this.positionEdge].getWeight();
				this.positionEdge += 1;
				if(this.positionEdge >= allVertex[this.positionIterator].vertexDegree()) {
					this.positionIterator += 1;
					this.positionEdge = 0;
				}
				return returnEdgeWeight;
			}
			
		};
	}
	
	/**
	 * M�todo que retorna um vetor com todos os dados armazenados no v�rtices
	 * @return Vetor com todos os dados armazenados no v�rtices
	 */
	public String[] getAllData() {
		String[] stationNames = new String[this.getNumOfVertex()];
		
		for(int position = 0; position < stationNames.length; position += 1) 
			stationNames[position] = this.allVertex[position].getVertexName();
		
		return stationNames;
	}
	
	/**
	 * M�todo que retorna um vetor com todos os v�rtices do grafo
	 * @return Vetor com todos os v�rtices do grafo
	 */
	public Vertex[] getAllVertex() {
		return this.allVertex;
	}
	
	/**
	 * M�todo toString do Grafo
	 */
	@Override
	public String toString() {
		String vertexId = "";
		for(int position = 0; position < this.next; position += 1) {
			if(position != this.next - 1)
				vertexId = vertexId + this.allVertex[position] + "*#*";
			else
				vertexId = vertexId + this.allVertex[position];				
		}
		return "Graph [NumOfVertex = " + getNumOfVertex() + ", NumOfEdges = " + getNumOfEdges() + "]" +
				"\r\nallVertex=[" + vertexId + "]";
	}

}
