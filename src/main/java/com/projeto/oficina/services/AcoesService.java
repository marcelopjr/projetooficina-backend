package com.projeto.oficina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Acoes;
import com.projeto.oficina.entities.OrdemServicos_Acoes;
import com.projeto.oficina.repositories.AcoesRepository;
import com.projeto.oficina.vo.AcoesVO;
import com.projeto.oficina.vo.AcoesViewVO;

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
	
	public AcoesViewVO converteEntidadeParaViewVO(OrdemServicos_Acoes ordemServicos_Acoes) {
		AcoesViewVO acoesViewVO = new AcoesViewVO();
		
		acoesViewVO.setNome(ordemServicos_Acoes.getId().getAcoes().getNome());
		acoesViewVO.setData(ordemServicos_Acoes.getData());
		acoesViewVO.setNome_peca(ordemServicos_Acoes.getNome_peca());
		
		return acoesViewVO;
	}
}
