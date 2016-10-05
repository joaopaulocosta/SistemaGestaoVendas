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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe que possibilita a visualização de todas as comandas, com total de vendas para 
 * cada produto, também foi implementado um filtro onde pode-se analisar comandas entre
 * um dado intervalo de datas
 * 
 * @author Joao
 *
 */
public class VisualizarComandas extends JDialog {
	
	//componentes visuais
	private final JPanel contentPanel = new JPanel();
	private JTable tabela;
	private JTextField txtData1;
	private JTextField txtData2;
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
	JLabel lblNComandasReal = new JLabel();
	
	//datas que serão usadas no filtro
	private Date data1,data2;
	
	/**
	 * Método construtor onde são criados os componentes, incluindo a tabela e suas linhas
	 * onde cada linha representa uma comanda
	 * @param listaComandas lista com as comandas que serão exibidas na tabela
	 */
	public VisualizarComandas(ArrayList<Comanda> listaComandas) {
		setBounds(200, 50, 1077, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		//criando tabela
		tabela = new JTable();
		tabela.setRowSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setColumnSelectionAllowed(true);
		tabela.setCellSelectionEnabled(true);
		tabela.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabela.setBounds(10, 97, 1041, 321);
		
		//incluindo cabeçalho
		tabela.setModel(new DefaultTableModel(
			new String[][] {
				
			},
			new String[] {
				"Data", "Quant. Ref", "Valor Ref", "Cerveja", "Pinga", "Refri", "Agua", "Marmitex", "Mini", "Outros", "Valor Total"
			}
		));
		
		//criando modelo
		dtm = (javax.swing.table.DefaultTableModel)tabela.getModel();
		
		//classe que restringe variáveis float com apenas duas casas decimais
		DecimalFormat df = new DecimalFormat();
		
		//adicionando as linhas
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
		
		//calculando total dos itens consumidos
		calcularTotal(listaComandas);
		
		{
			JLabel lblVisualizaoDeComandas = new JLabel("Visualiza\u00E7\u00E3o de Comandas");
			lblVisualizaoDeComandas.setBounds(420, 43, 266, 25);
			lblVisualizaoDeComandas.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisualizaoDeComandas.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPanel.add(lblVisualizaoDeComandas);
		}
		
		//Botão retornar junto com seus eventos
		JButton button = new JButton("Retornar");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					dispose();
				}
			}
		});
		button.setToolTipText("Retornar a p\u00E1gina inicial");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		button.setBounds(113, 527, 152, 48);
		contentPanel.add(button);
		
		//inserindo barra de rolagem vertical na tabela
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
		
		//alerta usado no tratamento das datas
		JLabel lblAlerta = new JLabel("Formato de data: 15/09/2016");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlerta.setBounds(668, 502, 346, 25);
		lblAlerta.setVisible(false);
		contentPanel.add(lblAlerta);
		
		
		//campo que receberá primeira data limitante junto com seus eventos
		txtData1 = new JTextField();
		txtData1.setToolTipText("Data inicial a ser filtrada");
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
		
		//campo que receberá a segunda data limitante junto com seus eventos
		txtData2 = new JTextField();
		txtData2.setToolTipText("Data final a ser filtrada");
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
		
		//Botão para iniciar filtragem junto com eventos
		JButton btnNovaVisualizao = new JButton("Nova Visualiza\u00E7\u00E3o");
		btnNovaVisualizao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					if(txtData1.getText().equals("") || txtData2.getText().equals("")){
						lblAlerta.setText("Insira duas datas limitantes");
						lblAlerta.setVisible(true);
					}
					else{
						filtrar(listaComandas);
					}
					
				}
			}
		});
		btnNovaVisualizao.setToolTipText("Nova tabela a partir de datas limitantes");
		btnNovaVisualizao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtData1.getText().equals("") || txtData2.getText().equals("")){
					lblAlerta.setText("Insira duas datas limitantes");
					lblAlerta.setVisible(true);
				}
				else{
					filtrar(listaComandas);
				}
			}
		});
		btnNovaVisualizao.addFocusListener(new FocusAdapter() {
		});
		btnNovaVisualizao.setBounds(804, 527, 152, 48);
		contentPanel.add(btnNovaVisualizao);
		
		JLabel lblNComandas = new JLabel("N\u00B0 de Comandas an\u00E1lisadas: ");
		lblNComandas.setHorizontalAlignment(SwingConstants.LEFT);
		lblNComandas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNComandas.setBounds(20, 478, 193, 25);
		contentPanel.add(lblNComandas);
		
		
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtData1, txtData2, btnNovaVisualizao, button}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtData1, txtData2, btnNovaVisualizao, button}));
		
		
		
		
	}
	
	/**
	 * Método que soma os itens vendidos entre todas as comandas passadas por paramêtro,
	 * e exibe para cada label abaixo da tabela sua respectiva soma 
	 * @param listaComandas lista com comandas que terão despesas somadas
	 */
	public void calcularTotal( ArrayList<Comanda> listaComandas ){
		//contadores
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
		
		
		//para cada comanda da lista os produtos são somados
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
		
		//exibindo os resultados
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(20, 414, 74, 25);
		contentPanel.add(lblTotal);
		lblTotalQuantRef.setToolTipText("Quantidade de Refei\u00E7\u00F5es servidas");
		

		lblTotalQuantRef.setText(String.valueOf(contQuantRef));
		lblTotalQuantRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalQuantRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQuantRef.setBounds(104, 417, 92, 25);
		contentPanel.add(lblTotalQuantRef);
		lblTotalValorRef.setToolTipText("Valor de todas as refei\u00E7\u00F5es servidas");
		
		
		lblTotalValorRef.setText("R$ " + String.valueOf(df.format(contValorRef)));
		lblTotalValorRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalValorRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalValorRef.setBounds(200, 417, 92, 25);
		contentPanel.add(lblTotalValorRef);
		lblTotalCerveja.setToolTipText("Total de cervejas vendidas");
		

		
		lblTotalCerveja.setText(String.valueOf(contCerv));
		lblTotalCerveja.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCerveja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCerveja.setBounds(290, 417, 98, 25);
		contentPanel.add(lblTotalCerveja);
		lblTotalPinga.setToolTipText("Total de doses de pinga vendidas");
		
		
		lblTotalPinga.setText(String.valueOf(contPinga));
		lblTotalPinga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPinga.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalPinga.setBounds(389, 417, 92, 25);
		contentPanel.add(lblTotalPinga);
		lblTotalRefri.setToolTipText("Total de Refrigerantes vendidos");
		
		
		lblTotalRefri.setText(String.valueOf(contRefri));
		lblTotalRefri.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRefri.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalRefri.setBounds(480, 417, 98, 25);
		contentPanel.add(lblTotalRefri);
		lblTotalAgua.setToolTipText("Total de \u00E1guas vendidas");
		
		
		lblTotalAgua.setText(String.valueOf(contAgua));
		lblTotalAgua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAgua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalAgua.setBounds(575, 417, 98, 25);
		contentPanel.add(lblTotalAgua);
		lblTotalMarmitex.setToolTipText("Total de marmitex vendidas");
		
		
		lblTotalMarmitex.setText(String.valueOf(contMarmitex));
		lblTotalMarmitex.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMarmitex.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalMarmitex.setBounds(668, 417, 98, 25);
		contentPanel.add(lblTotalMarmitex);
		lblTotalMini.setToolTipText("Total de mini-marmitex vendidas");
		
		
		lblTotalMini.setText(String.valueOf(contMini));
		lblTotalMini.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMini.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalMini.setBounds(762, 417, 98, 25);
		contentPanel.add(lblTotalMini);
		lblTotalOutros.setToolTipText("Valor total em vendas de produtos n\u00E3o cadastrados na comanda");
		
		
		lblTotalOutros.setText("R$ " + String.valueOf(df.format(contOutros)));
		lblTotalOutros.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOutros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalOutros.setBounds(858, 417, 98, 25);
		contentPanel.add(lblTotalOutros);
		lblValorTotal.setToolTipText("Valor total de vendas");
		
	
		lblValorTotal.setText("R$ " + String.valueOf(df.format(contValorTotal)));
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorTotal.setBounds(955, 417, 96, 25);
		contentPanel.add(lblValorTotal); 
		
		lblNComandasReal.setText(String.valueOf(listaComandas.size()));
		lblNComandasReal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNComandasReal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNComandasReal.setBounds(223, 478, 193, 25);
		contentPanel.add(lblNComandasReal);
		
	}
	
	/**
	 * Método que filtra as linhas da tabela de acordo com intervalo informado pelos dois 
	 * campos de data
	 * @param listaComandas
	 */
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
			//novo calculo é feito a partir da nova lista de comandas filtrada
			calcularTotal(novaLista);
		}
	}
}
