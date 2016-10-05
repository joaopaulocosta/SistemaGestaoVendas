package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.sistemagestaovendas.vendas.Produto;
import br.com.sistemagestaovendas.vendas.Refeicao;

public class TestRefeicao {

	@Test
	public void testConstrutor() {
		Produto prod = new Produto("Refeição", (float)34.90);
		Refeicao ref = new Refeicao(prod, (float)15.83);
		assertEquals((float)15.83, ref.getValor(), 0.001);
	}

}
