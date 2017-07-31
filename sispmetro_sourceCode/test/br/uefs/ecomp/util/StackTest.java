package br.uefs.ecomp.util;

import org.junit.Test;

import junit.framework.TestCase;

public class StackTest extends TestCase {

	@Test
	public void testAddInformations() {
		Stack<Integer> integerStack = new Stack<Integer>();
		for(int count = 0; count < 20; count += 1)
			integerStack.push(count);
		assertEquals(true, integerStack.size() == 20);
		int valorTest =  integerStack.peek();
		assertEquals(19, valorTest);
	}
	
	@Test
	public void testRemoveInformation() {
		Stack<Integer> integerStack = new Stack<Integer>();
		for(int count = 0; count < 20; count += 1)
			integerStack.push(count);

		assertEquals(true, integerStack.size() == 20);
		int valorTest = 0;
		
		for(int count = 0; count < 20; count += 1)
			valorTest = integerStack.pop();
		assertEquals(true, integerStack.size() == 0);
		assertEquals(0, valorTest);
	}
	
	@Test
	public void testRemoveEmpty(){
		Stack<Integer> integerStack = new Stack<Integer>();
		assertEquals(null, integerStack.pop());
	}
	
	@Test
	public void testPeekEmpty(){
		Stack<Integer> integerStack = new Stack<Integer>();
		assertEquals(null, integerStack.peek());
	}
	
	@Test
	public void testIsntEmpty() {
		Stack<Integer> integerStack = new Stack<Integer>();
		for(int count = 0; count < 7; count += 1)
			integerStack.push(count);
		assertEquals(false, integerStack.isEmpty());
	}
	
	@Test
	public void testIsEmpty() {
		Stack<Integer> integerStack = new Stack<Integer>();
		assertEquals(true, integerStack.isEmpty());
	}
	
	@Test
	public void testCopy() {
		Stack<Integer> integerStack = new Stack<Integer>();
		for(int count = 0; count < 20; count += 1)
			integerStack.push(count);
		
		Stack<Integer> cloneStack = integerStack.copy();
		assertEquals(integerStack.size(), cloneStack.size());
		
		for(int count = 0; count < 20; count += 1) {
			assertEquals(integerStack.pop(), cloneStack.pop());
		}
	}
}
