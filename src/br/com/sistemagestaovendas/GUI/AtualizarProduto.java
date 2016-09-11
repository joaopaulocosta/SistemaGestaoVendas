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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AtualizarProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNovoPreco;
	private Produto produtoSelecionado;

	public AtualizarProduto(ArrayList<Produto> listaProdutos) {
		setTitle("Atualizar Pre\u00E7o de Produto");
		setBounds(100, 100, 599, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Atualizar Pre\u00E7o");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(0, 23, 587, 25);
		contentPanel.add(label);
		
		txtNovoPreco = new JTextField();
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				for(Produto aux: listaProdutos){
					if(comboBox.getSelectedItem().equals(aux.getNome()))
						produtoSelecionado = aux;
				} 
				txtNovoPreco.setText(String.valueOf(produtoSelecionado.getPrecoFixo()));
			}
		});
		comboBox.setBounds(270, 107, 212, 39);
		
		boolean auxBool = false;
		
		//adicionando os produtos ja lançados no combobox
		for(Produto aux: listaProdutos){
			if(auxBool == false){
				produtoSelecionado = aux;
				auxBool = true;
			}
			comboBox.addItem(aux.getNome());
		}
		contentPanel.add(comboBox);
		
		
		JLabel label_1 = new JLabel("Nome do produto:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(108, 116, 152, 30);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Novo pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(154, 181, 106, 29);
		contentPanel.add(label_2);
		
		if(produtoSelecionado != null)
			//atribui valor ao campo de text de acordo com objeto setado inicianmente no comboBox
			txtNovoPreco = new JTextField(String.valueOf(produtoSelecionado.getPrecoFixo()));

			
		
		txtNovoPreco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNovoPreco.setColumns(10);
		txtNovoPreco.setBounds(270, 178, 212, 39);
		contentPanel.add(txtNovoPreco);
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				dispose();	//fechando janela
			}
		});
		btnRetornar.setBounds(120, 281, 152, 48);
		contentPanel.add(btnRetornar);
		
		JButton btnAtualizar = new JButton("Adicionar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnAtualizar.setBounds(282, 281, 152, 48);
		contentPanel.add(btnAtualizar);
	}

}
