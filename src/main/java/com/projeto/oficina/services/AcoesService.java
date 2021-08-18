package com.projeto.oficina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Acoes;
import com.projeto.oficina.repositories.AcoesRepository;
import com.projeto.oficina.vo.AcoesVO;

@Service
public class AcoesService {
	
	@Autowired
	private AcoesRepository acoesRepository;
	
	public Acoes findById(Integer id) {
		return acoesRepository.findById(id).get();
	}
	
	public List<Acoes> findAll() {
		return acoesRepository.findAll();
	}
	
	public AcoesVO converteEntidadeParaVo(Acoes acoes) {
		AcoesVO acoesVO = new AcoesVO();
		
		return acoesVO;
	}
	
	public Acoes converteVoParaEntidade(AcoesVO acoesVo) {
		Acoes acoes = new Acoes();
		
		return acoes;
	}
}
