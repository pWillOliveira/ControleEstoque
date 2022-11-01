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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class view extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtFornecedor;
	private JTable tabelaProdutos;
	private JButton btnListar;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view() {
		setAutoRequestFocus(false);
		setBounds(100, 100, 600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setBounds(10, 11, 130, 14);
		getContentPane().add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 36, 265, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fornecedor:");
		lblNewLabel_1.setBounds(10, 67, 86, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setBounds(10, 92, 265, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pnome, pfornecedor;
				pnome = txtNome.getText();
				pfornecedor = txtFornecedor.getText();
				
				Produto objproduto = new Produto();
				objproduto.setNome(pnome);
				objproduto.setFornecedor(pfornecedor);
				
				ProdutoDAO objprodutodao = new ProdutoDAO();
				objprodutodao.cadastrarProduto(objproduto);
			}
		});
		btnCadastrar.setBounds(10, 123, 105, 23);
		getContentPane().add(btnCadastrar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 564, 195);
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
				listarValores();
			}
		});
		btnListar.setBounds(125, 123, 124, 23);
		getContentPane().add(btnListar);

	}
	
	private void listarValores() {
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
