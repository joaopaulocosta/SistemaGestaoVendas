package br.com.sistemagestaovendas.GUI;

import br.com.sistemagestaovendas.BD.DadosComandas;
import br.com.sistemagestaovendas.BD.DadosProdutos;
import br.com.sistemagestaovendas.vendas.*;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**
 * Classe que implementa a interface gráfica da página inicial do sistema,
 * a partir de eventos criados para os botões outras janelas podem ser abertas
 * @author Joao
 *
 */
public class PaginaInicial {
	
	private JFrame frmSistemaDeGestao;	//Frame para tela Inicial
	
	//lista que armazenará os produtos lidos em arquivo
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();	
	
	//lista que armazena os produtos que cada comanda possui
	private ArrayList<ProdutoComanda> listaProdutosComanda = new ArrayList<ProdutoComanda>();
	
	//lista que armazena as comandas criadas pela janela Lançar Comanda
	private ArrayList<Comanda> listaComandas = new ArrayList<Comanda>();
	
	
	/**
	 * Método construtor que chama outros três métodos, criação da janela Página Inicial,
	 *  leitura dos produtos em arquivo e leitura das comandas em arquivo.
	 */
	public PaginaInicial() {
		inicializar();
		DadosProdutos carregarProdutos = new DadosProdutos(this.listaProdutos, "produtos.bin");
		carregarProdutos.carregarDados();
		DadosComandas carregarComandas = new DadosComandas(this.listaComandas, "comandas.bin");
		carregarComandas.carregarDados();
	}

	/**
	 * Método que cria todos os componentes visuais que serão exibidos na tela, e
	 * implementa os eventos necessários para o funcionamento do programa
	 */
	private void inicializar() {
		//Inicializando o frame da tela Inicial
		frmSistemaDeGestao = new JFrame();
		frmSistemaDeGestao.setTitle("Sistema de Gest\u00E3o de Vendas");
		frmSistemaDeGestao.setBounds(400, 100, 601, 503);
		frmSistemaDeGestao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestao.getContentPane().setLayout(null);
		
		//caso a janela seja fechada os dados serão salvos
		frmSistemaDeGestao.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	DadosProdutos salvar = new DadosProdutos(listaProdutos, "produtos.bin");
				salvar.salvarDados();
				
				DadosComandas carregarComandas = new DadosComandas(listaComandas, "comandas.bin");
				carregarComandas.salvarDados();
				destruirArquivoDeExecucao();
				System.exit(0);
		    }
		});
		//Titulo Principal
		JLabel labelTitulo = new JLabel("Sistema de Registro de Vendas");
		labelTitulo.setBounds(-11, 11, 607, 71);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		//Inserindo o ícone da tela
		labelTitulo.setIcon(new ImageIcon("img\\logo.png"));
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		frmSistemaDeGestao.getContentPane().add(labelTitulo);
		
		//Criando botão para janela Lançar Comanda
		JButton btnLancarComanda = new JButton("Lan\u00E7ar Comanda");
		btnLancarComanda.addKeyListener(new KeyAdapter() {	//evento de teclado
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					 gerarListaProdutoComanda();
					LancarComanda lancaComanda = new LancarComanda(novaListaProdutosComanda(),
							listaComandas);	//abri pagina para adicionar produto
					lancaComanda.setVisible(true);
				 }
			}
		});
		btnLancarComanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	//evento de mouse
				gerarListaProdutoComanda();
				LancarComanda lancaComanda = new LancarComanda(novaListaProdutosComanda(),
						listaComandas);	//abri pagina para adicionar produto
				lancaComanda.setVisible(true);
			}
		});
		btnLancarComanda.setBounds(200, 120, 200, 40);
		btnLancarComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmSistemaDeGestao.getContentPane().add(btnLancarComanda);
		
		//Criando botão para janela Visualizar Comandas
		JButton btnVisualizarComandas = new JButton("Visualizar Comandas");
		btnVisualizarComandas.addKeyListener(new KeyAdapter() {	//evento de teclado
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					VisualizarComandas visComandas = new VisualizarComandas(listaComandas);	//abri pagina para visualizar comandas
					visComandas.setVisible(true);
				 }
			}
		});
		btnVisualizarComandas.addMouseListener(new MouseAdapter() {	//evento de mouse
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VisualizarComandas visComandas = new VisualizarComandas(listaComandas);	//abri pagina para visualizar comandas
				visComandas.setVisible(true);
			}
		});
		btnVisualizarComandas.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVisualizarComandas.setBounds(200, 171, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnVisualizarComandas);
		
		//Criando botão para janela AdicionarProduto
		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addKeyListener(new KeyAdapter() {	//evento de teclado
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					 AdicionarProduto adcProduto = new AdicionarProduto(listaProdutos);	//abri pagina para adicionar produto
					 adcProduto.setVisible(true);
				 }
			}
		});
		btnAdicionarProduto.addMouseListener(new MouseAdapter() {	//evento de mouse
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdicionarProduto adcProduto = new AdicionarProduto(listaProdutos);	//abri pagina para adicionar produto
				adcProduto.setVisible(true);
			}
		});
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionarProduto.setBounds(200, 222, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnAdicionarProduto);
		
		//Criando botão para janela Atualizar Preço
		JButton btnAtualizarPreco = new JButton("Atualizar Pre\u00E7o");
		btnAtualizarPreco.addKeyListener(new KeyAdapter() {	//evento de teclado
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					//frmSistemaDeGestao.setVisible(false);	//esconde a pagina inicial
					AtualizarProduto atuaProduto = new AtualizarProduto(listaProdutos);	//abri pagina para atualizar produto
					atuaProduto.setVisible(true);
				 }
			}
		});
			
		btnAtualizarPreco.addMouseListener(new MouseAdapter() {	//evento de mouse
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//frmSistemaDeGestao.setVisible(false);	//esconde a pagina inicial
				AtualizarProduto atuaProduto = new AtualizarProduto(listaProdutos);	//abri pagina para atualizar produto
				atuaProduto.setVisible(true);		
			}
		});
		btnAtualizarPreco.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtualizarPreco.setBounds(200, 273, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnAtualizarPreco);
		
		//criando botão para sair do sistema
		JButton btnSair = new JButton("Sair");
		btnSair.addKeyListener(new KeyAdapter() {	//evento de teclado
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					DadosProdutos salvar = new DadosProdutos(listaProdutos, "produtos.bin");
					salvar.salvarDados();
					
					DadosComandas carregarComandas = new DadosComandas(listaComandas, "comandas.bin");
					carregarComandas.salvarDados();
					System.exit(0);
				 }
			}
		});
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	//evento de mouse
				DadosProdutos salvar = new DadosProdutos(listaProdutos, "produtos.bin");
				salvar.salvarDados();
				
				DadosComandas carregarComandas = new DadosComandas(listaComandas, "comandas.bin");
				carregarComandas.salvarDados();
				destruirArquivoDeExecucao();
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.setBounds(200, 324, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnSair);
		
		
	}
	
	/**
	 * Método que gera, a partir dos objetos Produto lidos em arquivo, uma lista de objetos
	 * ProdutoComanda que são necessários para a criação do objeto comanda. A lista gerada será
	 * um modelo para as novas comandas criadas
	 */
	public void gerarListaProdutoComanda(){
		for(Produto aux :this.listaProdutos){
			ProdutoComanda novoProdutoComanda = new ProdutoComanda(aux.getNome(),aux.getPrecoFixo());
			listaProdutosComanda.add(novoProdutoComanda);
		}
	}
	
	/**
	 * Método que duplica a lista de ProdutoComanda, essa nova lista é necessaria,
	 * pois cada comanda possui valores diferentes destes objetos. 
	 * @return lista de ProdutoComanda necessária para criação de nova comanda
	 */
	public ArrayList<ProdutoComanda> novaListaProdutosComanda(){
		ArrayList<ProdutoComanda> novaLista = new ArrayList<ProdutoComanda>();
		for(Produto aux :this.listaProdutos){
			ProdutoComanda novoProdutoComanda = new ProdutoComanda(aux.getNome(),aux.getPrecoFixo());
			novaLista.add(novoProdutoComanda);
		}
		
		return novaLista;
	}
	
	/**
	 *Método que exibe a Página Inicial na tela 
	 */
	public void exibirPagina(){
		this.frmSistemaDeGestao.setVisible(true);
	}
	
	
	public void destruirArquivoDeExecucao(){
		try {
			File file = new File("executando.bin" );
			FileOutputStream out = new FileOutputStream( file );
			ObjectOutput objectOut = new ObjectOutputStream( out );
			objectOut. close();
			out. close();
			file.delete();
		} catch (Exception e) {
			System.out.println( "Erro ao deletar arquivo. " + e.getMessage() );
		}
	}
}
