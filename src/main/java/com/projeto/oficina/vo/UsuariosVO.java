package com.projeto.oficina.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class UsuariosVO {
	
	@NotBlank(message = "Digite o seu nome!")
	@Size(min = 2, max = 50, message = "Tamanho do nome inválido!")
	private String nome;
	
	@NotBlank(message = "Digite o seu CPF!")
	@Size(min = 11, max = 14, message = "CPF inválido!")
	@CPF(message = "CPF inválido!")
	private String cpf;
	
	@NotBlank(message = "Digite o seu telefone!")
	@Size(min = 13, max = 13, message = "Numero de telefone inválido!")
	private String telefone;
	
	@NotBlank(message = "Digite seu e-mail!")
	@Email
	@Pattern(regexp = ".+@.+\\..+", message = "E-mail fornecido não é válido!")
	private String email;
	
	@NotBlank(message = "Digite uma senha!")
	private String senha;
	
	
	private String tipo;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
