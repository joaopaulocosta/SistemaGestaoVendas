package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe que armazenar� todas as despesas que n�o possuem nome cadastrado
 * na comanda
 * @author Joao
 *
 */
public class Outros implements Serializable{

	private float preco;
	

	/**
	 * Construtor simples recebe apenas o pre�o da despesa
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
	 * retorna o pre�o da despesa
	 * @return
	 */
	public float getPreco(){
		return this.preco;
	}
	
}
