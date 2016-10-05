package br.com.sistemagestaovendas.GUI;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import br.com.sistemagestaovendas.vendas.Comanda;



/**
 * Classe inicial da interface gráfica
 * @author Joao
 *
 */
public class Main {
	
	/**
	 * Método estático que chama a classe PaginaInicial 
	 * 
	 */
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(!verificaProgramaExecutando()){
					System.exit(0);
				}else{
					criarArquivoDeExecucao();
				}
				try {
					PaginaInicial pgInicial = new PaginaInicial();
					pgInicial.exibirPagina();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	public static boolean verificaProgramaExecutando(){
		try {
			File file = new File( "executando.bin" );
			FileInputStream in = new FileInputStream( file );
			ObjectInput objectIn = new ObjectInputStream( in );
			objectIn. close();
			in. close();
			return false;
			
		} catch (FileNotFoundException e) {
			return true;
			
		}  catch (Exception e) {
			System.out.println( "Erro ao ler arquivo. " + e.getMessage() );
		}
		return false;
	}
	
	public static void criarArquivoDeExecucao(){
		try {
			File file = new File("executando.bin" );
			FileOutputStream out = new FileOutputStream( file );
			ObjectOutput objectOut = new ObjectOutputStream( out );
			objectOut. close();
			out. close();
		} catch (Exception e) {
			System.out.println( "Erro ao gravar arquivo. " + e.getMessage() );
		}
	}
}
