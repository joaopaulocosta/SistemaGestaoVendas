package br.com.sistemagestaovendas.vendas;

import java.util.Date;
import java.util.ArrayList;

/**
 * Classe criada para manter as informações referentes à comanda de papel,
 *  comanda essa usada para informar os gastos dos clientes
 * @author Joao
 */
public class Comanda {
	

	private float valorTotal;	//valor referente a soma de todos os produtos consumidos
	private Date data;	//data que a comanda será registrada

	private ArrayList<Refeicao> listaRefeicoes;	//ArrayList com todas as refeições consumidas
	private ArrayList<ProdutoComanda> listaProdutos; //ArrayList com todos os produtos consumidos
	private ArrayList<Outros> listaOutros;	//caso o produto consumido não esteja cadastrado ele é
											//inserido no ArrayList "outros"
	
	/**
	 * Construtor Comanda, é usada a data atual do sistema, os ArrayList são 
	 * iniciados e o valor total da comanda é setado como 0
	 */
	public Comanda(){
		this.valorTotal = 0;
		this.data = new Date();
		this.listaRefeicoes = new ArrayList<Refeicao>();
		this.listaProdutos = new ArrayList<ProdutoComanda>();
		this.listaOutros = new ArrayList<Outros>();
		
	}
	
	/**
	 * Método acessor para valor total
	 * @return valor referente a soma de todos os produtos consumidos
	 *
	 */
	public float getValorTotal() {
		return this.valorTotal;
	}
	
	/**
	 * Método acessor para lista de refeições consumidas
	 * @return lista de refeições
	 */
	public ArrayList getListaRefeicao() {
		return listaRefeicoes;
	}
	
	/**
	 * Método acessor para lista Outros que contém os gastos em produtos
	 * que não estão na comanda
	 * @return	lista Outros
	 */
	public ArrayList getListaOutros() {
		return listaOutros;
	}
	
	/**
	 * Método que adiciona uma nova refeição à lista de refeições
	 * @param ref nova refeição
	 */
	public void addRefeicao(Refeicao ref) {
		this.listaRefeicoes.add(ref);
		this.valorTotal += ref.getValor();
	}
	
	/**
	 * Método que adiciona alguma nova dispesa na lista outros
	 * @param out nova dispesa
	 */
	public void addOutros(Outros out) {
		this.listaOutros.add(out);
		this.valorTotal += out.getPreco();
	}
	
	/**
	 * Método que retorna a quantidade de refeições que ja foram
	 * inseridas na comanda
	 * @return tamanho da lista refeições
	 */
	public int getQuantidadeRefeicoes() {
		return this.listaRefeicoes.size();
	}
	
	/**
	 * Método que retorna a quantidade de despesas inseridas 
	 * na lista outros
	 * @return tamanh da lista outros
	 */
	public int getQuantidadeOutros() {
		return this.listaOutros.size();
	}

}
