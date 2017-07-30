package br.uefs.ecomp.util;

class Edge {
	private Vertex vertex;
	private int cost;
	
	public Edge(Vertex newVertex, int edgeCost){
		this.vertex = newVertex;
		this.cost = edgeCost;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
