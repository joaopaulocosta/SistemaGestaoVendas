package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import br.com.sistemagestaovendas.BD.DadosProdutos;
import br.com.sistemagestaovendas.vendas.Produto;
import org.junit.Test;

public class TestDadosProdutos {

	@Test
	public void testSalvarCarregarDados() {
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
		Produto prod1 = new Produto("Cerveja", (float)7);
		Produto prod2 = new Produto("Refri", (float)5);
		Produto prod3 = new Produto("Pinga", (float)4);
		Produto prod4 = new Produto("Agua", (float)2);
		Produto prod5 = new Produto("PF", (float)12);
		listaProdutos.add(prod1);
		listaProdutos.add(prod2);
		listaProdutos.add(prod3);
		listaProdutos.add(prod4);
		listaProdutos.add(prod5);
		
		DadosProdutos bd1 = new DadosProdutos(listaProdutos, "testeProdutos.bin");
		bd1.salvarDados();
		
		ArrayList<Produto> novaLista = new ArrayList<Produto>();
		DadosProdutos bd2 = new DadosProdutos(novaLista, "testeProdutos.bin");
		bd2.carregarDados();
		
		//verificando se dados salvos são os msm lidos posteriormente
		assertEquals(listaProdutos.size(), novaLista.size());
		assertEquals(listaProdutos.get(1).getNome(), novaLista.get(1).getNome());
		assertEquals(listaProdutos.get(1).getPrecoFixo(), novaLista.get(1).getPrecoFixo(), 0.01);
	}
	


}
