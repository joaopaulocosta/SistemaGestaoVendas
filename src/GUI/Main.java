package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Window.Type;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private JFrame frmSistemaDeGestao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmSistemaDeGestao.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
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
				AdicionarProduto adcProduto = new AdicionarProduto();	//abri pagina para adicionar produto
				adcProduto.setVisible(true);
			}
		});
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionarProduto.setBounds(200, 273, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnAdicionarProduto);
		
		JButton btnAtualizarPreco = new JButton("Atualizar Pre\u00E7o");
		btnAtualizarPreco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//frmSistemaDeGestao.setVisible(false);	//esconde a pagina inicial
				AtualizarProduto atuaProduto = new AtualizarProduto();	//abri pagina para atualizar produto
				atuaProduto.setVisible(true);		
			}
		});
		btnAtualizarPreco.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtualizarPreco.setBounds(200, 324, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnAtualizarPreco);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.setBounds(200, 375, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnSair);
		
		JButton btnVisualizarComanda = new JButton("Visualizar Comanda");
		btnVisualizarComanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVisualizarComanda.setBounds(200, 222, 200, 40);
		frmSistemaDeGestao.getContentPane().add(btnVisualizarComanda);
	}
}
