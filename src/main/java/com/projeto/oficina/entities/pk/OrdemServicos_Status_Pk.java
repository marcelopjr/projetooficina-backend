package com.projeto.oficina.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.oficina.entities.OrdemServicos;
import com.projeto.oficina.entities.Status;

@Embeddable
public class OrdemServicos_Status_Pk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ordemservico_id")
	private OrdemServicos ordemservicos;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public OrdemServicos getOrdemservicos() {
		return ordemservicos;
	}

	public void setOrdemservicos(OrdemServicos ordemservicos) {
		this.ordemservicos = ordemservicos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
