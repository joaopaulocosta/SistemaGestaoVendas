package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

public class ProdutoComanda implements Serializable{

	private int quantidade;

	private Produto produto;
	
	public ProdutoComanda(Produto produto){
		this.produto = produto;
		this.quantidade = 0;
	}
	
	public void setQuantidade(int quant) {
		this.quantidade = quant;
	}

	public int getQuantidade() {
		return this.quantidade;
	}
	
	public Produto getProduto(){
		return produto;
	}
	
}
