package br.uefs.ecomp.util;

/**
 * @author Jictyvoo
 **/
public class WaySearch<TypeReceived extends Vertex> {
	
	private class Node<TypeNode>{
		Node<TypeNode> next;
		TypeNode information;
		int cost;
	}
	
	private Node<TypeReceived> first;
	private int sizeVar;
	private int wayCost;
	
	public WaySearch(){
		this.first = null;
		this.sizeVar = 0;
		this.wayCost = 0;
	}
	
	public int size() {
		return this.sizeVar;
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public TypeReceived pop() {
		if(this.first == null)
			return null;
		TypeReceived temporary = this.first.information;
		this.first = this.first.next;
		this.sizeVar = this.sizeVar - 1;
		return temporary;
	}

	public void push(TypeReceived obj, int cost) {
		Node<TypeReceived> temporary = new Node<TypeReceived>();
		temporary.information = obj;
		temporary.next = this.first;
		temporary.cost = cost;
		this.first = temporary;
		this.sizeVar = this.sizeVar + 1;
		this.wayCost += cost;
	}

	public TypeReceived peek() {
		return this.first.information;
	}
	
	public boolean contains(TypeReceived containsThis) {
		Node<TypeReceived> temporary = this.first;
		while(temporary != null) {
			if(temporary.information.equals(containsThis))
				return true;
			temporary = temporary.next;
		}
		return false;
	}
	
	public WaySearch<TypeReceived> copy(){
		Node<TypeReceived> temporary = this.first;
		WaySearch<TypeReceived> newWaySearch = new WaySearch<TypeReceived>();
		while(temporary != null) {
			newWaySearch.push(temporary.information, temporary.cost);
			temporary = temporary.next;
		}
		return newWaySearch;
	}
	
	public int getCost() {
		return this.wayCost;
	}
}
