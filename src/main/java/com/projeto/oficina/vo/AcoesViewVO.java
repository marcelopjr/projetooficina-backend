package com.projeto.oficina.vo;

import java.time.LocalDate;

public class AcoesViewVO {
	
	private String nome;
	
	private String nome_peca;
	
	private LocalDate data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNome_peca() {
		return nome_peca;
	}

	public void setNome_peca(String nome_peca) {
		this.nome_peca = nome_peca;
	}
	
	

}
