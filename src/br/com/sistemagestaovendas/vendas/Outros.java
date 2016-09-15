package br.com.sistemagestaovendas.vendas;

import java.io.Serializable;

public class Outros implements Serializable{

	private String descricao;

	private float preco;
	
	public Outros(String desc, float preco){
		this.descricao = desc;
		this.preco = preco;
	}
	
	public Outros(float preco){
		this.descricao = "";
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
