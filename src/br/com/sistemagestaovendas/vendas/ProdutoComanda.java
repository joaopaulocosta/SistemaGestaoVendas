package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe utilizada para armazenar a quantidade que será consumida
 * de cada produto
 * @author Joao
 *
 */
public class ProdutoComanda implements Serializable{

	private int quantidade;

	private Produto produto;
	
	/**
	 * Construtor simples, recebe um produto e adiciona 0 para a 
	 * quantidade consumida deste produto
	 * @param produto
	 */
	public ProdutoComanda(Produto produto){
		this.produto = produto;
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
	
	/**
	 * Retorna o produto que foi inserido na criação da classe
	 * @return produto
	 */
	public Produto getProduto(){
		return produto;
	}
	
}
