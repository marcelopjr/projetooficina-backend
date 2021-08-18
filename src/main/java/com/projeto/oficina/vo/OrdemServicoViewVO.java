package com.projeto.oficina.vo;

import java.util.List;

import com.projeto.oficina.entities.Carros;

public class OrdemServicoViewVO {
	
	private Integer id;

	private String valor;
		
	private Carros carro;
	
	private String status_atual;
	
	private List<AcoesViewVO> listaAcoes;
	
	private List<StatusViewVO> listaStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Carros getCarro() {
		return carro;
	}

	public void setCarro(Carros carro) {
		this.carro = carro;
	}

	public String getStatus_atual() {
		return status_atual;
	}

	public void setStatus_atual(String status_atual) {
		this.status_atual = status_atual;
	}

	public List<AcoesViewVO> getListaAcoes() {
		return listaAcoes;
	}

	public void setListaAcoes(List<AcoesViewVO> listaAcoes) {
		this.listaAcoes = listaAcoes;
	}

	public List<StatusViewVO> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<StatusViewVO> listaStatus) {
		this.listaStatus = listaStatus;
	}
	
	

}
