package br.com.sistemagestaovendas.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.sistemagestaovendas.vendas.Comanda;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizarComandas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tabela;
	

	public VisualizarComandas(ArrayList<Comanda> listaComandas) {
		setBounds(100, 100, 1077, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		tabela = new JTable();
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setBounds(10, 97, 1041, 323);
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Quant. Ref", "Valor Ref", "Cerveja", "Pinga", "Refri", "Agua", "Marmitex", "Mini", "Outros", "Valor Total"
			}
		));
		
		javax.swing.table.DefaultTableModel
		dtm =
		(javax.swing.table.DefaultTableModel)tabela.getModel();
		dtm.addRow(new Object[]{"Data", "Quant. Ref", "Valor Ref", "Cerveja", "Pinga", "Refri", "Agua", "Marmitex", "Mini", "Outros", "Valor Total"});
		contentPanel.setLayout(null);
		contentPanel.add(tabela);
		
		for(Comanda aux: listaComandas){
			String data = (aux.getData().getDate()) + "/" + (aux.getData().getMonth()+1) +
					"/" + (aux.getData().getYear() + 1900);
			dtm.addRow(new Object[]{ data, 
					aux.getQuantidadeRefeicoes(), 
					aux.somarRefeicoes(),
					aux.getQuantidadeProdutoNome("Cerveja Skol e Brahma") +
						aux.getQuantidadeProdutoNome("Cerveja Original"),
					aux.getQuantidadeProdutoNome("Pinga Farrista") +
						aux.getQuantidadeProdutoNome("Pinga Ligurita e Cristalina"),
					aux.getQuantidadeProdutoNome("Refrig 290ml") +
						aux.getQuantidadeProdutoNome("Refrig 600ml") +
						aux.getQuantidadeProdutoNome("Refrig 1000ml") +
						aux.getQuantidadeProdutoNome("Refrig 1250ml") +
						aux.getQuantidadeProdutoNome("Refrig 2000ml"),
					aux.getQuantidadeProdutoNome("Agua M. 500ml") +
						aux.getQuantidadeProdutoNome("Agua M. 1500ml"),
					aux.getQuantidadeProdutoNome("Marmitex"),
					aux.getQuantidadeProdutoNome("Mini-Marmitex"),
					aux.somarOutros(),
					aux.getValorTotal()
					
			});
			contentPanel.setLayout(null);
			contentPanel.add(tabela);
		} 
		
		{
			JLabel lblVisualizaoDeComandas = new JLabel("Visualiza\u00E7\u00E3o de Comandas");
			lblVisualizaoDeComandas.setBounds(420, 43, 266, 25);
			lblVisualizaoDeComandas.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisualizaoDeComandas.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPanel.add(lblVisualizaoDeComandas);
		}
		
		JButton button = new JButton("Retornar");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		button.setBounds(459, 525, 152, 48);
		contentPanel.add(button);
		
		
	}
}
