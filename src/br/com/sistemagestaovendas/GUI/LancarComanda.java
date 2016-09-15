package br.com.sistemagestaovendas.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import br.com.sistemagestaovendas.vendas.Comanda;
import br.com.sistemagestaovendas.vendas.Outros;
import br.com.sistemagestaovendas.vendas.ProdutoComanda;
import br.com.sistemagestaovendas.vendas.Refeicao;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LancarComanda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRefeicao;
	private JTextField txtAgua500;
	private JTextField txtCervejaSkolBrahma;
	private JTextField txtPingaLiguritaCristalina;
	private JTextField txtRefri290;
	private JTextField txtRefri600;
	private JTextField txtRefri1000;
	private JTextField txtRefri1250;
	private JTextField txtRefri2000;
	private JTextField txtAgua1500;
	private JTextField txtOutros;
	private JTextField txtMarmitex;
	private JTextField txtMiniMarmitex;
	private JLabel lblValorTotalReal;
	private JLabel lblAlerta;
	
	private int contRefeicoes = 0;
	private int contOutros = 0;
	private float valorTotal = 0;
	private JTextField txtCervejaOriginal;
	private JTextField txtPingaFarrista;
	
	public LancarComanda(ArrayList<ProdutoComanda> listaProdutosComanda, 
			ArrayList<Comanda> listaComandas) {
		
		Comanda novaComanda = new Comanda(listaProdutosComanda);
		
		setTitle("Lan\u00E7amento de Comanda");
		setBounds(100, 100, 614, 780);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblAlerta = new JLabel("Apenas n\u00FAmeros podem ser digitados, utilize . no lugar de ,");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlerta.setBounds(130, 638, 424, 30);
		lblAlerta.setVisible(false);
		contentPanel.add(lblAlerta);
		
		{
			JLabel labelTitulo = new JLabel("Lan\u00E7amento de Comanda");
			labelTitulo.setBounds(172, 11, 254, 25);
			labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
			contentPanel.add(labelTitulo);
		}
		{
			JLabel lblData = new JLabel("Data:");
			lblData.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblData.setBounds(31, 47, 164, 30);
			contentPanel.add(lblData);
		}
		{
			JLabel lblValorTotal = new JLabel("Valor Total:");
			lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblValorTotal.setBounds(293, 47, 152, 30);
			contentPanel.add(lblValorTotal);
		}
		
		lblValorTotalReal = new JLabel("R$ 0");
		lblValorTotalReal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorTotalReal.setBounds(402, 47, 152, 30);
		contentPanel.add(lblValorTotalReal);
		
		{
			JLabel lblRefeicao = new JLabel("Refei\u00E7\u00E3o:");
			lblRefeicao.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefeicao.setBounds(31, 88, 164, 30);
			contentPanel.add(lblRefeicao);
		}
		
		JButton btnMaisRefeicao = new JButton("+");
		btnMaisRefeicao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtRefeicao.setText("");
				txtRefeicao.requestFocus();
			}
			
		});
		btnMaisRefeicao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtRefeicao.setText("");
				txtRefeicao.requestFocus();
			}
		});
		btnMaisRefeicao.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMaisRefeicao.setBounds(332, 88, 48, 30);
		contentPanel.add(btnMaisRefeicao);
		
		JComboBox cmboxRefeicoes = new JComboBox();
		cmboxRefeicoes.setBounds(484, 88, 104, 28);
		contentPanel.add(cmboxRefeicoes);
	
	
		JLabel lblQuantRefeicoes = new JLabel("Quant: 0");
		lblQuantRefeicoes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantRefeicoes.setBounds(390, 88, 164, 30);
		contentPanel.add(lblQuantRefeicoes);
		
		{
			txtRefeicao = new JTextField();
			txtRefeicao.addFocusListener(new FocusAdapter() {
				//evento para processar refeição
				@Override
				public void focusLost(FocusEvent arg0) {
					ProdutoComanda ref = null;
					for(ProdutoComanda aux: listaProdutosComanda){
						if(aux.getProduto().getNome().equals("Refeição")){
							aux.setQuantidade(aux.getQuantidade() + 1);
							ref = aux;
						}
					}
					if(tratarEntrada(txtRefeicao) && ref != null){
						lblQuantRefeicoes.setText("Quant: " + (++contRefeicoes));
						cmboxRefeicoes.addItem(txtRefeicao.getText());
						valorTotal += Float.parseFloat(txtRefeicao.getText());
						lblValorTotalReal.setText("R$ " + valorTotal);
						//criando nova refeicao
						Refeicao novaRefeicao = new Refeicao(ref.getProduto(), Float.parseFloat(txtRefeicao.getText()) );
						//adicionando refeicao a nova comanda
						novaComanda.addRefeicao(novaRefeicao);
					}
				}
			});
			txtRefeicao.setBounds(179, 88, 146, 28);
			contentPanel.add(txtRefeicao);
			txtRefeicao.setColumns(10);
		}
		
		{
			JLabel lblCerveja = new JLabel("Cerveja: ");
			lblCerveja.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCerveja.setBounds(31, 136, 164, 30);
			contentPanel.add(lblCerveja);
		}
		{
			JLabel lblPinga = new JLabel("Pinga: ");
			lblPinga.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPinga.setBounds(31, 177, 164, 30);
			contentPanel.add(lblPinga);
		}
		{
			JLabel lblRefri290ml = new JLabel("Refri 290ml: ");
			lblRefri290ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefri290ml.setBounds(31, 218, 164, 30);
			contentPanel.add(lblRefri290ml);
		}
		{
			JLabel lblRefri600ml = new JLabel("Refri 600ml: ");
			lblRefri600ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefri600ml.setBounds(31, 259, 164, 30);
			contentPanel.add(lblRefri600ml);
		}
		{
			JLabel lblRefri1000ml = new JLabel("Refri 1000ml: ");
			lblRefri1000ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefri1000ml.setBounds(31, 302, 164, 30);
			contentPanel.add(lblRefri1000ml);
		}
		{
			JLabel lblRefri1250ml = new JLabel("Refri 1250ml: ");
			lblRefri1250ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefri1250ml.setBounds(31, 343, 164, 30);
			contentPanel.add(lblRefri1250ml);
		}
		{
			JLabel lblRefri2000ml = new JLabel("Refri 2000ml: ");
			lblRefri2000ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefri2000ml.setBounds(31, 384, 164, 30);
			contentPanel.add(lblRefri2000ml);
		}
		{
			JLabel lblAguaMin500ml = new JLabel("Agua M. 500ml: ");
			lblAguaMin500ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAguaMin500ml.setBounds(31, 425, 164, 30);
			contentPanel.add(lblAguaMin500ml);
		}
		{
			JLabel lblAguaMin1500ml = new JLabel("Agua M. 1500ml: ");
			lblAguaMin1500ml.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAguaMin1500ml.setBounds(31, 466, 164, 30);
			contentPanel.add(lblAguaMin1500ml);
		}
		{
			JLabel lblOutros = new JLabel("Outros:");
			lblOutros.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblOutros.setBounds(31, 597, 164, 30);
			contentPanel.add(lblOutros);
		}
		{
			txtAgua500 = new JTextField();
			txtAgua500.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Agua M. 500ml", txtAgua500, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Agua M. 500ml", txtAgua500, true);
				}
			});
			txtAgua500.setColumns(10);
			txtAgua500.setBounds(179, 429, 146, 28);
			contentPanel.add(txtAgua500);
		}
		{
			txtCervejaSkolBrahma = new JTextField();
			txtCervejaSkolBrahma.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					trataEventosFocus(listaProdutosComanda, "Cerveja Skol e Brahma", txtCervejaSkolBrahma, true);		
				}
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Cerveja Skol e Brahma", txtCervejaSkolBrahma, false);
				}
			});
			txtCervejaSkolBrahma.setColumns(10);
			txtCervejaSkolBrahma.setBounds(313, 140, 67, 28);
			contentPanel.add(txtCervejaSkolBrahma);
		}
		{
			txtPingaLiguritaCristalina = new JTextField();
			txtPingaLiguritaCristalina.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Pinga Ligurita e Cristalina", txtPingaLiguritaCristalina, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Pinga Ligurita e Cristalina", txtPingaLiguritaCristalina, true);

				}
			});
			txtPingaLiguritaCristalina.setColumns(10);
			txtPingaLiguritaCristalina.setBounds(313, 181, 67, 28);
			contentPanel.add(txtPingaLiguritaCristalina);
		}
		{
			txtRefri290 = new JTextField();
			txtRefri290.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 290ml", txtRefri290, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 290ml", txtRefri290, true);
				}
			});
			txtRefri290.setColumns(10);
			txtRefri290.setBounds(179, 222, 146, 28);
			contentPanel.add(txtRefri290);
		}
		{
			txtRefri600 = new JTextField();
			txtRefri600.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 600ml", txtRefri600, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 600ml", txtRefri600, true);
				}
			});
			txtRefri600.setColumns(10);
			txtRefri600.setBounds(179, 263, 146, 28);
			contentPanel.add(txtRefri600);
		}
		{
			txtRefri1000 = new JTextField();
			txtRefri1000.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 1000ml", txtRefri1000, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 1000ml", txtRefri1000, true);
				}
			});
			txtRefri1000.setColumns(10);
			txtRefri1000.setBounds(179, 306, 146, 28);
			contentPanel.add(txtRefri1000);
		}
		{
			txtRefri1250 = new JTextField();
			txtRefri1250.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 1250ml", txtRefri1250, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 1250ml", txtRefri1250, true);
				}
			});
			txtRefri1250.setColumns(10);
			txtRefri1250.setBounds(179, 347, 146, 28);
			contentPanel.add(txtRefri1250);
		}
		{
			txtRefri2000 = new JTextField();
			txtRefri2000.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 2000ml", txtRefri2000, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Refrig 2000ml", txtRefri2000, true);
				}
			});
			txtRefri2000.setColumns(10);
			txtRefri2000.setBounds(179, 388, 146, 28);
			contentPanel.add(txtRefri2000);
		}
		{
			txtAgua1500 = new JTextField();
			txtAgua1500.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Agua M. 1500ml", txtAgua1500, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Agua M. 1500ml", txtAgua1500, true);
				}
			});
			txtAgua1500.setColumns(10);
			txtAgua1500.setBounds(179, 470, 146, 28);
			contentPanel.add(txtAgua1500);
		}
		
		JButton btnMaisOutros = new JButton("+");
		btnMaisOutros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtOutros.setText("");
				txtOutros.requestFocus();
			}
		});
		btnMaisOutros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtOutros.setText("");
				txtOutros.requestFocus();
			}
		});
		btnMaisOutros.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMaisOutros.setBounds(332, 601, 48, 30);
		contentPanel.add(btnMaisOutros);
		
		JLabel lblQuantOutros = new JLabel("Quant: 0");
		lblQuantOutros.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantOutros.setBounds(390, 601, 164, 30);
		contentPanel.add(lblQuantOutros);
		
		JComboBox cmboxOutros = new JComboBox();
		cmboxOutros.setBounds(484, 601, 104, 28);
		contentPanel.add(cmboxOutros);
		
		txtOutros = new JTextField();
		txtOutros.addFocusListener(new FocusAdapter() {
			//evento para processar refeição
			@Override
			public void focusLost(FocusEvent arg0) {
				ProdutoComanda out = null;
				for(ProdutoComanda aux: listaProdutosComanda){
					if(aux.getProduto().getNome().equals("Outros")){
						aux.setQuantidade(aux.getQuantidade() + 1);
						out = aux;
					}
				}
				if(tratarEntrada(txtOutros)){
					lblQuantOutros.setText("Quant: " + (++contOutros));
					cmboxOutros.addItem(txtOutros.getText());
					valorTotal += Float.parseFloat(txtOutros.getText());
					lblValorTotalReal.setText("R$ " + valorTotal);
					//criando nova refeicao
					Outros novaDespesa = new Outros( Float.parseFloat(txtOutros.getText()) );
					//adicionando refeicao a nova comanda
					novaComanda.addOutros(novaDespesa);
				}
			}
		});
		txtOutros.setColumns(10);
		txtOutros.setBounds(179, 601, 146, 28);
		contentPanel.add(txtOutros);
		
		{	
			//fechar a janela
			JButton button = new JButton("Retornar");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			button.setBounds(149, 666, 152, 48);
			contentPanel.add(button);
		}
		{
			JButton btRegistrar = new JButton("Registrar");
			btRegistrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					novaComanda.setValorTotal(valorTotal);
					listaComandas.add(novaComanda);
					dispose();
				}
			});
			btRegistrar.setBounds(311, 666, 152, 48);
			contentPanel.add(btRegistrar);
		}
		
		JLabel lblMarmitex = new JLabel("Marmitex:");
		lblMarmitex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarmitex.setBounds(31, 507, 164, 30);
		contentPanel.add(lblMarmitex);
		
		txtMarmitex = new JTextField();
		txtMarmitex.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				trataEventosFocus(listaProdutosComanda, "Marmitex", txtMarmitex, false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				trataEventosFocus(listaProdutosComanda, "Marmitex", txtMarmitex, true);
			}
		});
		txtMarmitex.setColumns(10);
		txtMarmitex.setBounds(179, 511, 146, 28);
		contentPanel.add(txtMarmitex);
		{
			txtMiniMarmitex = new JTextField();
			txtMiniMarmitex.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Mini-Marmitex", txtMiniMarmitex, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Mini-Marmitex", txtMiniMarmitex, true);
				}
			});
			txtMiniMarmitex.setColumns(10);
			txtMiniMarmitex.setBounds(179, 552, 146, 28);
			contentPanel.add(txtMiniMarmitex);
		}
		{
			JLabel lblMiniMarmitex = new JLabel("Mini-Marmitex:");
			lblMiniMarmitex.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblMiniMarmitex.setBounds(31, 548, 164, 30);
			contentPanel.add(lblMiniMarmitex);
		}
		{	

			//pegando data atual e exibindo
			Date data = new Date();	
			JLabel lblDataReal = new JLabel((data.getDate()) + "/" + (data.getMonth()+1) +
					"/" + (data.getYear() + 1900)); 
			
					
			lblDataReal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDataReal.setBounds(95, 47, 164, 30);
			
			contentPanel.add(lblDataReal);
		}
		{
			JLabel lblSkolbrahma = new JLabel("Skol/Brahma:");
			lblSkolbrahma.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSkolbrahma.setBounds(189, 136, 164, 30);
			contentPanel.add(lblSkolbrahma);
		}
		{
			JLabel lblOriginal = new JLabel("Original: ");
			lblOriginal.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblOriginal.setBounds(402, 136, 72, 30);
			contentPanel.add(lblOriginal);
		}
		{
			txtCervejaOriginal = new JTextField();
			txtCervejaOriginal.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Cerveja Original",
							txtCervejaOriginal, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Cerveja Original",
							txtCervejaOriginal, true);
				}
			});
			txtCervejaOriginal.setColumns(10);
			txtCervejaOriginal.setBounds(484, 140, 67, 28);
			contentPanel.add(txtCervejaOriginal);
		}
		{
			JLabel lblPingaLiguritaCristalina = new JLabel("Ligurita/Cristal:");
			lblPingaLiguritaCristalina.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPingaLiguritaCristalina.setBounds(189, 177, 164, 30);
			contentPanel.add(lblPingaLiguritaCristalina);
		}
		{
			JLabel lblPingaFarrista = new JLabel("Farrista: ");
			lblPingaFarrista.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPingaFarrista.setBounds(402, 177, 72, 30);
			contentPanel.add(lblPingaFarrista);
		}
		{
			txtPingaFarrista = new JTextField();
			txtPingaFarrista.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Pinga Farrista",
							txtPingaFarrista, false);
				}
				@Override
				public void focusLost(FocusEvent e) {
					trataEventosFocus(listaProdutosComanda, "Pinga Farrista",
							txtPingaFarrista, true);
				}
			});
			txtPingaFarrista.setColumns(10);
			txtPingaFarrista.setBounds(484, 181, 67, 28);
			contentPanel.add(txtPingaFarrista);
		}
		


		
		
	}
	
	public boolean tratarEntrada(JTextField campo){
		
		String entrada = campo.getText();
		
		//verifica se o campo pode ser convertido em um tipo float
		try{
			
			float x = Float.parseFloat(entrada);
			lblAlerta.setVisible(false);
			return true;
		}catch(NumberFormatException ex){
			if(!entrada.equals("")){
				lblAlerta.setText("Apenas números podem ser digitados, utilize . no lugar de ,");
				lblAlerta.setVisible(true);
				campo.setText("");
				campo.requestFocus();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Método que trata os eventos Focus lost e gained de maneira generalizada
	 * @param listaProdutosComanda	lista de ProdutosComanda	
	 * @param nomeProduto	Nome do produto que tera o text Field tratado
	 * @param campo	text Field que será tratado	
	 * @param flagLost	flag que informa se é um evento lost focus ou gained
	 */
	public void trataEventosFocus(ArrayList<ProdutoComanda> listaProdutosComanda, 
			String nomeProduto, JTextField campo, boolean flagLost){
		
		//trata evento gained focus
		if(!flagLost){
			if(!campo.getText().equals("")){
				for(ProdutoComanda aux: listaProdutosComanda){
					if(aux.getProduto().getNome().equals(nomeProduto)){
							
						valorTotal -= (Integer.parseInt(campo.getText()) 
								* aux.getProduto().getPrecoFixo());
						lblValorTotalReal.setText("R$ " + valorTotal);
						campo.setText("");
						aux.setQuantidade(0); 
					}
				}
			}
		}
		//trata evento lost focus
		else{
			if(tratarEntrada(campo)){
				for(ProdutoComanda aux: listaProdutosComanda){
					if(aux.getProduto().getNome().equals(nomeProduto)){
							valorTotal += (Integer.parseInt(campo.getText()) 
									* aux.getProduto().getPrecoFixo());
							lblValorTotalReal.setText("R$ " + valorTotal);
							aux.setQuantidade(Integer.parseInt(campo.getText()));	//soma quantidade
					}
				}
			}
		}
	}
	
	
}
