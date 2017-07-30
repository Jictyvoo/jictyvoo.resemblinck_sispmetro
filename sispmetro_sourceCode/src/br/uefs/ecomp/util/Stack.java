package br.uefs.ecomp.util;

/**
 * @author Jictyvoo
 **/
public class Stack<TypeReceived> implements IStack<TypeReceived>{
	
	private class Node<TypeNode>{
		Node<TypeNode> next;
		TypeNode information;
		Node<TypeNode> previous;
	}
	
	private Node<TypeReceived> first;
	private int sizeVar;
	private Node<TypeReceived> last;
	
	public Stack(){
		this.first = null;
		this.sizeVar = 0;
		this.last = null;
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
		if(this.first != null)
			this.first.previous = null;
		this.sizeVar = this.sizeVar - 1;
		return temporary;
	}

	@Override
	public void push(TypeReceived obj) {
		Node<TypeReceived> temporary = new Node<TypeReceived>();
		temporary.information = obj;
		temporary.next = this.first;
		if(this.first != null)
			this.first.previous = temporary;
		else
			this.last = temporary;
		this.first = temporary;
		this.sizeVar = this.sizeVar + 1;
	}

	@Override
	public TypeReceived peek() {
		return this.first.information;
	}
	
	public Stack<TypeReceived> copy(){
		Stack<TypeReceived> newStack = new Stack<TypeReceived>();
		Node<TypeReceived> temporary = this.last;
		while(temporary != null) {
			newStack.push(temporary.information);
			temporary = temporary.previous;
		}
		return newStack;
	}
	
	public Stack<TypeReceived> inverse(){
		Stack<TypeReceived> newStack = new Stack<TypeReceived>();
		Node<TypeReceived> temporary = this.first;
		while(temporary != null) {
			newStack.push(temporary.information);
			temporary = temporary.next;
		}
		return newStack;
	}
	
}
