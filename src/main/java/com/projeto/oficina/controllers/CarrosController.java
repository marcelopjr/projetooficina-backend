package com.projeto.oficina.controllers;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.services.CarrosService;
import com.projeto.oficina.vo.CarrosVO;

@RestController
@RequestMapping("/carros")
public class CarrosController {
	
	@Autowired
	private CarrosService carrosService;
	
	@GetMapping("/existeplaca")
	public ResponseEntity<?> existPlaca(@RequestParam String placa){
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(carrosService.existPlaca(placa), headers, HttpStatus.OK);
	}
	
	@PostMapping("/novocarro")
	public ResponseEntity<String> save(@Valid @RequestBody CarrosVO novoCarroVO) throws GlobalException, MessagingException {
		HttpHeaders headers = new HttpHeaders();
		carrosService.save(novoCarroVO);
		return new ResponseEntity<>("Veiculo cadastrado com sucesso!", headers, HttpStatus.OK);
	}
}
