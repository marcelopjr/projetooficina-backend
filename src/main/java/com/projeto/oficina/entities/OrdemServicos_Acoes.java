package com.projeto.oficina.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.projeto.oficina.entities.pk.OrdemServicos_Acoes_Pk;

@Entity
@Table(name = "ordemservicos_acoes")
public class OrdemServicos_Acoes {
	
	@EmbeddedId
	private OrdemServicos_Acoes_Pk id = new OrdemServicos_Acoes_Pk();
		
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "nome_peca")
	private String nome_peca;

	public OrdemServicos_Acoes_Pk getId() {
		return id;
	}

	public void setId(OrdemServicos_Acoes_Pk id) {
		this.id = id;
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
