package vendas;

public class Produto {

	private String nome;

	private float precoFixo;
	
	public Produto(String nome, float preco){
		this.nome = nome;
		this.precoFixo = preco;
	}
	
	public void atualizarPreco(float novoPreco) {
		this.precoFixo = novoPreco;
	}

	public Float getPrecoFixo() {
		return this.precoFixo;
	}

	public String getNome() {
		return this.nome;
	}

}
