package br.com.sistemagestaovendas.vendas;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe criada para manter as informa��es referentes � comanda de papel,
 *  comanda essa usada para informar os gastos dos clientes
 * @author Joao
 */
public class Comanda implements Serializable {
	

	private float valorTotal;	//valor referente a soma de todos os produtos consumidos
	private Date data;	//data que a comanda ser� registrada

	private ArrayList<Refeicao> listaRefeicoes;	//ArrayList com todas as refei��es consumidas
	private ArrayList<ProdutoComanda> listaProdutos; //ArrayList com todos os produtos consumidos
	private ArrayList<Outros> listaOutros;	//caso o produto consumido n�o esteja cadastrado ele �
											//inserido no ArrayList "outros"
	
	/**
	 * Construtor Comanda, � usada a data atual do sistema, os ArrayList s�o 
	 * iniciados e o valor total da comanda � setado como 0
	 */
	public Comanda(ArrayList<ProdutoComanda> listaProdutos){
		this.valorTotal = 0;
		this.data = new Date();
		this.listaRefeicoes = new ArrayList<Refeicao>();
		this.listaProdutos = listaProdutos;
		this.listaOutros = new ArrayList<Outros>();
		
	}
	
	/**
	 * M�todo acessor para valor total
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
	 * M�todo acessor para lista de refei��es consumidas
	 * @return lista de refei��es
	 */
	public ArrayList getListaRefeicao() {
		return listaRefeicoes;
	}
	
	/**
	 * M�todo acessor para lista Outros que cont�m os gastos em produtos
	 * que n�o est�o na comanda
	 * @return	lista Outros
	 */
	public ArrayList getListaOutros() {
		return listaOutros;
	}
	
	/**
	 * M�todo que adiciona uma nova refei��o � lista de refei��es
	 * @param ref nova refei��o
	 */
	public void addRefeicao(Refeicao ref) {
		this.listaRefeicoes.add(ref);
		this.valorTotal += ref.getValor();
	}
	
	/**
	 * M�todo que adiciona alguma nova dispesa na lista outros
	 * @param out nova dispesa
	 */
	public void addOutros(Outros out) {
		this.listaOutros.add(out);
		this.valorTotal += out.getPreco();
	}
	
	/**
	 * M�todo que retorna a quantidade de refei��es que ja foram
	 * inseridas na comanda
	 * @return tamanho da lista refei��es
	 */
	public int getQuantidadeRefeicoes() {
		return this.listaRefeicoes.size();
	}
	
	/**
	 * M�todo que retorna a quantidade de despesas inseridas 
	 * na lista outros
	 * @return tamanho da lista outros
	 */
	public int getQuantidadeOutros() {
		return this.listaOutros.size();
	}
	
	/**
	 * M�todo que retorna a data da comanda
	 * @return data
	 */
	public Date getData(){
		return data;
	}
	
	/**
	 * M�todo que retorna a soma dos pre�os de todas as refei��es da comanda
	 */
	public float somarRefeicoes(){
		float total = 0;
		for(Refeicao aux: this.listaRefeicoes){
			total += aux.getValor();
		}
		return total;
	}
	
	/**
	 * M�todo que retorna a soma de todas as despesas que foram inseridos no 
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
	 * M�todo que retorna a quantidade consumida de um determinado produto
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
