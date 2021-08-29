package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.OrdemServicos_Status;
import com.projeto.oficina.entities.Status;
import com.projeto.oficina.repositories.StatusRepository;
import com.projeto.oficina.vo.StatusViewVO;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository statusRepository;
	
	public Status findById(Integer id) {
		return statusRepository.findById(id).get();
	}
	
	public StatusViewVO converteEntidadeParaViewVO(OrdemServicos_Status ordemServicos_Status) {
		StatusViewVO statusViewVO = new StatusViewVO();
		
		statusViewVO.setNome(ordemServicos_Status.getId().getStatus().getNome());
		statusViewVO.setData(ordemServicos_Status.getData());
		statusViewVO.setMensagem(ordemServicos_Status.getMensagem());
		
		return statusViewVO;
	}

}
