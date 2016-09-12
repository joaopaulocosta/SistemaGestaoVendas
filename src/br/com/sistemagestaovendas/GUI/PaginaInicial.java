package br.com.sistemagestaovendas.GUI;

import br.com.sistemagestaovendas.BD.DadosProdutos;
import br.com.sistemagestaovendas.vendas.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PaginaInicial {
	
	private JFrame frmSistemaDeGestao;
	//lista que armazenará os produtos criados pela janela adicionar produtos
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();	
	
	private ArrayList<ProdutoComanda> listaProdutosComanda = new ArrayList<ProdutoComanda>();
	
	private ArrayList<Comanda> listaComandas = new ArrayList<Comanda>();
	
	
	/**
	 * Create the application.
	 */
	public PaginaInicial() {
		initialize();
		DadosProdutos carregar = new DadosProdutos(this.listaProdutos);
		carregar.carregarDados();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeGestao = new JFrame();
		frmSistemaDeGestao.setTitle("Sistema de Gest\u00E3o de Vendas");
		frmSistemaDeGestao.setBounds(100, 100, 601, 503);
		frmSistemaDeGestao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestao.getContentPane().setLayout(null);
		
		JLabel labelTitulo = new JLabel("Sistema de Gest\u00E3o de Vendas");
		labelTitulo.setBounds(-11, 11, 607, 71);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setIcon(new ImageIcon("D:\\documentos\\UFLA\\6 per\u00EDodo\\JavaGroup\\SitemaGestaoVendas\\img\\logo.png"));
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		frmSistemaDeGestao.getContentPane().add(labelTitulo);
		
		JButton btnLancarComanda = new JButton("Lan\u00E7ar Comanda");
		btnLancarComanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LancarComanda lancaComanda = new LancarComanda();	//abri pagina para adicionar produto
				lancaComanda.setVisible(true);
			}
		});
		btnLancarComanda.setBounds(200, 120, 200, 40);
		btnLancarComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmSistemaDeGestao.getContentPane().add(btnLancarComanda);
		
		JButton btnGerarRelatrio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatrio.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGerarRelatrio.setBounds(200, 171, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnGerarRelatrio);
		
		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdicionarProduto adcProduto = new AdicionarProduto(listaProdutos);	//abri pagina para adicionar produto
				adcProduto.setVisible(true);
			}
		});
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionarProduto.setBounds(200, 222, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnAdicionarProduto);
		
		JButton btnAtualizarPreco = new JButton("Atualizar Pre\u00E7o");
		btnAtualizarPreco.addMouseListener(new MouseAdapter() {
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
		
		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DadosProdutos salvar = new DadosProdutos(listaProdutos);
				salvar.salvarDados();
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.setBounds(200, 324, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnSair);
		
		JButton btnVisualizarComanda = new JButton("Visualizar Comanda");
		btnVisualizarComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVisualizarComanda.setBounds(200, 222, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnVisualizarComanda);
	}
	
	public void gerarListaProdutoComanda(){
		for(Produto aux :this.listaProdutos){
			ProdutoComanda novoProdutoComanda = new ProdutoComanda(aux);
			listaProdutosComanda.add(novoProdutoComanda);
		}
	}
	
	public void exibirPagina(){
		this.frmSistemaDeGestao.setVisible(true);
	}
}
