package br.com.sistemagestaovendas.GUI;

import br.com.sistemagestaovendas.vendas.Produto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe que armazena o nome e o pre�o dos produtos que ser�o
 * tratados na comanda
 * @author Joao
 *
 */
public class AdicionarProduto extends JDialog {
	
	//componentes visuais
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtPreco;
	
	/**
	 * Construtor que recebe uma lista onde ser�o inseridos os novos produtos,
	 * os dados s�o inseridos nos campos, as entradas s�o tratadas, o objeto
	 * � criado e adicionado a lista
	 * @param listaProdutos	lista onde ser� adicionada o novo produto
	 */
	public AdicionarProduto(ArrayList<Produto> listaProdutos) {
		setTitle("Adicionar Produto \u00E0 Comanda");
		setBounds(100, 100, 553, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Adicionar Produto");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(-15, 11, 587, 25);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(93, 104, 58, 14);
		contentPanel.add(label_1);
		
		//campo nome
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setColumns(10);
		txtNome.setBounds(159, 94, 308, 39);
		contentPanel.add(txtNome);
		
		JLabel label_2 = new JLabel("Pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(93, 176, 58, 14);
		contentPanel.add(label_2);
		
		//campo pre�o
		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPreco.setColumns(10);
		txtPreco.setBounds(159, 166, 308, 39);
		contentPanel.add(txtPreco);
		
		//bot�o retornar a p�gina inicial e seus eventos
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					dispose();	//fechando janela
				}
			}
		});
		btnRetornar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				dispose();	//fechando janela
			}
		});
		btnRetornar.setBounds(93, 281, 152, 48);
		contentPanel.add(btnRetornar);
		
		//label para alerta caso haja alguma valor fora do padr�o
		JLabel lblAlerta = new JLabel("Os valores informados n\u00E3o podem estar em branco");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setBounds(120, 232, 347, 14);
		lblAlerta.setVisible(false);
		contentPanel.add(lblAlerta);
		
		//botao adicionar novo produto e seus eventos
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					if(verificaCampo(txtNome.getText(), txtPreco.getText(), lblAlerta)){
						Produto novoProduto = new Produto(txtNome.getText(),Float.parseFloat(txtPreco.getText()));
						listaProdutos.add(novoProduto);
						dispose();
					}
				}
			}
		});
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(verificaCampo(txtNome.getText(), txtPreco.getText(), lblAlerta)){
					Produto novoProduto = new Produto(txtNome.getText(),Float.parseFloat(txtPreco.getText()));
					listaProdutos.add(novoProduto);
					dispose();
				}
			}
		});
		btnAdicionar.setBounds(315, 281, 152, 48);
		contentPanel.add(btnAdicionar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNome, txtPreco, btnAdicionar, btnRetornar}));
		
		
	}
	
	/**
	 * M�todo que faz o tratamento das informa��es que ser�o inseridas
	 * @param nome	Produto selecionado no combobox
	 * @param preco	Pre�o que ser� atualizado
	 * @param lblAlerta	Label onde ser� exibida a mensagem de erro caso necess�rio
	 * @return True caso padr�o esteja correto e False caso n�o
	 */
	public boolean verificaCampo(String nome, String preco,JLabel lblAlerta){
		//verifica se o campo est� em branco
		if(nome.equals("") || preco.equals("")){
			lblAlerta.setText("Os valores informados n�o podem estar em branco");
			lblAlerta.setVisible(true);
			return false;
		}
		
		//verifica se o campo pode ser convertido em um tipo float
		try{
			float x = Float.parseFloat(preco);
		}catch(NumberFormatException ex){
			lblAlerta.setText("Apenas n�meros podem ser digitados, utilize . no lugar de ,");
			lblAlerta.setVisible(true);
		}
		
		return true;
	}

}
