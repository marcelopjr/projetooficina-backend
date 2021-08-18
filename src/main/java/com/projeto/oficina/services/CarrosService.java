package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.repositories.CarrosRepository;

@Service
public class CarrosService {
	
	@Autowired
	CarrosRepository carrosRepository;

}
