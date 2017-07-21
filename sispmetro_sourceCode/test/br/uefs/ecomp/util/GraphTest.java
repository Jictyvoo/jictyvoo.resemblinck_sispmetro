package br.uefs.ecomp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testInsertVertexAndEdge() {
		Graph littleGraph = new Graph();

		littleGraph.addVertex("A");
		littleGraph.addVertex("B");
		littleGraph.addVertex("C");
		littleGraph.addVertex("D");
		littleGraph.addVertex("E");
		littleGraph.addVertex("F");

		littleGraph.addEdge("A", "B", 3);
		littleGraph.addEdge("A", "F", 5);
		littleGraph.addEdge("B", "F", 1);
		littleGraph.addEdge("F", "C", 2);
		littleGraph.addEdge("F", "E", 2);
		littleGraph.addEdge("C", "E", 4);
		littleGraph.addEdge("C", "D", 5);

		assertEquals(7, littleGraph.getNumOfEdges());
		assertEquals(6, littleGraph.getNumOfVertex());
	}

}
