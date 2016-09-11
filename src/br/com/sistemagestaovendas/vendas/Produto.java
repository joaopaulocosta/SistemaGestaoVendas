package br.com.sistemagestaovendas.vendas;

/**
 * Classe que mantém informações sobre os produtos que serão inseridos
 * em cada comanda
 * @author Joao
 *
 */
public class Produto {

	private String nome;	//nome do produto
	private float precoFixo;	//preço que será calculado pela comanda
	
	/**
	 * Construtor da classe que inicializa os valores da classe de acordo
	 * com  os dois paramêtros de entrada
	 * @param nome designação do produto
	 * @param preco valor que será calculado 
	 */
	public Produto(String nome, float preco){
		this.nome = nome;
		this.precoFixo = preco;
	}
	
	/**
	 * Método criado para atualização de preço dos produtos
	 * @param novoPreco novo valor de preço
	 */
	public void atualizarPreco(float novoPreco) {
		this.precoFixo = novoPreco;
	}
	
	/**
	 * Método acessor para o atributo preço fixo
	 * @return valor referente ao preço
	 */
	public Float getPrecoFixo() {
		return this.precoFixo;
	}
	
	/**
	 * Método acessor para o atributo nome do produto
	 * @return nome do produto
	 */
	public String getNome() {
		return this.nome;
	}

}
