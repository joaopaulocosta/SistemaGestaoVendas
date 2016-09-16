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
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Classe que possui os componentes gráficos que são responsáveis por realizar atualizações
 *  de preços sobre os produtos que estão na comanda
 * @author Joao
 *
 */
public class AtualizarProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNovoPreco;	//campo que recebe o novo preço
	private Produto produtoSelecionado;	//produto que será selecionado
	
	/**
	 * Método que cria os componentes gráficos para comunicação com o usuário e também
	 * os eventos que serão executados quando for selecionado um produto e seu preço
	 * for alterado
	 * @param listaProdutos lista onde esta inserido o produto que será alterado
	 */
	public AtualizarProduto(ArrayList<Produto> listaProdutos) {
		//criando a janela de comunicação
		setTitle("Atualizar Pre\u00E7o de Produto");
		setBounds(100, 100, 599, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Label principal da janela
		JLabel label = new JLabel("Atualizar Pre\u00E7o");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(0, 23, 587, 25);
		contentPanel.add(label);
		
		//campo que receberá o novo preço
		txtNovoPreco = new JTextField();
		
		//combobox que listará todos os produtos das comandas
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			/**	caso algum produto do combobox seja selecionado o preço atual será exibido 
			*	no text field Novo Preço */
			public void itemStateChanged(ItemEvent arg0) {
				for(Produto aux: listaProdutos){
					if(comboBox.getSelectedItem().equals(aux.getNome()))
						produtoSelecionado = aux;
				} 
				txtNovoPreco.setText(String.valueOf(produtoSelecionado.getPrecoFixo()));
			}
		});
		comboBox.setBounds(270, 107, 212, 39);
		
		//variável usada para selecionar o primeiro valor do combobox
		boolean auxBool = false;
		
		//adicionando os produtos no combobox
		for(Produto aux: listaProdutos){
			if(auxBool == false){
				produtoSelecionado = aux;
				auxBool = true;
			}
			comboBox.addItem(aux.getNome());
		}
		contentPanel.add(comboBox);
		
		//label Nome do produto
		JLabel label_1 = new JLabel("Nome do produto:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(108, 116, 152, 30);
		contentPanel.add(label_1);
		
		//label Novo preço
		JLabel label_2 = new JLabel("Novo pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(154, 181, 106, 29);
		contentPanel.add(label_2);
		
		/** caso haja produtos cadastrados no text field Novo Preço será exibido o preço do primeiro
		* 	valor do combobox */
		if(produtoSelecionado != null)
			//atribui valor ao campo de text de acordo com objeto setado inicianmente no comboBox
			txtNovoPreco = new JTextField(String.valueOf(produtoSelecionado.getPrecoFixo()));

			
		//exibindo o text field Novo Preço
		txtNovoPreco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNovoPreco.setColumns(10);
		txtNovoPreco.setBounds(270, 178, 212, 39);
		contentPanel.add(txtNovoPreco);
		
		//criando botão retornar à página inicial
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
		btnRetornar.setBounds(120, 281, 152, 48);
		contentPanel.add(btnRetornar);
		
		JLabel lblAlerta = new JLabel("O valor informado n\u00E3o pode estar em branco");
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setBounds(10, 228, 563, 42);
		lblAlerta.setVisible(false);
		contentPanel.add(lblAlerta);
		
		//criando botão para salvar modificações de preços
		JButton btnAtualizar = new JButton("Adicionar");
		btnAtualizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){	//caso enter seja pressionado
					//verifica informações inseridas
					if(verificaCampo(comboBox.getSelectedItem().toString() ,
							txtNovoPreco.getText(),lblAlerta)){
						//chama método para atualizar preço
						produtoSelecionado.atualizarPreco(Float.parseFloat(txtNovoPreco.getText()));
						dispose();
					}
				}
			}
		});
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//verifica informações inseridas
				if(verificaCampo(comboBox.getSelectedItem().toString() ,
						txtNovoPreco.getText(),lblAlerta)){
					//chama método para atualizar preço
					produtoSelecionado.atualizarPreco(Float.parseFloat(txtNovoPreco.getText()));
					dispose();
				}
			}
		});
		btnAtualizar.setBounds(282, 281, 152, 48);
		contentPanel.add(btnAtualizar);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBox, txtNovoPreco, btnAtualizar, btnRetornar}));
		
		
		
	}
	
	/**
	 * Método que faz o tratamento das informações que serão inseridas
	 * @param nome	Produto selecionado no combobox
	 * @param preco	Preço que será atualizado
	 * @param lblAlerta	Label onde será exibida a mensagem de erro caso necessário
	 * @return True caso padrão esteja correto e False caso não
	 */
	public boolean verificaCampo(String nome, String preco,JLabel lblAlerta){
		//verifica se o campo está em branco
		if(nome.equals("") || preco.equals("")){
			lblAlerta.setText("Os valores informados não podem estar em branco");
			lblAlerta.setVisible(true);
			return false;
		}
		
		//verifica se o campo pode ser convertido em um tipo float
		try{
			float x = Float.parseFloat(preco);
		}catch(NumberFormatException ex){
			lblAlerta.setText("Apenas números podem ser digitados, utilize . no lugar de ,");
			lblAlerta.setVisible(true);
		}
		
		return true;
	}
}
