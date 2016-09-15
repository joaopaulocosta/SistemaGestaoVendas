package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

public class Refeicao  implements Serializable{

	private float valor;
	
	private Produto produto;
	
	public Refeicao(Produto produto, float valor){
		this.produto = produto;
		this.valor = valor;
	}
	
	public float getValor() {
		return this.valor;
	}

}
