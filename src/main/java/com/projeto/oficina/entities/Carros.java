package com.projeto.oficina.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carros")
public class Carros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "cor")
	private String cor;
	
	@ManyToOne
	@JoinColumn(name = "usuarioid", referencedColumnName = "id")
	private Usuarios usuario;

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

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	
	
}
