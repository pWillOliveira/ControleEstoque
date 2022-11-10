package br.com.produtos.appview;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.produtos.dao.UsuarioDAO;
import br.com.produtos.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Inicia a janela de Login.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria o JFrame de Login.
	 */
	public LoginView() {
		setTitle("Sistema de Controle de Estoque - Versão 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sistema de Controle de Estoque");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(70, 11, 284, 32);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 91, 53, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(152, 156, 45, 13);
		contentPane.add(lblNewLabel_2);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(152, 115, 131, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(152, 180, 131, 20);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					String nomeusuario, senhausuario;
					nomeusuario = txtUsuario.getText();
					senhausuario = txtSenha.getText();

					Usuario objusuario = new Usuario();
					objusuario.setNomeUsuario(nomeusuario);
					objusuario.setSenhaUsuario(senhausuario);

					UsuarioDAO objusuariodao = new UsuarioDAO();
					ResultSet rsusuariodao = objusuariodao.LoginUsuario(objusuario);

					if (rsusuariodao.next()) {
						AppView objappview = new AppView();
						objappview.setVisible(true);
						objappview.setAlwaysOnTop(true);
						objappview.setAlwaysOnTop(false);
						dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "Usuário ou Senha inválida.");
					}

				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "btnEntrar" + erro);
				}
			}
		});
		btnEntrar.setBounds(172, 210, 89, 23);
		contentPane.add(btnEntrar);
	}
}
