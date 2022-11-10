package br.com.produtos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import br.com.produtos.model.Produto;

public class ProdutoDAO {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Produto> lista = new ArrayList<>();

	public void cadastrarProduto(Produto objproduto) {
		final String query = "insert into produtos (nome, fornecedor, quantidade, preco) values (?,?,?,?)";
		conn = new ConexaoDAO().conectaBD();

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, objproduto.getNome());
			pstm.setString(2, objproduto.getFornecedor());
			pstm.setInt(3, objproduto.getQuantidade());
			pstm.setDouble(4, objproduto.getPreco());

			pstm.execute();
			pstm.close();
			conn.close();

			JOptionPane.showMessageDialog(null, "Produto cadastrado na base de dados.", "Produto Cadastrado",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Cadastrar" + erro);
		}
	}

	public ArrayList<Produto> pesquisarProduto() {
		final String query = "select * from produtos";
		conn = new ConexaoDAO().conectaBD();

		try {
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Produto objproduto = new Produto();
				objproduto.setIdproduto(rs.getInt("id"));
				objproduto.setNome(rs.getString("nome"));
				objproduto.setFornecedor(rs.getString("fornecedor"));
				objproduto.setQuantidade(rs.getInt("quantidade"));
				objproduto.setPreco(rs.getDouble("preco"));

				lista.add(objproduto);
			}
			pstm.close();
			conn.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Pesquisar" + erro);
		}
		return lista;

	}

	public void alterarProduto(Produto objproduto) {
		final String query = "update produtos set nome = ?, fornecedor = ?, quantidade = ?, preco = ? where id = ?";
		conn = new ConexaoDAO().conectaBD();

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, objproduto.getNome());
			pstm.setString(2, objproduto.getFornecedor());
			pstm.setInt(3, objproduto.getQuantidade());
			pstm.setDouble(4, objproduto.getPreco());
			pstm.setInt(5, objproduto.getIdproduto());

			pstm.execute();
			pstm.close();
			conn.close();

			JOptionPane.showMessageDialog(null, "Produto alterado na base de dados.", "Produto Alterado",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Alterar" + erro);
		}
	}

	public void deletarProduto(Produto objproduto) {
		final String query = "delete from produtos where id = ?";
		conn = new ConexaoDAO().conectaBD();

		try {

			pstm = conn.prepareStatement(query);
			pstm.setInt(1, objproduto.getIdproduto());

			pstm.execute();
			pstm.close();
			conn.close();

			JOptionPane.showMessageDialog(null, "Produto excluído da base de dados.", "Produto Excluído",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO Excluir" + erro);
		}
	}

}
