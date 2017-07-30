package br.uefs.ecomp.util;

import org.junit.Test;

import junit.framework.TestCase;

public class GraphTest extends TestCase {

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
	
	@Test
	public void testGraphConstructor() {
		Graph testGraph = new Graph('C', 10);
		assertEquals(10, testGraph.getNumOfVertex());
		assertEquals(10, testGraph.getNumOfEdges());
		
		testGraph = new Graph('K', 4);
		assertEquals(4, testGraph.getNumOfVertex());
		assertEquals(6, testGraph.getNumOfEdges());
	}
	
	@Test
	public void testDjkstra(){
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
		
		IStack<String> test = littleGraph.minorWay("A", "D");
		String[] expected = {"A", "B", "F", "C", "D"};
		int count = 0;
		while(!test.isEmpty()) {
			assertEquals(expected[count], test.pop());
			count += 1;
		}
	}
	
	@Test
	public void testRemoveEdge() {
		
	}
	
	@Test
	public void testRemoveVertex() {
		
	}

}
