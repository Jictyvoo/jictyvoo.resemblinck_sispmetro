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
	
	private IStack<Vertex> searchWay(Vertex searching, IStack<Vertex> visited) {	/*Dikstra*/
		return null;
	}
	
	public IStack<Vertex> minorWay(String origin, String destiny) {	/*Mudar de Fila para Pilha*/
		Vertex first = this.foundVertex(origin);
		Vertex second = this.foundVertex(destiny);
		if(first == null || second == null)
			return null;
		IStack<Vertex> returnStack = new Stack<Vertex>();
		returnStack.push(first);	/*adiciona o primeiro elemento para iniciar a busca recursiva*/
		return this.searchWay(second, returnStack);
	}
}
