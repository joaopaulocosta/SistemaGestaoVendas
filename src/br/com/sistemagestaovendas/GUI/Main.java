package br.com.sistemagestaovendas.GUI;
import java.awt.EventQueue;



/**
 * Classe inicial da interface gr�fica
 * @author Joao
 *
 */
public class Main {
	
	/**
	 * M�todo est�tico que chama a classe PaginaInicial 
	 * 
	 */
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaInicial pgInicial = new PaginaInicial();
					pgInicial.exibirPagina();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
}
