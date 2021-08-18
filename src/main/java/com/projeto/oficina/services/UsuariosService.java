package com.projeto.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Usuarios;
import com.projeto.oficina.repositories.UsuariosRepository;
import com.projeto.oficina.vo.UsuariosVO;
import com.projeto.oficina.vo.UsuariosViewVO;

@Service
public class UsuariosService {
	
	@Autowired
	UsuariosRepository usuarioRepository;
	
	public UsuariosViewVO findById(Integer id) {
		return ConverteEntidadeParaView(usuarioRepository.findById(id).get());
	}
	
	public UsuariosVO ConverteEntidadeParaVo(Usuarios usuario) {
		UsuariosVO usuariosVO = new UsuariosVO();
		
		return usuariosVO;
	}
	
	public Usuarios ConverteVoParaEntidade(UsuariosVO usuarioVO) {
		Usuarios usuarios = new Usuarios();
		
		return usuarios;
	}
	
	public UsuariosViewVO ConverteEntidadeParaView(Usuarios usuario) {
		UsuariosViewVO usuariosViewVO = new UsuariosViewVO();
		
		return usuariosViewVO;
	}

}
