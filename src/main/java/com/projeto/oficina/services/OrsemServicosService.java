package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.repositories.OrdemServicosRepository;

@Service
public class OrsemServicosService {

	@Autowired
	OrdemServicosRepository ordemServicosRepository;
	
	
}
