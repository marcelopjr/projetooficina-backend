package com.projeto.oficina.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.oficina.entities.Acoes;
import com.projeto.oficina.entities.OrdemServicos;

@Embeddable
public class OrdemServicos_Acoes_Pk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ordemservico_id")
	private OrdemServicos ordemservicos;
	
	@ManyToOne
	@JoinColumn(name = "acoes_id")
	private Acoes acoes;

	public OrdemServicos getOrdemservicos() {
		return ordemservicos;
	}

	public void setOrdemservicos(OrdemServicos ordemservicos) {
		this.ordemservicos = ordemservicos;
	}

	public Acoes getAcoes() {
		return acoes;
	}

	public void setAcoes(Acoes acoes) {
		this.acoes = acoes;
	}
	
	
}
