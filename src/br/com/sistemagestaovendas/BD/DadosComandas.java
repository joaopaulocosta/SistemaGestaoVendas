package br.com.sistemagestaovendas.BD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.sistemagestaovendas.vendas.Comanda;
import br.com.sistemagestaovendas.vendas.Produto;
/**
 * Classe respons�vel por armazenar e carregar os dados referentes 
 * a classe Comanda
 * @author Joao
 *
 */
public class DadosComandas {
	
	private String nomeArquivo;
	private ArrayList<Comanda> listaComandas;
	
	/**
	 * Construtor que recebe a lista de comandas que ser�o tratadas
	 * @param listaComandas ArrayList com comandas que ser�o armazenadas ou carregadas
	 */
	public DadosComandas(ArrayList<Comanda> listaComandas, String nome){
		if(nome == null)
			this.nomeArquivo = "comandas.bin";
		else
			this.nomeArquivo = nome;
			
		this.listaComandas = listaComandas;
	}
	
	/**
	 * fun��o que ira importar dados binarios de arquivo ao iniciar o programa
	 */
	public void carregarDados(){
		try {
			File file = new File( this.nomeArquivo );
			FileInputStream in = new FileInputStream( file );
			ObjectInput objectIn = new ObjectInputStream( in );
			
			//lendo comandas de arquivo binario
			int tam = objectIn.readInt();
			for(int i = 0; i< tam; i++){
				Comanda novaComanda = (Comanda) objectIn.readObject();
				listaComandas.add(novaComanda);
			}
			objectIn. close();
			in. close();
			
		} catch (FileNotFoundException e) {
			System.out.println( "Arq. de dados das comandas n�o encontrado. " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
		} catch (Exception e) {
			System.out.println( "Erro ao ler arquivo. " + e.getMessage() );
		}
	}
	
	/**
	 * fun��o que ira salvar os dados de produtos antes de sair da aplica��o
	 */
	public void salvarDados(){
		try {
				File file = new File( this.nomeArquivo );
				FileOutputStream out = new FileOutputStream( file );
				ObjectOutput objectOut = new ObjectOutputStream( out );
				
				//gravando dados das comandas no arquivo binario
				objectOut.writeInt(this.listaComandas.size());
				for(Comanda comanda: this.listaComandas){
					objectOut.writeObject(comanda);
				}	
				objectOut. close();
				out. close();
				
			} catch (FileNotFoundException e) {
				System.out.println( "Arq. de dados n�o encontrado. " + e.getMessage() );
			} catch (IOException e) {
				System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
			} catch (Exception e) {
				System.out.println( "Erro ao gravar arquivo. " + e.getMessage() );
			}
		
	}
}

