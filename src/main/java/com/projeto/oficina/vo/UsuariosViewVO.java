package com.projeto.oficina.vo;

import java.util.List;

public class UsuariosViewVO {
	
	private Integer id;

	private String nome;
	
	private String cpf;
	
	private String telefone;
	
	private String email;
	
	private List<CarrosViewVO> listaCarros;
	
	private List<OrdemServicosViewVO> listaOrdemServicos;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OrdemServicosViewVO> getListaOrdemServicos() {
		return listaOrdemServicos;
	}

	public void setListaOrdemServicos(List<OrdemServicosViewVO> listaOrdemServicos) {
		this.listaOrdemServicos = listaOrdemServicos;
	}

	public List<CarrosViewVO> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<CarrosViewVO> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
	
}
