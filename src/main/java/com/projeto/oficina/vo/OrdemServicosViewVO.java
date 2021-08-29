package com.projeto.oficina.vo;

import java.time.LocalDate;
import java.util.List;

public class OrdemServicosViewVO {
	
	private Integer id;

	private String valor;
	
	private boolean os_aberta;
	
	private String status_atual;
	
	private LocalDate data_abertura;
	
	private LocalDate data_finalizada;
	
	private LocalDate data_entrega_carro;
		
	private CarrosViewVO carro;
	
	private List<StatusViewVO> listaStatus;
	
	private List<AcoesViewVO> listaAcoes;

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

	public CarrosViewVO getCarro() {
		return carro;
	}

	public void setCarro(CarrosViewVO carro) {
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
