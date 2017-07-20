package br.uefs.ecomp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test() {
		Graph littleGraph = new Graph();

		littleGraph.addVertex("A");
		littleGraph.addVertex("B");
		littleGraph.addVertex("C");
		littleGraph.addVertex("D");
		littleGraph.addVertex("E");
		littleGraph.addVertex("F");

		littleGraph.addEdge("A", "B", 2);
		littleGraph.addEdge("A", "C", 2);
		littleGraph.addEdge("A", "F", 2);

		assertEquals(3, littleGraph.getNumOfEdges());
		assertEquals(6, littleGraph.getNumOfVertex());
	}

}
