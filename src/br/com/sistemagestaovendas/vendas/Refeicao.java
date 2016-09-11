package br.com.sistemagestaovendas.vendas;


public class Refeicao  {

	private int valor;
	
	private Produto produto;
	
	public Refeicao(Produto produto, int valor){
		this.produto = produto;
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}

}
