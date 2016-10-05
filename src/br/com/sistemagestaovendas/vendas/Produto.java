package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

/**
 * Classe que mant�m informa��es sobre os produtos que ser�o inseridos
 * em cada comanda
 * @author Joao
 *
 */
public class Produto implements Serializable {

	private String nome;	//nome do produto
	private Float precoFixo;	//pre�o que ser� calculado pela comanda
	
	/**
	 * Construtor da classe que inicializa os valores da classe de acordo
	 * com  os dois param�tros de entrada
	 * @param nome designa��o do produto
	 * @param preco valor que ser� calculado 
	 */
	public Produto(String nome, float preco){
		this.nome = nome;
		this.precoFixo = preco;
	}
	
	/**
	 * M�todo criado para atualiza��o de pre�o dos produtos
	 * @param novoPreco novo valor de pre�o
	 */
	public void atualizarPreco(float novoPreco) {
		this.precoFixo = novoPreco;
	}
	
	/**
	 * M�todo acessor para o atributo pre�o fixo
	 * @return valor referente ao pre�o
	 */
	public Float getPrecoFixo() {
		return this.precoFixo;
	}
	
	/**
	 * M�todo acessor para o atributo nome do produto
	 * @return nome do produto
	 */
	public String getNome() {
		return this.nome;
	}

}
