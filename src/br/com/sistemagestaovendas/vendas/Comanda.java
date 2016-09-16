package br.com.sistemagestaovendas.vendas;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe criada para manter as informações referentes à comanda de papel,
 *  comanda essa usada para informar os gastos dos clientes
 * @author Joao
 */
public class Comanda implements Serializable {
	

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
	public Comanda(ArrayList<ProdutoComanda> listaProdutos){
		this.valorTotal = 0;
		this.data = new Date();
		this.listaRefeicoes = new ArrayList<Refeicao>();
		this.listaProdutos = listaProdutos;
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
	
	public void setValorTotal(float valor){
		this.valorTotal = valor;
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
	 * @return tamanho da lista outros
	 */
	public int getQuantidadeOutros() {
		return this.listaOutros.size();
	}
	
	/**
	 * Método que retorna a data da comanda
	 * @return data
	 */
	public Date getData(){
		return data;
	}
	
	/**
	 * Método que retorna a soma dos preços de todas as refeições da comanda
	 */
	public float somarRefeicoes(){
		float total = 0;
		for(Refeicao aux: this.listaRefeicoes){
			total += aux.getValor();
		}
		return total;
	}
	
	/**
	 * Método que retorna a soma de todas as despesas que foram inseridos no 
	 * campo Outros
	 */
	public float somarOutros(){
		float total = 0;
		for(Outros aux: this.listaOutros){
			total += aux.getPreco();
		}
		return total;
	}
	
	/**
	 * Método que retorna a quantidade consumida de um determinado produto
	 * @param nome	String com o nome do produto
	 * @return Quantidade do produto consumida
	 */
	public int getQuantidadeProdutoNome(String nome){
		int quant = 0;
		for(ProdutoComanda aux: this.listaProdutos){
			if(aux.getProduto().getNome().equals(nome)){
				quant = aux.getQuantidade();	
			}
		}
		return quant;
	}
	
}
