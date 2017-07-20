package br.uefs.ecomp.util;

/**
 * Interface Iterator que define o funcionamento das funcoes utilizadas para andar pela arvore, ou outra estrutura de dados
 * que utilize do mesmo
 */
public interface Iterator <TypeReceived> {
	
	public boolean hasNext();

	public TypeReceived next();
	
}