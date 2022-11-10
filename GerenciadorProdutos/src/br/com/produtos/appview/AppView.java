package br.com.produtos.appview;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.produtos.dao.ProdutoDAO;
import br.com.produtos.model.Data;
import br.com.produtos.model.Produto;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.ListSelectionModel;

public class AppView extends JFrame {

	/**
	 * Autor William Oliveira
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtFornecedor;
	private JTextField txtCodigo;
	private static JTable tabelaProdutos;
	private JButton btnListar;
	private JButton btnDeletar;
	private JScrollPane scrollPane;
	private static JLabel lblData;
	private static JLabel lblHora;
	private JTextField txtQtd;
	private JTextField txtPreco;

	/**
	 * Cria o Frame.
	 */
	public AppView() {
		Locale.setDefault(Locale.US);

		setTitle("Controle de Estoque");
		setAutoRequestFocus(false);
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Label de Hora e Data na Tela
		lblData = new JLabel("00/00/00");
		lblData.setBackground(UIManager.getColor("Button.shadow"));
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setForeground(new Color(0, 0, 128));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(451, 11, 67, 14);
		getContentPane().add(lblData);

		lblHora = new JLabel("00:00:00");
		lblHora.setBackground(UIManager.getColor("Button.shadow"));
		lblHora.setFont(new Font("Arial", Font.BOLD, 12));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(new Color(0, 0, 128));
		lblHora.setBounds(515, 11, 59, 14);
		getContentPane().add(lblHora);

		// Criando o Timer para atualização da hora em tempo real.
		Timer timer = new Timer();
		final long s = (1000);
		TimerTask tarefa = new TimerTask() {
			@Override
			public void run() {
				Data hora = new Data(new Date());
				Data data = new Data(new Date());
				lblData.setText(data.getData());
				lblHora.setText(hora.getHora());
			}
		};
		timer.scheduleAtFixedRate(tarefa, s, s);

		// Demais Labels e Botões
		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setBounds(10, 67, 130, 14);
		getContentPane().add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(10, 92, 230, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fornecedor:");
		lblNewLabel_1.setBounds(250, 67, 86, 14);
		getContentPane().add(lblNewLabel_1);

		txtFornecedor = new JTextField();
		txtFornecedor.setBounds(250, 92, 230, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText() == null || txtNome.getText().trim().equals("") || txtFornecedor.getText() == null
						|| txtFornecedor.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos para cadastrar.",
							"Erro ao Cadastrar", JOptionPane.ERROR_MESSAGE);

				} else {
					CadastrarProdutos();
					ListarValores();
					LimparCampos();
				}
			}
		});
		btnCadastrar.setBounds(10, 179, 105, 23);
		getContentPane().add(btnCadastrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 213, 564, 195);
		getContentPane().add(scrollPane);

		tabelaProdutos = new JTable();
		tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProdutos
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, }, new String[] {
						"C\u00F3digo", "Descri\u00E7\u00E3o do Produto", "Fornecedor", "QTD", "Pre\u00E7o R$" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(195);
		tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(40);
		scrollPane.setViewportView(tabelaProdutos);
		ListarValores();

		btnListar = new JButton("Limpar Campos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnListar.setBounds(323, 179, 124, 23);
		getContentPane().add(btnListar);

		JLabel lblNewLabel_2 = new JLabel("Código:");
		lblNewLabel_2.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(10, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		JButton btnCarregar = new JButton("Carregar Campos");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelaProdutos.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela para carregar os valores.",
							"Erro ao Carregar Campos", JOptionPane.ERROR_MESSAGE);
				} else {
					CarregarCampos();
					ListarValores();
				}
			}
		});
		btnCarregar.setBounds(10, 419, 148, 23);
		getContentPane().add(btnCarregar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText() == null || txtCodigo.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Carregue os campos antes para alterar um produto.",
							"Erro ao Alterar", JOptionPane.ERROR_MESSAGE);

				} else {
					AlterarProduto();
					ListarValores();
					LimparCampos();
				}
			}

		});
		btnAlterar.setBounds(125, 179, 89, 23);
		getContentPane().add(btnAlterar);

		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText() == null || txtCodigo.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Carregue os campos antes para excluir um produto.",
							"Erro ao Excluir", JOptionPane.ERROR_MESSAGE);

				} else {
					DeletarProduto();
					ListarValores();
					LimparCampos();
				}
			}
		});
		btnDeletar.setBounds(224, 179, 89, 23);
		getContentPane().add(btnDeletar);

		txtQtd = new JTextField();
		txtQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String chars = "0987654321";
				if (!chars.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtQtd.setColumns(10);
		txtQtd.setBounds(10, 148, 110, 20);
		getContentPane().add(txtQtd);

		JLabel lblNewLabel_3 = new JLabel("Quantidade:");
		lblNewLabel_3.setBounds(10, 123, 86, 14);
		getContentPane().add(lblNewLabel_3);

		txtPreco = new JTextField();
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String chars = "0987654321.";
				if (!chars.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtPreco.setColumns(10);
		txtPreco.setBounds(250, 148, 110, 20);
		getContentPane().add(txtPreco);

		JLabel lblNewLabel_4 = new JLabel("Preço:");
		lblNewLabel_4.setBounds(250, 123, 63, 14);
		getContentPane().add(lblNewLabel_4);

	}

	/**
	 * Metodos dos botões.
	 */

	private void DeletarProduto() {
		int idprodutos;
		idprodutos = Integer.parseInt(txtCodigo.getText());

		Produto objproduto = new Produto();
		objproduto.setIdproduto(idprodutos);

		ProdutoDAO objprodutodao = new ProdutoDAO();
		objprodutodao.deletarProduto(objproduto);
	}

	private void AlterarProduto() {
		int idprodutos, quantidade;
		String nomeprodutos, fornecedorprodutos;
		double preco;

		idprodutos = Integer.parseInt(txtCodigo.getText());
		nomeprodutos = txtNome.getText();
		fornecedorprodutos = txtFornecedor.getText();
		quantidade = Integer.parseInt(txtQtd.getText());
		preco = Double.parseDouble(txtPreco.getText());

		Produto objproduto = new Produto();
		objproduto.setIdproduto(idprodutos);
		objproduto.setNome(nomeprodutos);
		objproduto.setFornecedor(fornecedorprodutos);
		objproduto.setQuantidade(quantidade);
		objproduto.setPreco(preco);

		ProdutoDAO objprodutodao = new ProdutoDAO();
		objprodutodao.alterarProduto(objproduto);

	}

	private void LimparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtFornecedor.setText("");
		txtQtd.setText("");
		txtPreco.setText("");
		txtNome.requestFocus();
	}

	private void CadastrarProdutos() {
		String pnome, pfornecedor;
		int quantidade;
		double preco;
		pnome = txtNome.getText();
		pfornecedor = txtFornecedor.getText();
		quantidade = Integer.parseInt(txtQtd.getText());
		preco = Double.parseDouble(txtPreco.getText());

		Produto objproduto = new Produto();
		objproduto.setNome(pnome);
		objproduto.setFornecedor(pfornecedor);
		objproduto.setQuantidade(quantidade);
		objproduto.setPreco(preco);

		ProdutoDAO objprodutodao = new ProdutoDAO();
		objprodutodao.cadastrarProduto(objproduto);
		LimparCampos();
		ListarValores();

	}

	private void CarregarCampos() {
		int set = tabelaProdutos.getSelectedRow();

		txtCodigo.setText(tabelaProdutos.getModel().getValueAt(set, 0).toString());
		txtNome.setText(tabelaProdutos.getModel().getValueAt(set, 1).toString());
		txtFornecedor.setText(tabelaProdutos.getModel().getValueAt(set, 2).toString());
		txtQtd.setText(tabelaProdutos.getModel().getValueAt(set, 3).toString());
		txtPreco.setText(tabelaProdutos.getModel().getValueAt(set, 4).toString());
	}

	private static void ListarValores() {
		try {
			ProdutoDAO objprodutodao = new ProdutoDAO();

			DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
			model.setNumRows(0);

			ArrayList<Produto> lista = objprodutodao.pesquisarProduto();

			for (int i = 0; i < lista.size(); i++) {
				DecimalFormat df = new DecimalFormat("0.00");
				model.addRow(new Object[] { lista.get(i).getIdproduto(), lista.get(i).getNome(),
						lista.get(i).getFornecedor(), lista.get(i).getQuantidade(),
						df.format(lista.get(i).getPreco()) });
			}

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Listar Valores: " + erro);
		}
	}

}
