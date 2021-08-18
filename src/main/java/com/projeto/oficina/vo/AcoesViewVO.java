package com.projeto.oficina.vo;

import java.time.LocalDate;

public class AcoesViewVO {
	
	private String nome;
	
	private String mensagem;
	
	private LocalDate data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
