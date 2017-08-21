package br.uefs.ecomp.util;

public class Dijkstra {
	
	public Dijkstra() {}
	
	/**
	 * Classe Privada que tem como objetivo auxiliar no algoritmo de menor caminho Dijkstra
	 * @author Jo�o Victor Oliveira Couto
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
	 * M�todo Privado que retorna o pr�ximo v�rtice a ser visitado no algoritmo de menor caminho
	 * @param searching	Vetor de V�rtices a ser usado para pesquisar o pr�ximo v�rtice
	 * @return	O pr�ximo v�rtice a ser visitado, caso n�o exista retorna null
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
	 * Retorna se o v�rtice j� foi visitado
	 * @param searching	Vetor de V�rtices a ser usado para pesquisar o v�rtice
	 * @param vertex	V�rtice a se procurar
	 * @return	Retorna se o v�rtice j� foi visitado
	 */
	private boolean visitedVertex(Searching[] searching, Vertex vertex) {
		for(Searching find : searching) {
			if(find.getVertex().equals(vertex))
				return find.asVisited();
		}
		return false;
	}
	
	/**
	 * Retorna o objeto Searching correspondente ao v�rtice solicitado
	 * @param find	V�rtice a procurar
	 * @param searching	Vetor para a procura do V�rtice
	 * @return	Retorna null caso n�o encontre o v�rtice, caso contr�rio retorna o objeto correspondente ao v�rtice encontrado
	 */
	private Searching getSearchingVertex(Vertex find, Searching[] searching) {
		for(Searching found : searching) {
			if(found.getVertex().equals(find))
				return found;
		}
		return null;
	}
	
	/**
	 * M�todo p�blico para o c�lculo do menor caminho, o m�todo ir� verificar e preparar os dados para dar inicio ao algoritmo de busca
	 * @param origin	Informa��o contida no v�rtice de origem
	 * @param destiny	Informa��o contida no v�rtice de destino
	 * @return	Retorna uma pilha com todas as informa��es contidas em todos os v�rtices dentro do caminho, indo da origem at� o destino
	 */
	public IStack<String> minorWay(Vertex first, Vertex second, Graph mainGraph) {
		if(first == null || second == null)
			return null;
		if(first == second) {	/*verifica se a origem � igual ao destino*/
			IStack<String> returnSame = new Stack<String>();
			returnSame.push(first.getVertexName());
			return returnSame;
		}
		
		Searching[] vertex = new Searching[mainGraph.getNumOfVertex()];	/*prepara os v�rtices para a execu��o do Dijkstra*/
		for(int position = 0; position < vertex.length; position += 1)
			vertex[position] = new Searching(mainGraph.getAllVertex()[position]);
		
		this.getSearchingVertex(first, vertex).setPrevious(null);
		this.searchWay(first, second, vertex);	/*executa Dijkstra*/
		
		IStack<String> returnWay = new Stack<String>();
		Searching newStack = this.getSearchingVertex(second, vertex);
		if(newStack.getPrevious() != null) {	/*constr�i a pilha com o menor caminho*/
			while(newStack != null) {
				returnWay.push(newStack.getVertex().getVertexName());
				newStack = newStack.getPrevious();
			}
		}
		return returnWay;
	}
	
	/**
	 * Algoritmo para o menor caminho de Dijkstra
	 * @param first	V�rtice de Origem
	 * @param destiny	V�rtice de Destino
	 * @param searching	Vetor de objetos auxiliares � busca pelo caminho
	 * @return	Retorna uma pilha com o caminho de v�rtices do destino at� a origem
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
