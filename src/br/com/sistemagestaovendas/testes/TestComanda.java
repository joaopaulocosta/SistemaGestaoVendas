package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import br.com.sistemagestaovendas.vendas.Comanda;
import br.com.sistemagestaovendas.vendas.Outros;
import br.com.sistemagestaovendas.vendas.Produto;
import br.com.sistemagestaovendas.vendas.ProdutoComanda;
import br.com.sistemagestaovendas.vendas.Refeicao;


public class TestComanda {
	
	
	@Test
	public void testValorTotal(){
		ArrayList<ProdutoComanda> listaProdutos = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos);
		
		comanda.setValorTotal((float)55.5);
		assertEquals((float)55.5,comanda.getValorTotal(), 0.01);
	}
	
	
	@Test
	public void testData(){
		ArrayList<ProdutoComanda> listaProdutos = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos);
		
		Date data = new Date();
		assertEquals(data.toString(), comanda.getData().toString());
	}
	
	@Test
	public void testQuantidadeProdutoNome() {
		ArrayList<ProdutoComanda> listaProdutos = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos);
		
		ProdutoComanda prod2 = new ProdutoComanda("Cerveja", (float)7);
		prod2.setQuantidade(5);
		listaProdutos.add(prod2);
		
		int quant = comanda.getQuantidadeProdutoNome("Cerveja");
		assertEquals(5, quant);
		
	}
	
	@Test
	public void testRefeicoes() {
		ArrayList<ProdutoComanda> listaProdutos = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos);
		
		ProdutoComanda prod1 = new ProdutoComanda("Refeição", (float)34.9);
		listaProdutos.add(prod1);
		
		Refeicao ref1 = new Refeicao(prod1, (float)15.35);
		comanda.addRefeicao(ref1);
		
		Refeicao ref2 = new Refeicao(prod1, (float)14.65);
		comanda.addRefeicao(ref2);
		
		//verificando se valor das refeições foram somados ao valor total
		assertEquals((float)30.0, comanda.getValorTotal(), 0.01);
		assertEquals(2,comanda.getQuantidadeRefeicoes());
		
		ArrayList<Refeicao> listaRefeicoes = comanda.getListaRefeicao();
		assertEquals(2,listaRefeicoes.size());
	}
	
	@Test
	public void testOutros() {
		ArrayList<ProdutoComanda> listaProdutos = new ArrayList<ProdutoComanda>();
		Comanda comanda = new Comanda(listaProdutos);

		Outros out1 = new Outros((float)15.6);
		comanda.addOutros(out1);
		Outros out2 = new Outros((float)14.4);
		comanda.addOutros(out2);
		Outros out3 = new Outros((float)20.0);
		comanda.addOutros(out3);
		
		assertEquals(3,comanda.getQuantidadeOutros());
		assertEquals((float)50.0, comanda.getValorTotal(),0.01);
		
		ArrayList<Outros> listaOutros = comanda.getListaOutros();
		assertEquals(3,listaOutros.size());
	}
}
