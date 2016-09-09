package vendas;

import java.util.Date;
import java.util.ArrayList;

public class Comanda {
	
	private static int cont;
	private int codigo;
	private float valorTotal;
	private Date data;

	private ArrayList<Refeicao> listaRefeicoes;
	private ArrayList<ProdutoComanda> listaProdutos;
	private ArrayList<Outros> listaOutros;
	
	public Comanda(){
		this.codigo = this.cont++;
		this.valorTotal = 0;
		this.data = new Date();
		
		this.listaRefeicoes = new ArrayList<Refeicao>();
		this.listaProdutos = new ArrayList<ProdutoComanda>();
		this.listaOutros = new ArrayList<Outros>();
		
	}
	
	public float getValorTotal() {
		return this.valorTotal;
	}

	public ArrayList getListaRefeicao() {
		return listaRefeicoes;
	}

	public ArrayList getListaOutros() {
		return listaOutros;
	}

	public void addRefeicao(Refeicao ref) {
		this.listaRefeicoes.add(ref);
		this.valorTotal += ref.getValor();
	}

	public void addOutros(Outros out) {
		this.listaOutros.add(out);
		this.valorTotal += out.getPreco();
	}

	public int getQuantidadeRefeicoes() {
		return this.listaRefeicoes.size();
	}

	public int getQuantidadeOutros() {
		return this.listaOutros.size();
	}

}
