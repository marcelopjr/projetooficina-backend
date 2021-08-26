package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Carros;
import com.projeto.oficina.repositories.CarrosRepository;
import com.projeto.oficina.vo.CarrosVO;

@Service
public class CarrosService {
	
	@Autowired
	CarrosRepository carrosRepository;
	
	@Autowired
	UsuariosService usuariosService;
	
	public boolean save(CarrosVO carrosVO) {
		Carros novoCarro = converteVoParaEntidade(carrosVO);
		novoCarro.setUsuario(usuariosService.GetUsuarioAutenticado());
		carrosRepository.save(novoCarro);
		return true;
	}
	
	public Carros converteVoParaEntidade(CarrosVO carrosVO) {
		Carros carros = new Carros();
		carros.setMarca(carrosVO.getMarca());
		carros.setModelo(carrosVO.getModelo());
		carros.setPlaca(carrosVO.getPlaca());
		return carros;
	}
	
	public CarrosVO converteEntidadeParaVo(Carros carros) {
		CarrosVO carrosVO = new CarrosVO();
		return carrosVO;
	}
}
