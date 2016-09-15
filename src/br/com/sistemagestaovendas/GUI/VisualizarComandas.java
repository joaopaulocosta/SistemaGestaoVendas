package br.com.sistemagestaovendas.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.com.sistemagestaovendas.vendas.Comanda;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Canvas;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VisualizarComandas extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabela;
	private JTextField txtData1;
	private JTextField txtData2;
	private Date data1,data2;
	private DefaultTableModel dtm;
	
	private JLabel lblTotal = new JLabel("Total:");;
	private JLabel lblTotalQuantRef = new JLabel();
	private JLabel lblTotalValorRef = new JLabel(); 
	private JLabel lblTotalCerveja = new JLabel();
	private JLabel lblTotalPinga = new JLabel();
	private JLabel lblTotalRefri = new JLabel();
	private JLabel lblTotalAgua = new JLabel();
	private JLabel lblTotalMarmitex = new JLabel();
	private JLabel lblTotalMini = new JLabel();
	private JLabel lblTotalOutros = new JLabel();
	private JLabel lblValorTotal = new JLabel();
	
	public VisualizarComandas(ArrayList<Comanda> listaComandas) {
		setBounds(100, 100, 1077, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		tabela = new JTable();
		tabela.setRowSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setColumnSelectionAllowed(true);
		tabela.setCellSelectionEnabled(true);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setBounds(10, 97, 1041, 321);
		
		
		tabela.setModel(new DefaultTableModel(
			new String[][] {
				
			},
			new String[] {
				"Data", "Quant. Ref", "Valor Ref", "Cerveja", "Pinga", "Refri", "Agua", "Marmitex", "Mini", "Outros", "Valor Total"
			}
		));
		
		dtm = (javax.swing.table.DefaultTableModel)tabela.getModel();
		
		//classe que restringe variáveis float com apenas duas casas decimais
		DecimalFormat df = new DecimalFormat();
		
		
		for(Comanda aux: listaComandas){
			String data = (aux.getData().getDate()) + "/" + (aux.getData().getMonth()+1) +
					"/" + (aux.getData().getYear() + 1900);
			dtm.addRow(new Object[]{ data, 
					aux.getQuantidadeRefeicoes(), 
					df.format(aux.somarRefeicoes()),
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
					df.format(aux.somarOutros()),
					df.format(aux.getValorTotal())
					
			});
			
		} 
		
		
		
		contentPanel.setLayout(null);
		contentPanel.add(tabela); 
		
		calcularTotal(listaComandas);
		
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
		button.setBounds(359, 527, 152, 48);
		contentPanel.add(button);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 74, 1041, 332);
		contentPanel.add(scrollPane);
		

		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		verticalBox.setBounds(10, 414, 1041, 28);
		contentPanel.add(verticalBox);
		
		JLabel lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFiltrar.setBounds(661, 470, 74, 25);
		contentPanel.add(lblFiltrar);
		
		JLabel lblAlerta = new JLabel("Formato de data: 15/09/2016");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlerta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlerta.setBounds(762, 502, 252, 25);
		lblAlerta.setVisible(false);
		contentPanel.add(lblAlerta);
		
		
		
		txtData1 = new JTextField();
		txtData1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtData1.getText().equals("")){
					try{
						String [] aux = txtData1.getText().split("/");
						data1 = new Date(aux[1] + "/" + aux[0] + "/" + aux[2]);
						lblAlerta.setVisible(false);
					}catch(Exception e){
						lblAlerta.setText("Formato de data: 15/09/2016");
						lblAlerta.setVisible(true);
						txtData1.setText("");
						txtData1.requestFocus();
					}
					
				}else{
					lblAlerta.setVisible(false);
				}
			}
		});
		txtData1.setBounds(775, 470, 104, 26);
		contentPanel.add(txtData1);
		txtData1.setColumns(10);
		
		JLabel label = new JLabel("\u00E0");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(889, 470, 18, 25);
		contentPanel.add(label);
		
		txtData2 = new JTextField();
		txtData2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtData2.getText().equals("")){
					try{
						String [] aux = txtData2.getText().split("/");
						data2 = new Date(aux[1] + "/" + aux[0] + "/" + aux[2]);
						lblAlerta.setVisible(false);
						if(data1.after(data2)){
							lblAlerta.setText("Data inicial maior que data final");
							lblAlerta.setVisible(true);
							txtData2.setText("");
							txtData2.requestFocus();
						}
					}catch(Exception e){
						lblAlerta.setText("Formato de data: 15/09/2016");
						lblAlerta.setVisible(true);
						txtData2.setText("");
						txtData2.requestFocus();
					}
					
				}else{
					lblAlerta.setVisible(false);
				}
			}
		});
		txtData2.setColumns(10);
		txtData2.setBounds(910, 470, 104, 26);
		contentPanel.add(txtData2);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDe.setBounds(738, 470, 27, 25);
		contentPanel.add(lblDe);
		
		JButton btnNovaVisualizao = new JButton("Nova Visualiza\u00E7\u00E3o");
		btnNovaVisualizao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				filtrar(listaComandas);
			}
		});
		btnNovaVisualizao.addFocusListener(new FocusAdapter() {
		});
		btnNovaVisualizao.setBounds(521, 527, 152, 48);
		contentPanel.add(btnNovaVisualizao);
		
		
		
		
	}
	
	public void calcularTotal( ArrayList<Comanda> listaComandas ){
		int contQuantRef = 0;
		float contValorRef = 0;
		int contCerv = 0;
		int contPinga = 0;
		int contRefri = 0;
		int contAgua = 0;
		int contMarmitex = 0;
		int contMini = 0;
		float contOutros = 0;
		float contValorTotal = 0;
		
		
		
		for(Comanda aux: listaComandas){
			contQuantRef += aux.getQuantidadeRefeicoes();
			contValorRef += aux.somarRefeicoes();
			contCerv += aux.getQuantidadeProdutoNome("Cerveja Skol e Brahma") + 
					aux.getQuantidadeProdutoNome("Cerveja Original");
			contPinga += aux.getQuantidadeProdutoNome("Pinga Farrista") + 
					aux.getQuantidadeProdutoNome("Pinga Ligurita e Cristalina");
			contRefri += aux.getQuantidadeProdutoNome("Refrig 290ml") +
					aux.getQuantidadeProdutoNome("Refrig 600ml") +
					aux.getQuantidadeProdutoNome("Refrig 1000ml") +
					aux.getQuantidadeProdutoNome("Refrig 1250ml") +
					aux.getQuantidadeProdutoNome("Refrig 2000ml");
			contAgua += aux.getQuantidadeProdutoNome("Agua M. 500ml") +
					aux.getQuantidadeProdutoNome("Agua M. 1500ml");
			contMarmitex += aux.getQuantidadeProdutoNome("Marmitex");
			contMini += aux.getQuantidadeProdutoNome("Mini-Marmitex");
			contOutros += aux.somarOutros();
			contValorTotal += aux.getValorTotal();
		}
		
		//classe que restringe variáveis float com apenas duas casas decimais
		DecimalFormat df = new DecimalFormat();
		
		
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(20, 414, 74, 25);
		contentPanel.add(lblTotal);
		

		lblTotalQuantRef.setText(String.valueOf(contQuantRef));
		lblTotalQuantRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalQuantRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQuantRef.setBounds(104, 417, 92, 25);
		contentPanel.add(lblTotalQuantRef);
		
		
		lblTotalValorRef.setText(String.valueOf(df.format(contValorRef)));
		lblTotalValorRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalValorRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalValorRef.setBounds(200, 417, 92, 25);
		contentPanel.add(lblTotalValorRef);
		

		
		lblTotalCerveja.setText(String.valueOf(contCerv));
		lblTotalCerveja.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCerveja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCerveja.setBounds(290, 417, 98, 25);
		contentPanel.add(lblTotalCerveja);
		
		
		lblTotalPinga.setText(String.valueOf(contPinga));
		lblTotalPinga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPinga.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalPinga.setBounds(389, 417, 92, 25);
		contentPanel.add(lblTotalPinga);
		
		
		lblTotalRefri.setText(String.valueOf(contRefri));
		lblTotalRefri.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRefri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalRefri.setBounds(480, 417, 98, 25);
		contentPanel.add(lblTotalRefri);
		
		
		lblTotalAgua.setText(String.valueOf(contAgua));
		lblTotalAgua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAgua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalAgua.setBounds(575, 417, 98, 25);
		contentPanel.add(lblTotalAgua);
		
		
		lblTotalMarmitex.setText(String.valueOf(contMarmitex));
		lblTotalMarmitex.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMarmitex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalMarmitex.setBounds(668, 417, 98, 25);
		contentPanel.add(lblTotalMarmitex);
		
		
		lblTotalMini.setText(String.valueOf(contMini));
		lblTotalMini.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMini.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalMini.setBounds(762, 417, 98, 25);
		contentPanel.add(lblTotalMini);
		
		
		lblTotalOutros.setText(String.valueOf(df.format(contOutros)));
		lblTotalOutros.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOutros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalOutros.setBounds(858, 417, 98, 25);
		contentPanel.add(lblTotalOutros);
		
	
		lblValorTotal.setText(String.valueOf(df.format(contValorTotal)));
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorTotal.setBounds(955, 417, 96, 25);
		contentPanel.add(lblValorTotal); 
		
	}
	
	public void filtrar(ArrayList<Comanda> listaComandas){
		ArrayList<Comanda> novaLista = new ArrayList<Comanda>();
		if(!txtData1.equals("") && !txtData2.equals("")){
			for(int i = tabela.getRowCount() - 1; i>=0 ; i--)
				dtm.removeRow(i);
			
			//classe que restringe variáveis float com apenas duas casas decimais
			DecimalFormat df = new DecimalFormat();
			
			for(Comanda aux: listaComandas){
				String data = (aux.getData().getMonth()+1) + "/" + (aux.getData().getDate()) +
						"/" + (aux.getData().getYear() + 1900);
				
				Date dataMeio = new Date(data);
				
				if(dataMeio.after(data1) && dataMeio.before(data2) || dataMeio.equals(data1) || dataMeio.equals(data2)){	
					novaLista.add(aux);
					dtm.addRow(new Object[]{  (aux.getData().getDate()) + "/" + (aux.getData().getMonth()+1) +
							"/" + (aux.getData().getYear() + 1900), 
							aux.getQuantidadeRefeicoes(), 
							df.format(aux.somarRefeicoes()),
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
							df.format(aux.somarOutros()),
							df.format(aux.getValorTotal())
							
					});
			
				}
			}
			System.out.println(novaLista.size());
			calcularTotal(novaLista);
		}
	}
}
