package com.projeto.oficina.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "status")
public class Status {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status_atual")
	private List<OrdemServicos> listaOrdemServicos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<OrdemServicos> getListaOrdemServicos() {
		return listaOrdemServicos;
	}

	public void setListaOrdemServicos(List<OrdemServicos> listaOrdemServicos) {
		this.listaOrdemServicos = listaOrdemServicos;
	}
	
	
	
}
