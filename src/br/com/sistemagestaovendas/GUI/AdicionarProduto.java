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

public class AdicionarProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtPreco;

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
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setColumns(10);
		txtNome.setBounds(159, 94, 308, 39);
		contentPanel.add(txtNome);
		
		JLabel label_2 = new JLabel("Pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(93, 176, 58, 14);
		contentPanel.add(label_2);
		
		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPreco.setColumns(10);
		txtPreco.setBounds(159, 166, 308, 39);
		contentPanel.add(txtPreco);
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				dispose();	//fechando janela
			}
		});
		btnRetornar.setBounds(120, 281, 152, 48);
		contentPanel.add(btnRetornar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Produto novoProduto = new Produto(txtNome.getText(),Float.parseFloat(txtPreco.getText()));
				listaProdutos.add(novoProduto);
				dispose();
			}
		});
		btnAdicionar.setBounds(282, 281, 152, 48);
		contentPanel.add(btnAdicionar);
	}

}
