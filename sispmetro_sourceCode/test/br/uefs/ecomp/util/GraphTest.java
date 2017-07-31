package br.uefs.ecomp.util;

import org.junit.Test;

import junit.framework.TestCase;

public class GraphTest extends TestCase {
	
	private Graph generateGraph() {
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
		
		return littleGraph;
	}

	@Test
	public void testAddVertexAndEdge() {
		Graph littleGraph = this.generateGraph();

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
		Graph littleGraph = this.generateGraph();
		
		IStack<String> test = littleGraph.minorWay("A", "D");
		String[] expected = {"A", "B", "F", "C", "D"};
		int count = 0;
		assertFalse(test.isEmpty());
		while(!test.isEmpty()) {
			assertEquals(expected[count], test.pop());
			count += 1;
		}
	}
	
	@Test
	public void testDjkstraWithoutAWayBetweenVertex() {
		Graph littleGraph = this.generateGraph();
		littleGraph.removeVertex("F");
		
		IStack<String> test = littleGraph.minorWay("A", "D");
		assertEquals(true, test.isEmpty());
	}
	
	@Test
	public void testDjkstraSameDestinyAndOrigin() {
		Graph littleGraph = this.generateGraph();
		
		IStack<String> test = littleGraph.minorWay("A", "A");
		assertEquals(1, test.size());
		assertEquals("A", test.pop());
	}
	
	@Test
	public void testRemoveEdge() {
		Graph littleGraph = this.generateGraph();
		littleGraph.removeEdge("A", "F");
		assertEquals(6, littleGraph.getNumOfEdges());
		assertEquals(6, littleGraph.getNumOfVertex());
	}
	
	@Test
	public void testRemoveVertex() {
		Graph littleGraph = this.generateGraph();
		littleGraph.removeVertex("F");
		assertEquals(3, littleGraph.getNumOfEdges());
		assertEquals(5, littleGraph.getNumOfVertex());
	}
	
	@Test
	public void testVertexIterator() {
		Graph littleGraph = this.generateGraph();
		Iterator<Vertex> iterator = littleGraph.vertexIterator();
		String[] expected = {"A", "B", "C", "D", "E", "F"};
		int count = 0;
		while(iterator.hasNext()) {
			assertEquals(expected[count], iterator.next().getVertexName());
			count += 1;
		}
	}
	
	@Test
	public void testEdgeIterator() {
		Graph littleGraph = this.generateGraph();
		Iterator<Integer> iterator = littleGraph.edgeIterator();
		int count = 0;
		while(iterator.hasNext()) {
			iterator.next();
			count += 1;
		}
		assertEquals(littleGraph.getNumOfEdges() * 2, count);
	}

}
