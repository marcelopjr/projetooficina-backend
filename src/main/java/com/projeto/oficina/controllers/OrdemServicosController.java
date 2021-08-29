package com.projeto.oficina.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.services.OrdemServicosService;
import com.projeto.oficina.vo.OrdemServicosVO;

@RestController
@RequestMapping("/ordemservicos")
public class OrdemServicosController {

	@Autowired
	private OrdemServicosService ordemServicosService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(ordemServicosService.findById(id), headers, HttpStatus.OK);
	}

	@PostMapping("/novaos")
	public ResponseEntity<?> novaOS(@Valid @RequestBody OrdemServicosVO ordemServicosVO) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(ordemServicosService.save(ordemServicosVO), headers, HttpStatus.OK);
	}

	@PostMapping("/attstatus/{idOs}/{idStatus}")
	public ResponseEntity<?> atualizarStatusOS(@PathVariable Integer idOs, @PathVariable Integer idStatus,
			@RequestParam(required = false) String mensagem) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();

		if (mensagem != null) {
			ordemServicosService.alterarStatus(idStatus, idOs, mensagem);
		} else {
			ordemServicosService.alterarStatus(idStatus, idOs);
		}

		return new ResponseEntity<>("Status Atualizado", headers, HttpStatus.OK);

	}

	@PostMapping("/addacoes/{idOs}/{idAcoes}")
	public ResponseEntity<?> adicionarAcoes(@PathVariable Integer idOs, @PathVariable Integer idAcoes,
			@RequestParam(required = false) String peca) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();
		ordemServicosService.adicionarAcoes(idAcoes, idOs, peca);
		return new ResponseEntity<>("Ação adicionada", headers, HttpStatus.OK);
	}

}
