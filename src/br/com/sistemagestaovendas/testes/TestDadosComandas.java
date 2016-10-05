package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.com.sistemagestaovendas.BD.DadosComandas;
import br.com.sistemagestaovendas.vendas.Comanda;
import br.com.sistemagestaovendas.vendas.Outros;
import br.com.sistemagestaovendas.vendas.ProdutoComanda;
import br.com.sistemagestaovendas.vendas.Refeicao;

public class TestDadosComandas {

	@Test
	public void testSalvarCarregarDados() {
		ArrayList<Comanda> listaComandas = new ArrayList<Comanda>();
	
		listaComandas.add(criarComanda());
		listaComandas.add(criarComanda());
		listaComandas.add(criarComanda());
		listaComandas.add(criarComanda());
		listaComandas.add(criarComanda());
		
		DadosComandas bd1 = new DadosComandas(listaComandas,"testeComandas.bin");
		bd1.salvarDados();
		
		ArrayList<Comanda> novaLista = new ArrayList<Comanda>();
		
		DadosComandas bd2 = new DadosComandas(novaLista, "testeComandas.bin");
		bd2.carregarDados();
		
		//verificando se dados salvos são os msm lidos posteriormente
		assertEquals(listaComandas.size(), novaLista.size());
		assertEquals(listaComandas.get(1).getValorTotal(), novaLista.get(1).getValorTotal(), 0.01);
	}
	
	public Comanda criarComanda(){
		ArrayList<ProdutoComanda> listaProdutos1 = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos1);
		
		ProdutoComanda prod1 = new ProdutoComanda("Refeição", (float)34.9);
		listaProdutos1.add(prod1);
		
		Refeicao ref1 = new Refeicao(prod1, (float)15.35);
		comanda.addRefeicao(ref1);
		
		Refeicao ref2 = new Refeicao(prod1, (float)14.65);
		comanda.addRefeicao(ref2);
		
		Outros out1 = new Outros((float)15.6);
		comanda.addOutros(out1);
		Outros out2 = new Outros((float)14.4);
		comanda.addOutros(out2);
		Outros out3 = new Outros((float)20.0);
		comanda.addOutros(out3);
		
		return comanda;
	}
}
