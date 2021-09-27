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
	

	@PutMapping("/solicitartrocadesenha")
	public ResponseEntity<?> solicitarTrocaDeSenha(@RequestParam String email) throws GlobalException, MessagingException{
		HttpHeaders headers = new HttpHeaders();
		usuariosService.solicitarTrocaDeSenha(email);
		return new ResponseEntity<>("Email para troca de senha enviado!", headers, HttpStatus.OK);
	}
	
	@GetMapping("/validartrocadesenha")
	public ResponseEntity<?> validarTrocaSenha(@RequestParam String chave, @RequestParam String email) throws GlobalException{
		HttpHeaders headers = new HttpHeaders();
	
		if(usuariosService.validarChaveTrocaSenha(chave, email)) {
			return new ResponseEntity<>(true, headers, HttpStatus.OK);
		} else {
			throw new GlobalException("Parece que há algum problema com o seu link, tente acessa-lo novamente ou solicitar a troca de senha de novo");
			//return new ResponseEntity<>("Parece que há algum problema com o seu link, tente acessa-lo novamente ou solicitar a troca de senha de novo", headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/trocarsenha")
	public ResponseEntity<?> trocarSenha(@RequestParam String chave, @RequestParam String email, @RequestParam String senha) throws GlobalException{
		HttpHeaders headers = new HttpHeaders();
		usuariosService.trocarSenha(chave, email, senha);
		return new ResponseEntity<>("Senha alterada com sucesso!", headers, HttpStatus.OK);
	}
	
	@GetMapping("/checaremail")
	public ResponseEntity<?> existEmail(@RequestParam String email){
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(usuariosService.existEmail(email), headers, HttpStatus.OK);
	}
	
	@GetMapping("/checarcpf")
	public ResponseEntity<?> existCpf(@RequestParam String cpf){
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(usuariosService.existCpf(cpf), headers, HttpStatus.OK);
	}
	
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
		return new ResponseEntity<>("Um Email foi enviado para ativação da conta! \n" + novoUsuarioVO.getEmail(), headers, HttpStatus.OK);
	}
	
	@PutMapping("/ativaremail")
	public ResponseEntity<String> ativarEmail(@RequestParam String chave, @RequestParam String email) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();
		usuariosService.ativaremail(chave, email);
		return new ResponseEntity<>("Email ativado com sucesso!", headers, HttpStatus.OK);
	}
}
