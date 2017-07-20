package br.uefs.ecomp.util;

/**
 * Classe de Estrutura de Dados para gerenciar dados no formato FIFO
 * @author João Victor Oliveira Couto & Resemblinck Freitas
 **/
public class Queue<TypeReceived> implements IQueue<TypeReceived>{
	
	private class Node<TypeNode>{
		protected Node<TypeNode> next;
		protected TypeNode information;
	}
	
	private Node<TypeReceived> first;
	private Node<TypeReceived> last;
	private int sizeVar;
	
	public Queue(){
		this.first = null;
		this.last = null;
		this.sizeVar = 0;
	}
	
	/**
	 * Verifica se a fila está vazia
	 * @return Retorna um boolean true para vazia e false para cheia 
	**/
	@Override
	public boolean isEmpty() {
		return this.first == null;
	}
	
	/**
	 * @return Retorna o tamanho da Fila
	**/
	@Override
	public int size() {
		return this.sizeVar;
	}
	
	/**
	 * Insere um dado de qualquer tipo no final da Fila
	 * @param o Dado a ser armazenado 
	**/
	@Override
	public void add(TypeReceived receivedInfomation) {
		if(this.last != null){
			this.last.next = new Node<TypeReceived>();
			this.last = this.last.next;
			this.last.information = receivedInfomation;
		} else {
			this.last = new Node<TypeReceived>();
			this.first = this.last;
			this.last.information = receivedInfomation;
		}
		this.sizeVar = this.sizeVar + 1;
	}
	
	/**
	 * Remove um dado do inicio da Fila
	 * @return Retorna o dado removido
	**/
	@Override
	public TypeReceived remove() {
		if(this.first == null)
			return null;
		TypeReceived temporary = this.first.information;
		if(this.first == this.last)
			this.last = null;
		this.first = this.first.next;
		this.sizeVar = this.sizeVar - 1;
		return temporary;
	}
	
	/**
	 * @return Verifica se a fila está vazia e retorna null caso esteja, caso contrario retorna o dado no inicio da fila
	**/
	@Override
	public TypeReceived peek() {
		if(this.first == null)
			return null;
		return this.first.information;
	}
	
}
