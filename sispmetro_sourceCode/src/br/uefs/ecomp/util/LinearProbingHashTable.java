package br.uefs.ecomp.util;

public class LinearProbingHashTable<KeyType, InformationType> implements IHashTable<KeyType, InformationType> {
	
	private final double LOAD_FACTOR = 0.5;
	private final Entry<KeyType, InformationType> EMPTY = new Entry<KeyType, InformationType>(null, null);
	
	private Entry<KeyType, InformationType>[] entries;
	private int sizeVar;
	
	@SuppressWarnings("unchecked")
	public LinearProbingHashTable(int size) {
		this.entries = (Entry<KeyType, InformationType>[]) new Object[size - 1];	/*ERROR*/
	}
	
	public LinearProbingHashTable() {
		this(31);
	}
	
	private int hashFunction(Entry<KeyType, InformationType>[] entrySet, KeyType key) {	/*Wrong*/
		return Math.abs(key.hashCode() % entrySet.length);
	}
	
	private double loadFactor() {
		return size() / (double) entries.length;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		Entry<KeyType, InformationType>[] temp = this.entries;
		this.entries = (Entry<KeyType, InformationType>[]) new Object[entries.length * 2];
		for (Entry<KeyType, InformationType> e : temp) {
			if (e != null ? !e.equals(EMPTY) : false) {
				put(e.getKey(), e.getValue());
			}
		}
	}
	
	private int findPos(Entry<KeyType, InformationType>[] entrySet, int pos, Entry<KeyType, InformationType> e) {
		int firstEmpty = -1;
		while (entries[pos] != null && !entries[pos].equals(e)) {
			if (firstEmpty == -1 && entries[pos].equals(EMPTY)) {
				firstEmpty = pos;
			}
			pos = (pos + 1) % entries.length;
		}
		if (entries[pos] == null && firstEmpty != -1) {
			return firstEmpty;
		} else {
			return pos;
		}
	}
	
	private boolean isEmpty(int pos) {
		if(pos >= this.entries.length)
			return true;
		return this.entries[pos] == null ? true : this.entries[pos].equals(EMPTY);
	}
	
	public void put(KeyType key, InformationType value) {
		Entry<KeyType, InformationType> e = new Entry<KeyType, InformationType>(key, value);
		int i = findPos(entries, hashFunction(entries, key), e);
		if (isEmpty(i)) {
			entries[i] = e;
			sizeVar++;
			if (loadFactor() > LOAD_FACTOR) {
				resize();
			}
		}
	}

	@Override
	public InformationType get(KeyType key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyType[] keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeKey(KeyType key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeValue(InformationType value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		return this.sizeVar == 0;
	}

	@Override
	public int size() {
		return this.sizeVar;
	}

}