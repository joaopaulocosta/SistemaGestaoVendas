package vendas;

public class Outros {

	private String descricao;

	private float preco;
	
	public Outros(String desc, float preco){
		this.descricao = desc;
		this.preco = preco;
	}
	
	public void setDescricao(String desc) {
		this.descricao = desc;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public float getPreco(){
		return this.preco;
	}
	
}
