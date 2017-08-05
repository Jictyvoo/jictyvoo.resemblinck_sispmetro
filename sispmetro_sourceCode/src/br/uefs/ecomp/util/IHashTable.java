package br.uefs.ecomp.util;

public interface IHashTable <KeyType, InformationType> {
	
	public void put(KeyType key, InformationType value);
	
	public InformationType get(KeyType key);
	
	public KeyType[] keySet();
	
	public void removeKey(KeyType key);
	
	public void removeValue(InformationType value);
	
	public boolean isEmpty();
	
	public int size();
	
}