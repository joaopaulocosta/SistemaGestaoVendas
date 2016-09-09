package GUI;

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
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LancarComanda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRefeicao;
	private JTextField txtAgua500;
	private JTextField txtCerveja;
	private JTextField txtPinga;
	private JTextField txtRefri290;
	private JTextField txtRefri600;
	private JTextField txtRefri1000;
	private JTextField txtRefri1250;
	private JTextField txtRefri2000;
	private JTextField txtAgua1500;
	private JTextField txtOutros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LancarComanda dialog = new LancarComanda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LancarComanda() {
		setTitle("Lan\u00E7amento de Comanda");
		setBounds(100, 100, 614, 780);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
		{
			JLabel lblRefeicao = new JLabel("Refei\u00E7\u00E3o:");
			lblRefeicao.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRefeicao.setBounds(31, 88, 164, 30);
			contentPanel.add(lblRefeicao);
		}
		{
			txtRefeicao = new JTextField();
			txtRefeicao.setBounds(179, 88, 146, 28);
			contentPanel.add(txtRefeicao);
			txtRefeicao.setColumns(10);
		}
		{
			JComboBox cmboxRefeicoes = new JComboBox();
			cmboxRefeicoes.setBounds(470, 88, 118, 28);
			contentPanel.add(cmboxRefeicoes);
		}
		{
			JLabel lblQuantRefeicoes = new JLabel("Quantidade: 0");
			lblQuantRefeicoes.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblQuantRefeicoes.setBounds(335, 88, 164, 30);
			contentPanel.add(lblQuantRefeicoes);
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
			lblOutros.setBounds(31, 507, 164, 30);
			contentPanel.add(lblOutros);
		}
		{
			txtAgua500 = new JTextField();
			txtAgua500.setColumns(10);
			txtAgua500.setBounds(179, 429, 146, 28);
			contentPanel.add(txtAgua500);
		}
		{
			txtCerveja = new JTextField();
			txtCerveja.setColumns(10);
			txtCerveja.setBounds(179, 140, 146, 28);
			contentPanel.add(txtCerveja);
		}
		{
			txtPinga = new JTextField();
			txtPinga.setColumns(10);
			txtPinga.setBounds(179, 181, 146, 28);
			contentPanel.add(txtPinga);
		}
		{
			txtRefri290 = new JTextField();
			txtRefri290.setColumns(10);
			txtRefri290.setBounds(179, 222, 146, 28);
			contentPanel.add(txtRefri290);
		}
		{
			txtRefri600 = new JTextField();
			txtRefri600.setColumns(10);
			txtRefri600.setBounds(179, 263, 146, 28);
			contentPanel.add(txtRefri600);
		}
		{
			txtRefri1000 = new JTextField();
			txtRefri1000.setColumns(10);
			txtRefri1000.setBounds(179, 306, 146, 28);
			contentPanel.add(txtRefri1000);
		}
		{
			txtRefri1250 = new JTextField();
			txtRefri1250.setColumns(10);
			txtRefri1250.setBounds(179, 347, 146, 28);
			contentPanel.add(txtRefri1250);
		}
		{
			txtRefri2000 = new JTextField();
			txtRefri2000.setColumns(10);
			txtRefri2000.setBounds(179, 388, 146, 28);
			contentPanel.add(txtRefri2000);
		}
		{
			txtAgua1500 = new JTextField();
			txtAgua1500.setColumns(10);
			txtAgua1500.setBounds(179, 470, 146, 28);
			contentPanel.add(txtAgua1500);
		}
		{
			txtOutros = new JTextField();
			txtOutros.setColumns(10);
			txtOutros.setBounds(179, 511, 146, 28);
			contentPanel.add(txtOutros);
		}
		{
			JLabel lblQuantOutros = new JLabel("Quantidade: 0");
			lblQuantOutros.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblQuantOutros.setBounds(335, 507, 164, 30);
			contentPanel.add(lblQuantOutros);
		}
		{
			JComboBox cmboxOutros = new JComboBox();
			cmboxOutros.setBounds(470, 507, 118, 28);
			contentPanel.add(cmboxOutros);
		}
		{
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
			btRegistrar.setBounds(311, 666, 152, 48);
			contentPanel.add(btRegistrar);
		}
	}

}
