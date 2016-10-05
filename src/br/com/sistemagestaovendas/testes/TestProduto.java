package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.sistemagestaovendas.vendas.Produto;

public class TestProduto {

	@Test
	public void testConstrutor() {
		Produto produto = new Produto("Cerveja", 7);
		assertEquals( 7,produto.getPrecoFixo(), 0.01);
		assertEquals("Cerveja", produto.getNome());
	}
	
	@Test
	public void testAtualizarPreco(){
		Produto produto = new Produto("Cerveja", 7);
		produto.atualizarPreco((float)7.5);
		assertEquals((float)7.5, produto.getPrecoFixo(), 0.01);
	}

}
