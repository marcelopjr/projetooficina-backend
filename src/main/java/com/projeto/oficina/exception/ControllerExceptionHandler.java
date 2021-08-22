package com.projeto.oficina.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> erros = new ArrayList<String>();
		for (FieldError erro : e.getBindingResult().getFieldErrors()) {
			erros.add(erro.getDefaultMessage());
		}
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos invalidos", LocalDateTime.now(),
				erros);
		return super.handleExceptionInternal(e, erroResposta, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<Object> handleCadastroException(GlobalException e) {
		List<String> erros = new ArrayList<String>();
		erros.add(e.getMessage());
		ErroResposta erroResposta = new ErroResposta(400, "Ocorreu um error", LocalDateTime.now(),
				erros);
		return ResponseEntity.badRequest().body(erroResposta);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Object> handleLoginException(LoginException e) {
		List<String> erros = new ArrayList<String>();
		erros.add(e.getMessage());
		ErroResposta erroResposta = new ErroResposta(400, "Error de login", LocalDateTime.now(),
				erros);
		return ResponseEntity.badRequest().body(erroResposta);
	}

}
