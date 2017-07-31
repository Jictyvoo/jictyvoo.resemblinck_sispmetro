package br.uefs.ecomp.util;

/**
 *
 * @author João Victor Oliveira Couto
 */
public class Graph {
	private Vertex[] allVertex;
	private int next;
	
	public Graph(){
		this.allVertex = new Vertex[1];
		this.next = 0;
	}

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
				for(int additing = position + 1; additing < quantity; additing += 1)
					this.allVertex[position].addEdge(this.allVertex[additing], 1);
			
		}
		else if(type == 'C' || type == 'c') {
			for(int position = 0; position < quantity - 1; position += 1)
				this.allVertex[position].addEdge(this.allVertex[position + 1], 1);
			this.allVertex[0].addEdge(this.allVertex[quantity - 1], 1);
		}
	}

	private void expandVertex(){
		Vertex[] temporaryV = new Vertex[this.allVertex.length + 1];
		for(int position = 0; position < this.allVertex.length; position += 1)
			temporaryV[position] = this.allVertex[position];
		
		this.allVertex = temporaryV;
	}

	private Vertex foundVertex(String vertexNameSearch){
		for(int position = 0; position < this.next; position += 1) {
			Vertex search = this.allVertex[position];
			if(search.getVertexName().equals(vertexNameSearch))
				return search;
		}
		return null;
	}

	public void addVertex(String newVertexName){
		if(this.foundVertex(newVertexName) == null){
			if(this.next == this.allVertex.length)
				this.expandVertex();
			
			this.allVertex[this.next] = new Vertex(newVertexName);
			this.next += 1;
		}
	}

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

	public boolean addEdge(String firstVertex, String secondVertex, int edgeCost){
		Vertex first = this.foundVertex(firstVertex);
		Vertex second = this.foundVertex(secondVertex);
		if(first == null || second == null)
			return false;
		return first.addEdge(second, edgeCost);
	}

	public boolean removeEdge(String firstVertex, String secondVertex) {
		Vertex first = this.foundVertex(firstVertex);
		Vertex second = this.foundVertex(secondVertex);
		if(first == null || second == null)
			return false;
		return first.removeEdge(second);
	}

	public int getNumOfVertex() {
		return this.next;
	}

	public int getNumOfEdges() {
		int returnNumber = 0;
		for(int position = 0; position < this.next; position += 1) {
			Vertex counting = this.allVertex[position];
			returnNumber += counting.vertexDegree();
		}
		return returnNumber/2;
	}
	
	private class Searching {
		private Vertex vertex;
		private boolean visited;
		private int sizeOfWay;
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
		
		int getSizeOfWay() {
			return this.sizeOfWay;
		}
		
		void setSizeOfWay(int newSize) {
			this.sizeOfWay = newSize;
		}
		
		Stack<Vertex> getWay() {
			return this.way;
		}
		
		void setWay(Stack<Vertex> newWay) {
			this.way = newWay;
		}
		
	}
	
	private boolean allVertexVisited(Searching[] searching) {
		for(Searching visited : searching) {
			if(!visited.asVisited())
				return true;
		}
		return false;
	}
	
	private Searching nextVertex(Searching[] searching) {
		Searching returnNext = null;
		for(Searching find : searching) {
			if(!find.asVisited() && (returnNext != null ? returnNext.getSizeOfWay() > find.getSizeOfWay() : true))
				returnNext = find;
		}
		return returnNext;
	}

	private boolean visitedVertex(Searching[] searching, Vertex vertex) {
		for(Searching find : searching) {
			if(find.getVertex().equals(vertex))
				return find.asVisited();
		}
		return false;
	}
	
	private Searching getSearchingVertex(Vertex find, Searching[] searching) {
		for(Searching found : searching) {
			if(found.getVertex().equals(find))
				return found;
		}
		return null;
	}
	
	private Stack<Vertex> searchWay(Vertex first, Vertex destiny, Searching[] searching) {	/*Djkstra*/
		Searching findWay = null;
		/*begins the loop, and only stops if reached the destiny and the way as the minor of all*/
		while(this.allVertexVisited(searching)) {
			if(findWay != null)
				findWay = this.nextVertex(searching);
			if(findWay == null) {
				findWay = this.getSearchingVertex(first, searching);
				findWay.setSizeOfWay(0);
				findWay.setWay(new Stack<Vertex>());
			}
			
			Edge[] edges = findWay.getVertex().getEdges();
			Stack<Vertex> inWay = findWay.getWay();
			
			for(int position = 0; position < findWay.getVertex().vertexDegree(); position += 1) {
				if(!this.visitedVertex(searching, edges[position].getVertex())) {
					Searching newWay = this.getSearchingVertex(edges[position].getVertex(), searching);
					int newWaySize = (findWay.getSizeOfWay() + edges[position].getCost());
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
		}
		Stack<Vertex> returnWay = this.getSearchingVertex(destiny, searching).getWay();
		if(returnWay != null) {returnWay.push(destiny);}
		return returnWay;
	}

	public IStack<String> minorWay(String origin, String destiny) {
		Vertex first = this.foundVertex(origin);
		Vertex second = this.foundVertex(destiny);
		if(first == null || second == null)
			return null;
		
		Searching[] vertex = new Searching[this.getNumOfVertex()];
		for(int position = 0; position < vertex.length; position += 1)
			vertex[position] = new Searching(this.allVertex[position]);
		
		IStack<Vertex> minorWatFound = this.searchWay(first, second, vertex);
		IStack<String> returnWay = new Stack<String>();
		if(minorWatFound != null) {
			while(!minorWatFound.isEmpty())
				returnWay.push(minorWatFound.pop().getVertexName());
		}
		return returnWay;
	}
	
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
	
	public Iterator<Integer> edgeIterator(){
		return new Iterator<Integer>() {	/*Classe Anonima de um Iterador*/
			
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
			public Integer next() {
				Integer returnEdgeWeight = allVertex[this.positionIterator].getEdges()[this.positionEdge].getCost();
				this.positionEdge += 1;
				if(this.positionEdge >= allVertex[this.positionIterator].vertexDegree()) {
					this.positionIterator += 1;
					this.positionEdge = 0;
				}
				return returnEdgeWeight;
			}
			
		};
	}
	
	public String[] getAllData() {
		String[] stationNames = new String[this.getNumOfVertex()];
		
		for(int position = 0; position < stationNames.length; position += 1) 
			stationNames[position] = this.allVertex[position].getVertexName();
		
		return stationNames;
	}

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
