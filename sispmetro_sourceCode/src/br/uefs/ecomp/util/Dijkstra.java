package br.uefs.ecomp.util;

public class Dijkstra {
	
	public Dijkstra() {}
	
	/**
	 * Classe Privada que tem como objetivo auxiliar no algoritmo de menor caminho Dijkstra
	 * @author João Victor Oliveira Couto
	 */
	private class Searching {
		private Vertex vertex;
		private boolean visited;
		private float sizeOfWay;
		private Searching previous;
		
		Searching(Vertex vertexReceived) {
			this.vertex = vertexReceived;
			this.visited = false;
			this.sizeOfWay = Float.MAX_VALUE;
			this.previous = null;
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

		public Searching getPrevious() {
			return previous;
		}

		public void setPrevious(Searching previous) {
			this.previous = previous;
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
	 * Método público para o cálculo do menor caminho, o método irá verificar e preparar os dados para dar inicio ao algoritmo de busca
	 * @param origin	Informação contida no vértice de origem
	 * @param destiny	Informação contida no vértice de destino
	 * @return	Retorna uma pilha com todas as informações contidas em todos os vértices dentro do caminho, indo da origem até o destino
	 */
	public IStack<String> minorWay(Vertex first, Vertex second, Graph mainGraph) {
		if(first == null || second == null)
			return null;
		if(first == second) {
			IStack<String> returnSame = new Stack<String>();
			returnSame.push(first.getVertexName());
			return returnSame;
		}
		
		Searching[] vertex = new Searching[mainGraph.getNumOfVertex()];
		for(int position = 0; position < vertex.length; position += 1)
			vertex[position] = new Searching(mainGraph.getAllVertex()[position]);
		
		this.getSearchingVertex(first, vertex).setPrevious(null);
		this.searchWay(first, second, vertex);
		
		IStack<String> returnWay = new Stack<String>();
		Searching newStack = this.getSearchingVertex(second, vertex);
		if(newStack.getPrevious() != null) {
			while(newStack != null) {
				returnWay.push(newStack.getVertex().getVertexName());
				newStack = newStack.getPrevious();
			}
		}
		return returnWay;
	}
	
	/**
	 * Algoritmo para o menor caminho de Dijkstra
	 * @param first	Vértice de Origem
	 * @param destiny	Vértice de Destino
	 * @param searching	Vetor de objetos auxiliares à busca pelo caminho
	 * @return	Retorna uma pilha com o caminho de vértices do destino até a origem
	 */
	private void searchWay(Vertex first, Vertex destiny, Searching[] searching) {	/*Dijkstra*/
		Searching findWay = this.getSearchingVertex(first, searching);
		findWay.setSizeOfWay(0);
		/*begins the loop, and only stops if reached the destiny and the way as the minor of all*/
		do {
			Edge[] edges = findWay.getVertex().getEdges();
			
			for(int position = 0; position < findWay.getVertex().vertexDegree(); position += 1) {
				if(!this.visitedVertex(searching, edges[position].getVertex())) {
					Searching newWay = this.getSearchingVertex(edges[position].getVertex(), searching);
					float newWaySize = (findWay.getSizeOfWay() + edges[position].getWeight());
					if(newWaySize < newWay.getSizeOfWay()) {
						newWay.setSizeOfWay(newWaySize);
						newWay.setPrevious(findWay);
					}
				}
			}
			findWay.setVisited(true);
			findWay = this.nextVertex(searching);
			
		} while(findWay != null);
	}
	
}
