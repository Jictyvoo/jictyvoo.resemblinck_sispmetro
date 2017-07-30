package br.uefs.ecomp.util;

public class Entry <KeyType, InformationType> {
	
	private KeyType key;
	private InformationType value;
	
	public Entry(KeyType key, InformationType value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyType getKey(){
		return key;
	}
	
	public void setKey(KeyType key){
		this.key = key;
	}
	
	public InformationType getValue(){
		return value;
	}
	
	public void setValue(InformationType value){
		this.value = value;
	}
	
	public boolean equals(Entry<KeyType, InformationType> o) {
		return this.key == null ? o.getKey() == null : this.key.equals(o.getKey());
	}
	
}