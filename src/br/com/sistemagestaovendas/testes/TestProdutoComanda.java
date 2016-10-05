package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.sistemagestaovendas.vendas.ProdutoComanda;

public class TestProdutoComanda {

	@Test
	public void testConstrutor() {
		ProdutoComanda prod = new ProdutoComanda("Cerveja",(float)7.0);
		assertEquals("Cerveja", prod.getNome());
		assertEquals((float)7.0, prod.getPrecoFixo(), 0.001);
		assertEquals(0, prod.getQuantidade());
		prod.setQuantidade(5);
		assertEquals(5, prod.getQuantidade());
		
	}

}
