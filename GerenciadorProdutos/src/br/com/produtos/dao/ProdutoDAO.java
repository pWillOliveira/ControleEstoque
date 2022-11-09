package br.com.produtos.dao;

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
		final String query = "insert into produtos (nomeprodutos, fornecedorprodutos) values (?,?)";
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
		JOptionPane.showMessageDialog(null, "Produto cadastrado na base de dados.", "Produto Cadastrado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public ArrayList<Produto> pesquisarProduto() {
		final String query = "select * from produtos";
		conn = new ConexaoDAO().conectaBD();

		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();

			while (rs.next()) {
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

	public void alterarProduto(Produto objproduto) {
		final String query = "update produtos set nomeprodutos = ?, fornecedorprodutos = ? where idprodutos = ?";
		conn = new ConexaoDAO().conectaBD();

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, objproduto.getNome());
			pstm.setString(2, objproduto.getFornecedor());
			pstm.setInt(3, objproduto.getIdproduto());

			pstm.execute();
			pstm.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Alterar" + erro);
		}
		JOptionPane.showMessageDialog(null, "Produto alterado na base de dados.", "Produto Alterado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void deletarProduto(Produto objproduto) {
		final String query = "delete from produtos where idprodutos = ?";
		conn = new ConexaoDAO().conectaBD();

		try {

			pstm = conn.prepareStatement(query);
			pstm.setInt(1, objproduto.getIdproduto());

			pstm.execute();
			pstm.close();

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Excluir" + erro);
		}
		JOptionPane.showMessageDialog(null, "Produto excluído da base de dados.", "Produto Excluído",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
