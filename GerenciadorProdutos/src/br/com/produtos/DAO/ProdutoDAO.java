package br.com.produtos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import br.com.produtos.model.Produto;

//Classe de conexão com o MySQL
public class ProdutoDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();
	
	public void cadastrarProduto(Produto objproduto) {
		String query = "insert into produtos (nomeprodutos, fornecedorprodutos) values (?,?)";
		
		conn = new ConexaoDAO().conectaBD();
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, objproduto.getNome());
			pstm.setString(2, objproduto.getFornecedor());
			
			pstm.execute();
			pstm.close();
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Cadastrar" + erro);
		}
		JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");
	}
	
	public  ArrayList<Produto> PesquisarProduto(){
		String query = "select * from produtos";
		conn = new ConexaoDAO().conectaBD();
		
		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Produto objproduto = new Produto();
				objproduto.setIdproduto(rs.getInt("idprodutos"));
				objproduto.setNome(rs.getString("nomeprodutos"));
				objproduto.setFornecedor(rs.getString("fornecedorprodutos"));
				
				lista.add(objproduto);
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Pesquisar" + erro);
		}
		return lista;
		
	}

}
