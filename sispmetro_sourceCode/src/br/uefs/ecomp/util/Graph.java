package br.uefs.ecomp.util;

/**
 * Classe Grafo que armazena uma lista de vértices e os conecta através de arestas
 * @author João Victor Oliveira Couto
 */
public class Graph {
	private Vertex[] allVertex;
	private int next;
	
	/**
	 * Construtor padrão do Grafo que inicia o Grafo de forma vazia
	 */
	public Graph(){
		this.allVertex = new Vertex[1];
		this.next = 0;
	}
	
	/**
	 * Construtor do Grafo que permite a construção automatizada de um Grafo Especial
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
	 * Método privado para expandir o tamanho do array de vertices contido dentro do grafo
	 */
	private void expandVertex(){
		Vertex[] temporaryV = new Vertex[this.allVertex.length + 1];
		for(int position = 0; position < this.allVertex.length; position += 1)
			temporaryV[position] = this.allVertex[position];
		
		this.allVertex = temporaryV;
	}
	
	/**
	 * Método privado para procurar o vértice que está armazenando determinada informação
	 * @param vertexNameSearch	Informação armazenada no vértice que está a procura
	 * @return	Retorna o vértice encontrado, caso não tenha sido encontrado retorna null
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
	 * Adiciona um vértice ao grafo
	 * @param newVertexName	Informação contida no vértice
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
	 * Remove um vértice do grafo baseado na informação contida nele
	 * @param vertexName	Informação contida no vértice a ser deletado
	 * @return	Retorna true caso o vértice tenha sido deletado, e false caso contrário
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
	 * @param firstVertex	O vértice de origem da aresta a ser adicionada
	 * @param secondVertex	O vértice de destino da aresta a ser adicionada
	 * @param edgeCost	O peso da Aresta a ser adicionada
	 * @return	Retorna se foi possível adicionar a aresta
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
	 * @param firstVertex	O vértice de origem da aresta a ser removida
	 * @param secondVertex	O vértice de destino da aresta a ser removida
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
	  * Método que retorna o número de vértices que existem no Grafo
	  * @return	O número de vértices existentes no Grafo
	  */
	public int getNumOfVertex() {
		return this.next;
	}
	
	/**
	  * Método que retorna o número de arestas que existem no Grafo
	  * @return	O número de arestas existentes no Grafo
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
	 * Classe Privada que tem como objetivo auxiliar no algoritmo de menor caminho Dijkstra
	 * @author João Victor Oliveira Couto
	 */
	private class Searching {
		private Vertex vertex;
		private boolean visited;
		private float sizeOfWay;
		private Stack<Vertex> way;
		
		Searching(Vertex vertexReceived) {
			this.vertex = vertexReceived;
			this.visited = false;
			this.sizeOfWay = Integer.MAX_VALUE;
			this.way = null;
		}
		
		boolean asVisited() {
			return this.visited;
		}
		
		void setVisited(boolean alreadyVisited) {
			this.visited = alreadyVisited;
		}
		
		Vertex getVertex() {
			return this.vertex;
		}
		
		float getSizeOfWay() {
			return this.sizeOfWay;
		}
		
		void setSizeOfWay(float newSize) {
			this.sizeOfWay = newSize;
		}
		
		Stack<Vertex> getWay() {
			return this.way;
		}
		
		void setWay(Stack<Vertex> newWay) {
			this.way = newWay;
		}
		
	}
	
	/**
	 * Método Privado que retorna o próximo vértice a ser visitado no algoritmo de menor caminho
	 * @param searching	Vetor de Vértices a ser usado para pesquisar o próximo vértice
	 * @return	O próximo vértice a ser visitado, caso não exista retorna null
	 */
	private Searching nextVertex(Searching[] searching) {
		Searching returnNext = null;
		for(Searching find : searching) {
			if(!find.asVisited() && (returnNext != null ? returnNext.getSizeOfWay() > find.getSizeOfWay() : true))
				returnNext = find;
		}
		return returnNext;
	}
	
	/**
	 * Retorna se o vértice já foi visitado
	 * @param searching	Vetor de Vértices a ser usado para pesquisar o vértice
	 * @param vertex	Vértice a se procurar
	 * @return	Retorna se o vértice já foi visitado
	 */
	private boolean visitedVertex(Searching[] searching, Vertex vertex) {
		for(Searching find : searching) {
			if(find.getVertex().equals(vertex))
				return find.asVisited();
		}
		return false;
	}
	
	/**
	 * Retorna o objeto Searching correspondente ao vértice solicitado
	 * @param find	Vértice a procurar
	 * @param searching	Vetor para a procura do Vértice
	 * @return	Retorna null caso não encontre o vértice, caso contrário retorna o objeto correspondente ao vértice encontrado
	 */
	private Searching getSearchingVertex(Vertex find, Searching[] searching) {
		for(Searching found : searching) {
			if(found.getVertex().equals(find))
				return found;
		}
		return null;
	}
	
	/**
	 * Algoritmo para o menor caminho de Dijkstra
	 * @param first	Vértice de Origem
	 * @param destiny	Vértice de Destino
	 * @param searching	Vetor de objetos auxiliares à busca pelo caminho
	 * @return	Retorna uma pilha com o caminho de vértices do destino até a origem
	 */
	private Stack<Vertex> searchWay(Vertex first, Vertex destiny, Searching[] searching) {	/*Dijkstra*/
		Searching findWay = this.getSearchingVertex(first, searching);
		findWay.setSizeOfWay(0);
		findWay.setWay(new Stack<Vertex>());
		/*begins the loop, and only stops if reached the destiny and the way as the minor of all*/
		do {
			Edge[] edges = findWay.getVertex().getEdges();
			Stack<Vertex> inWay = findWay.getWay();
			
			for(int position = 0; position < findWay.getVertex().vertexDegree(); position += 1) {
				if(!this.visitedVertex(searching, edges[position].getVertex())) {
					Searching newWay = this.getSearchingVertex(edges[position].getVertex(), searching);
					float newWaySize = (findWay.getSizeOfWay() + edges[position].getWeight());
					if(newWaySize < newWay.getSizeOfWay()) {
						Stack<Vertex> cloneWay = null;
						if(inWay != null) {
							cloneWay = inWay.copy();
							cloneWay.push(findWay.getVertex());
							newWay.setSizeOfWay(newWaySize);
						}
						newWay.setWay(cloneWay);
					}
				}
			}
			findWay.setVisited(true);
			findWay = this.nextVertex(searching);
			
		} while(findWay != null);
		Stack<Vertex> returnWay = this.getSearchingVertex(destiny, searching).getWay();
		if(returnWay != null) {returnWay.push(destiny);}
		return returnWay;
	}
	
	/**
	 * Método público para o cálculo do menor caminho, o método irá verificar e preparar os dados para dar inicio ao algoritmo de busca
	 * @param origin	Informação contida no vértice de origem
	 * @param destiny	Informação contida no vértice de destino
	 * @return	Retorna uma pilha com todas as informações contidas em todos os vértices dentro do caminho, indo da origem até o destino
	 */
	public IStack<String> minorWay(String origin, String destiny) {
		Vertex first = this.foundVertex(origin);
		Vertex second = this.foundVertex(destiny);
		if(first == null || second == null)
			return null;
		
		Searching[] vertex = new Searching[this.getNumOfVertex()];
		for(int position = 0; position < vertex.length; position += 1)
			vertex[position] = new Searching(this.allVertex[position]);
		
		IStack<Vertex> minorWayFound = this.searchWay(first, second, vertex);
		IStack<String> returnWay = new Stack<String>();
		if(minorWayFound != null) {
			while(!minorWayFound.isEmpty())
				returnWay.push(minorWayFound.pop().getVertexName());
		}
		return returnWay;
	}
	
	/**
	 * Iterador para andar pelas informações contidas nos vértices do Grafo
	 * @return Iterador de Vértices
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
	 * Iterador para andar pelas informações contidas nas arestas do Grafo
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
	 * Método que retorna um vetor com todos os dados armazenados no vértices
	 * @return Vetor com todos os dados armazenados no vértices
	 */
	public String[] getAllData() {
		String[] stationNames = new String[this.getNumOfVertex()];
		
		for(int position = 0; position < stationNames.length; position += 1) 
			stationNames[position] = this.allVertex[position].getVertexName();
		
		return stationNames;
	}
	
	/**
	 * Método toString do Grafo
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
