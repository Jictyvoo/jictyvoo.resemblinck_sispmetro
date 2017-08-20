package br.uefs.ecomp.util;

public interface IStack <TypeReceived> {
	

	public int size();

	public boolean isEmpty();
	
	public TypeReceived pop();
	
	public void push(TypeReceived obj);
	
	public TypeReceived peek();
}
