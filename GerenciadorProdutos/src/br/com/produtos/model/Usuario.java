package br.com.produtos.model;

public class Usuario {
	private int IdUsuario;
	private String NomeUsuario;
	private String SenhaUsuario;
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return NomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		NomeUsuario = nomeUsuario;
	}
	public String getSenhaUsuario() {
		return SenhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		SenhaUsuario = senhaUsuario;
	}

}
