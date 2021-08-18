package com.projeto.oficina.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordemservicos")
public class OrdemServicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String valor;
	
	@ManyToOne
	private Usuarios usuario;
	
	@ManyToOne
	private Carros carro;
	
	@ManyToOne
	private Status status_atual;
	
	@OneToMany
	private List<OrdemServicos_Acoes> listaAcoes;
	
	@OneToMany
	private List<OrdemServicos_Status> listaStatus;

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

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Carros getCarro() {
		return carro;
	}

	public void setCarro(Carros carro) {
		this.carro = carro;
	}

	public Status getStatus_atual() {
		return status_atual;
	}

	public void setStatus_atual(Status status_atual) {
		this.status_atual = status_atual;
	}

	public List<OrdemServicos_Acoes> getListaAcoes() {
		return listaAcoes;
	}

	public void setListaAcoes(List<OrdemServicos_Acoes> listaAcoes) {
		this.listaAcoes = listaAcoes;
	}

	public List<OrdemServicos_Status> getListaStatus() {
		return listaStatus;
	}

	public void setListaStatus(List<OrdemServicos_Status> listaStatus) {
		this.listaStatus = listaStatus;
	}
	
	
}
