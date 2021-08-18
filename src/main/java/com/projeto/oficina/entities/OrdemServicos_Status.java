package com.projeto.oficina.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.projeto.oficina.entities.pk.OrdemServicos_Status_Pk;

@Entity
@Table(name = "ordemservicos_status")
public class OrdemServicos_Status {
	
	@EmbeddedId
	private OrdemServicos_Status_Pk id = new OrdemServicos_Status_Pk();
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@Column(name = "data")
	private LocalDate data;

	public OrdemServicos_Status_Pk getId() {
		return id;
	}

	public void setId(OrdemServicos_Status_Pk id) {
		this.id = id;
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
