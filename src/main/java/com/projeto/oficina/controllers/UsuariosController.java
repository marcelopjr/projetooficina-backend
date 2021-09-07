package com.projeto.oficina.controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.services.UsuariosService;
import com.projeto.oficina.vo.CarrosViewVO;
import com.projeto.oficina.vo.OrdemServicosViewVO;
import com.projeto.oficina.vo.UsuariosVO;
import com.projeto.oficina.vo.UsuariosViewVO;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;
	
	@GetMapping("/minhasinfos")
	public ResponseEntity<UsuariosViewVO>MinhasInfos(){
		HttpHeaders headers = new HttpHeaders();
		UsuariosViewVO usuario = usuariosService.minhaInfos();
		return new ResponseEntity<>(usuario, headers, HttpStatus.OK);
	}
	
	@GetMapping("/meuscarros")
	public ResponseEntity<List<CarrosViewVO>>MeusCarros(){
		HttpHeaders headers = new HttpHeaders();
		List<CarrosViewVO> meusCarros = usuariosService.meusCarros();
		return new ResponseEntity<>(meusCarros, headers, HttpStatus.OK);
	}
	
	@GetMapping("/minhasos")
	public ResponseEntity<List<OrdemServicosViewVO>>MinhasOS(){
		HttpHeaders headers = new HttpHeaders();
		List<OrdemServicosViewVO> minhasOS = usuariosService.minhaOS();
		return new ResponseEntity<>(minhasOS, headers, HttpStatus.OK);
	}
	
	@GetMapping("/minhasosabertas")
	public ResponseEntity<List<OrdemServicosViewVO>>MinhasOSAbertas(){
		HttpHeaders headers = new HttpHeaders();
		List<OrdemServicosViewVO> minhasOSAbertas = usuariosService.minhaOSAbertas();
		return new ResponseEntity<>(minhasOSAbertas, headers, HttpStatus.OK);
	}
	
	@GetMapping("/minhasosfechadas")
	public ResponseEntity<List<OrdemServicosViewVO>>MinhasOSFechadas(){
		HttpHeaders headers = new HttpHeaders();
		List<OrdemServicosViewVO> minhasOSFechadas = usuariosService.minhaOSFechadas();
		return new ResponseEntity<>(minhasOSFechadas, headers, HttpStatus.OK);
	}
	
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
