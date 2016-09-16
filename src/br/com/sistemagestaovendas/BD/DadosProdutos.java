package br.com.sistemagestaovendas.BD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import br.com.sistemagestaovendas.vendas.Produto;

/**
 * Classe respons�vel por armazenar e carregar os dados referentes 
 * a classe Produto
 * @author Joao-
 *
 */
public class DadosProdutos {
	
	private ArrayList<Produto> listaProdutos;
	
	/**
	 * Construtor que recebe a lista de produtos que ser�o tratados
	 * @param listaComandas
	 */
	public DadosProdutos(ArrayList<Produto> listaProdutos){
		this.listaProdutos = listaProdutos;
	}
	
	/**
	 * M�todo que ira importar dados binarios de arquivo ao iniciar o programa
	 */
	public void carregarDados(){
		try {
			File file = new File( "produtos.bin" );
			FileInputStream in = new FileInputStream( file );
			ObjectInput objectIn = new ObjectInputStream( in );
			
			//lendo produtos de arquivo binario
			int tam = objectIn.readInt();
			for(int i = 0; i< tam; i++){
				Produto novoProduto = (Produto) objectIn.readObject();
				listaProdutos.add(novoProduto);
			}
			objectIn. close();
			in. close();
			
		} catch (FileNotFoundException e) {
			System.out.println( "Arq. de dados dos produtos n�o encontrado. " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "Erro de E/S. Causa: " + e.getMessage() );
		} catch (Exception e) {
			System.out.println( "Erro ao ler arquivo. " + e.getMessage() );
		}
	}
	
	/**
	 * M�todo que ira salvar os dados de produtos antes de sair da aplica��o
	 */
	public void salvarDados(){
		try {
				File file = new File( "produtos.bin" );
				FileOutputStream out = new FileOutputStream( file );
				ObjectOutput objectOut = new ObjectOutputStream( out );
				
				//gravando dados dos produtos no arquivo binario
				objectOut.writeInt(this.listaProdutos.size());
				for(Produto produto: this.listaProdutos){
					objectOut.writeObject(produto);
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
