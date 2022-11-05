package br.com.produtos.model;

public class Produto {

	private String nome, fornecedor;
	private int idproduto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}

}
