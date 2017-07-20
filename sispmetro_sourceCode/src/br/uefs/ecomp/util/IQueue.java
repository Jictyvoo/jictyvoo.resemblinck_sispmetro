package br.uefs.ecomp.util;

public interface IQueue <TypeReceived> {
  
	public boolean isEmpty();

	public int size();

	public void add(TypeReceived receivedInfomation);

	public TypeReceived remove();

	public TypeReceived peek();		
}
