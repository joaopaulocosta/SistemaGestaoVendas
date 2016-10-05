package br.com.sistemagestaovendas.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.sistemagestaovendas.vendas.Outros;

public class TestOutros {

	@Test
	public void test() {
		Outros out = new Outros((float)4.5);
		assertEquals((float)4.5, out.getPreco(),0.1);
		out.setPreco((float) 5.8);
		assertEquals((float)5.8, out.getPreco(),0.1);
	}

}
