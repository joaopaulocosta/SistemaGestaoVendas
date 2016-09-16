package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe que armazenará todas as despesas que não possuem nome cadastrado
 * na comanda
 * @author Joao
 *
 */
public class Outros implements Serializable{

	private float preco;
	

	/**
	 * Construtor simples recebe apenas o preço da despesa
	 * @param preco
	 */
	public Outros(float preco){

		this.preco = preco;
	}
	
	/**
	 * Altera o preco da despesa
	 * @param preco
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	/**
	 * retorna o preço da despesa
	 * @return
	 */
	public float getPreco(){
		return this.preco;
	}
	
}
