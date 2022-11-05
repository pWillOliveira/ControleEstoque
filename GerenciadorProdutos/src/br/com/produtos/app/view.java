package br.com.produtos.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.produtos.DAO.ProdutoDAO;
import br.com.produtos.model.Produto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class view extends JFrame {

	/**
	 * Autor William Oliveira
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtFornecedor;
	private static JTable tabelaProdutos;
	private JButton btnListar;
	private JScrollPane scrollPane;
	private JTextField txtCodigo;
	/**
	 * Executa a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
					frame.setVisible(true);
					ListarValores();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria o Frame.
	 */
	public view() {
		setTitle("Controle de Estoque");
		setAutoRequestFocus(false);
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setBounds(10, 67, 130, 14);
		getContentPane().add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 92, 265, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fornecedor:");
		lblNewLabel_1.setBounds(10, 123, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setBounds(10, 148, 265, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProdutos();
				CarregarCampos();
			}
		});
		btnCadastrar.setBounds(10, 179, 105, 23);
		getContentPane().add(btnCadastrar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 213, 564, 195);
		getContentPane().add(scrollPane);
		
		tabelaProdutos = new JTable();
		tabelaProdutos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descri\u00E7\u00E3o do Produto", "Fornecedor"
			}
		));
		scrollPane.setViewportView(tabelaProdutos);
		
		btnListar = new JButton("Listar Produtos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarValores();
			}
		});
		btnListar.setBounds(125, 179, 124, 23);
		getContentPane().add(btnListar);
		
		JLabel lblNewLabel_2 = new JLabel("Código:");
		lblNewLabel_2.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 36, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnCarregar = new JButton("Carregar Campos");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarregarCampos();
				ListarValores();
			}
		});
		btnCarregar.setBounds(10, 419, 148, 23);
		getContentPane().add(btnCarregar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(259, 179, 89, 23);
		getContentPane().add(btnAlterar);
		
		
		

	}
	/**
	 * Metodos dos botões.
	 */
	private void AlterarProduto() {
		
	}
	
	private void LimparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtFornecedor.setText("");
		txtNome.requestFocus();
	}
	
	private void CadastrarProdutos() {
		String pnome, pfornecedor;
		pnome = txtNome.getText();
		pfornecedor = txtFornecedor.getText();
		
		Produto objproduto = new Produto();
		objproduto.setNome(pnome);
		objproduto.setFornecedor(pfornecedor);
		
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
	}
	
	private static void ListarValores() {
		try {
			ProdutoDAO objprodutodao = new ProdutoDAO();
			
			DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
			model.setNumRows(0);
			
			ArrayList<Produto> lista = objprodutodao.PesquisarProduto();
			
			for(int i = 0 ; i < lista.size() ; i++) {
				model.addRow(new Object[] {
						lista.get(i).getIdproduto(),
						lista.get(i).getNome(),
						lista.get(i).getFornecedor()
				});
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Listar Valores: "+ erro);
		}
	}
}
