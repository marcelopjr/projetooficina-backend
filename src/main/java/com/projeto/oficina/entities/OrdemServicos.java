package com.projeto.oficina.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ordemservicos")
public class OrdemServicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "valor")
	private String valor;
	
	@Column(name = "os_aberta")
	private boolean os_aberta;
	
	@Column(name = "data_abertura")
	private LocalDate data_abertura;
	
	@Column(name = "data_finalizada")
	private LocalDate data_finalizada;
	
	@Column(name = "data_entrega_carro")
	private LocalDate data_entrega_carro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuarios usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "carro_id", referencedColumnName = "id")
	private Carros carro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "status_atual_id", referencedColumnName = "id")
	private Status status_atual;
	
	@OneToMany(mappedBy = "id.ordemservicos")
	private List<OrdemServicos_Acoes> listaAcoes;
	
	@OneToMany(mappedBy = "id.ordemservicos")
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

	public LocalDate getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(LocalDate data_abertura) {
		this.data_abertura = data_abertura;
	}

	public LocalDate getData_finalizada() {
		return data_finalizada;
	}

	public void setData_finalizada(LocalDate data_finalizada) {
		this.data_finalizada = data_finalizada;
	}

	public LocalDate getData_entrega_carro() {
		return data_entrega_carro;
	}

	public void setData_entrega_carro(LocalDate data_entrega_carro) {
		this.data_entrega_carro = data_entrega_carro;
	}

	public boolean isOs_aberta() {
		return os_aberta;
	}

	public void setOs_aberta(boolean os_aberta) {
		this.os_aberta = os_aberta;
	}
	
}
