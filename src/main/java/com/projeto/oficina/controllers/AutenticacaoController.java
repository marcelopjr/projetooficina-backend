package com.projeto.oficina.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto.oficina.entities.Usuarios;
import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.repositories.UsuariosRepository;
import com.projeto.oficina.vo.LoginVO;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@PostMapping()
	public ResponseEntity<?> Login(@Valid @RequestBody LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		HttpHeaders headers = new HttpHeaders();

		try {

			request.login(loginVO.getEmail(), loginVO.getSenha());

			
			Usuarios usuarioLogando = usuariosRepository.findByEmail(loginVO.getEmail());
			
			if(!usuarioLogando.isEmailAtivado()) {
				throw new GlobalException("Por favor confirme seu e-mail!");
			}
			
			Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

			User user = (User) authentication.getPrincipal();

//			String login = user.getUsername();

			String jwt = JWT.create().withClaim("email", usuarioLogando.getEmail()).withClaim("role", usuarioLogando.getTipo())
					.withClaim("nome", usuarioLogando.getNome()).withClaim("Cpf", usuarioLogando.getCpf())
					.sign(Algorithm.HMAC256("ChaveOficinaJWT"));

		
			return new ResponseEntity<String>(jwt, headers, HttpStatus.OK);

		} catch (ServletException e) {

			throw new GlobalException("Login ou senha inválidos!");

		}

	}

}
