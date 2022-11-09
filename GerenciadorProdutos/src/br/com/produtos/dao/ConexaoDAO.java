package br.com.produtos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {

	public Connection conectaBD() {
		Connection conn = null;

		try {
			final String url = "jdbc:mysql://localhost:3306/";
			final String schema = "dbprodutos";
			final String user = "root";
			final String password = "root";

			conn = DriverManager.getConnection(url + schema + "?user=" + user + "&password=" + password);

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ConexaoDAO" + erro.getMessage());
		}
		return conn;
	}

}
