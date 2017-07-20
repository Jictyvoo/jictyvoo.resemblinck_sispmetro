package br.uefs.ecomp.util;

import org.junit.Test;

import br.uefs.ecomp.util.Queue;
import junit.framework.TestCase;

public class QueueTest extends TestCase {

	@Test
	public void testAddInformations() {
		Queue<Integer> integerQueue = new Queue<Integer>();
		for(int count = 0; count < 20; count += 1)
			integerQueue.add(count);
		assertEquals(true, integerQueue.size() == 20);
		int valorTest =  integerQueue.peek();
		assertEquals(0, valorTest);
	}
	
	@Test
	public void testRemoveInformation() {
		Queue<Integer> integerQueue = new Queue<Integer>();
		for(int count = 0; count < 20; count += 1)
			integerQueue.add(count);

		assertEquals(true, integerQueue.size() == 20);
		int valorTest = 0;
		
		for(int count = 0; count < 20; count += 1)
			valorTest = integerQueue.remove();
		assertEquals(true, integerQueue.size() == 0);
		assertEquals(19, valorTest);
	}
	
	@Test
	public void testRemoveEmpty(){
		Queue<Integer> integerQueue = new Queue<Integer>();
		assertEquals(null, integerQueue.remove());
	}
	
	@Test
	public void testPeekEmpty(){
		Queue<Integer> integerQueue = new Queue<Integer>();
		assertEquals(null, integerQueue.peek());
	}
	
	@Test
	public void testIsntEmpty() {
		Queue<Integer> integerQueue = new Queue<Integer>();
		for(int count = 0; count < 7; count += 1)
			integerQueue.add(count);
		assertEquals(false, integerQueue.isEmpty());
	}
	
	@Test
	public void testIsEmpty() {
		Queue<Integer> integerQueue = new Queue<Integer>();
		assertEquals(true, integerQueue.isEmpty());
	}
}
