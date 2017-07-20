package br.uefs.ecomp.util;

/**
 * @author Jictyvoo
 **/
public class Stack<TypeReceived> implements IStack<TypeReceived>{
	
	private class Node<TypeNode>{
		Node<TypeNode> next;
		TypeNode information;
	}
	
	private Node<TypeReceived> first;
	private int sizeVar;
	
	public Stack(){
		this.first = null;
		this.sizeVar = 0;
	}
	
	@Override
	public int size() {
		return this.sizeVar;
	}

	@Override
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override
	public TypeReceived pop() {
		if(this.first == null)
			return null;
		TypeReceived temporary = this.first.information;
		this.first = this.first.next;
		this.sizeVar = this.sizeVar - 1;
		return temporary;
	}

	@Override
	public void push(TypeReceived obj) {
		Node<TypeReceived> temporary = new Node<TypeReceived>();
		temporary.information = obj;
		temporary.next = this.first;
		this.first = temporary;
		this.sizeVar = this.sizeVar + 1;
	}

	@Override
	public TypeReceived peek() {
		return this.first.information;
	}
	
}
