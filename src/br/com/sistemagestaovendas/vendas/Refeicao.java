package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe que armazenará o valor das refeições inseridas nas comandas
 * @author Joao
 *
 */
public class Refeicao  implements Serializable{

	private float valor;
	
	private Produto produto;
	
	/**
	 * Construtor simples
	 * @param produto Produto com nome Refeição
	 * @param valor	Valor da refeição
	 */
	public Refeicao(Produto produto, float valor){
		this.produto = produto;
		this.valor = valor;
	}
	
	/**
	 * Retorna valor da refeição
	 * @return valor
	 */
	public float getValor() {
		return this.valor;
	}

}
