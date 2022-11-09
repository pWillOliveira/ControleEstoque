package br.com.produtos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.produtos.model.Usuario;

public class UsuarioDAO {

	Connection conn;

	public ResultSet LoginUsuario(Usuario objusuario) {
		conn = new ConexaoDAO().conectaBD();

		try {
			final String query = "select * from usuario where nomeusuario = ? and senhausuario = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, objusuario.getNomeUsuario());
			pstm.setString(2, objusuario.getSenhaUsuario());
			
			ResultSet rs = pstm.executeQuery();
			return rs;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "LoginUsuario" + erro);
			return null;
		}

	}
}
