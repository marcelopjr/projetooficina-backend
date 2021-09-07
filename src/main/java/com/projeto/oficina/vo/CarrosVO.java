package com.projeto.oficina.vo;

import javax.validation.constraints.NotBlank;

public class CarrosVO {
	
	private Integer id;

	@NotBlank(message = "Digite o modelo do seu carro!")
	private String modelo;
	
	@NotBlank(message = "Digite a marca do seu carro!")
	private String marca;
	
	@NotBlank(message = "Digite a placa do seu carro!")
	@PlacaVeiculo
	private String placa;
	
	@NotBlank(message = "Digite a cor do seu carro!")
	private String cor;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	

}
