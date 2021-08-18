package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.repositories.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository statusRepository;

}
