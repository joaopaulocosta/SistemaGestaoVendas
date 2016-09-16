package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe que armazenar� o valor das refei��es inseridas nas comandas
 * @author Joao
 *
 */
public class Refeicao  implements Serializable{

	private float valor;
	
	private Produto produto;
	
	/**
	 * Construtor simples
	 * @param produto Produto com nome Refei��o
	 * @param valor	Valor da refei��o
	 */
	public Refeicao(Produto produto, float valor){
		this.produto = produto;
		this.valor = valor;
	}
	
	/**
	 * Retorna valor da refei��o
	 * @return valor
	 */
	public float getValor() {
		return this.valor;
	}

}
