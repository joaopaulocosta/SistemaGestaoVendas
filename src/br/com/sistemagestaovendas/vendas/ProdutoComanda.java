package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe utilizada para armazenar a quantidade que será consumida
 * de cada produto
 * @author Joao
 *
 */
public class ProdutoComanda extends Produto implements Serializable{

	private int quantidade;

	
	/**
	 * Construtor simples, recebe um produto e adiciona 0 para a 
	 * quantidade consumida deste produto
	 * @param produto
	 */
	public ProdutoComanda(String nome, float preco){
		super(nome,preco);
		this.quantidade = 0;
	}
	
	/**
	 * Adiciona um valor para a quantidade consumida do produto
	 * @param quant
	 */
	public void setQuantidade(int quant) {
		this.quantidade = quant;
	}
	
	/**
	 * Retorna a quantidade consumida do produto
	 * @return quantidade
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	
}
