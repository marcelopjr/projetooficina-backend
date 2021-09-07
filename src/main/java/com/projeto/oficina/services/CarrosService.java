package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Carros;
import com.projeto.oficina.repositories.CarrosRepository;
import com.projeto.oficina.vo.CarrosVO;
import com.projeto.oficina.vo.CarrosViewVO;

@Service
public class CarrosService {
	
	@Autowired
	CarrosRepository carrosRepository;
	
	@Autowired
	UsuariosService usuariosService;
	
	public Carros findById(Integer id) {
		Carros carros = carrosRepository.findById(id).get();
		return carros;
	}
	
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
		carros.setCor(carrosVO.getCor());
		return carros;
	}
	
	public CarrosVO converteEntidadeParaVo(Carros carros) {
		CarrosVO carrosVO = new CarrosVO();
		carrosVO.setId(carros.getId());
		carrosVO.setMarca(carros.getMarca());
		carrosVO.setModelo(carros.getModelo());
		carrosVO.setPlaca(carros.getPlaca());
		return carrosVO;
	}
	
	public CarrosViewVO converteEntidadeParaViewVO(Carros carros) {
		CarrosViewVO carrosViewVO = new CarrosViewVO();
		
		carrosViewVO.setId(carros.getId());
		carrosViewVO.setMarca(carros.getMarca());
		carrosViewVO.setModelo(carros.getModelo());
		carrosViewVO.setPlaca(carros.getPlaca());
		carrosViewVO.setCor(carros.getCor());
		
		return carrosViewVO;
	}
}
