package com.projeto.oficina.controllers;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.services.UsuariosService;
import com.projeto.oficina.vo.UsuariosVO;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;
	
	@PostMapping("/cadastro")
	public ResponseEntity<String> save(@Valid @RequestBody UsuariosVO novoUsuarioVO) throws GlobalException, MessagingException {
		HttpHeaders headers = new HttpHeaders();
		usuariosService.save(novoUsuarioVO);
		return new ResponseEntity<>("Email de ativacao enviado!", headers, HttpStatus.OK);
	}
	
	@PutMapping("/ativaremail")
	public ResponseEntity<String> ativarEmail(@RequestParam String chave, @RequestParam String email) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();
		usuariosService.ativaremail(chave, email);
		return new ResponseEntity<>("Email ativado com sucesso!", headers, HttpStatus.OK);
	}
}
