package com.projeto.oficina.services;

import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Usuarios;
import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.repositories.UsuariosRepository;
import com.projeto.oficina.vo.UsuariosVO;
import com.projeto.oficina.vo.UsuariosViewVO;

@Service
public class UsuariosService {
	
	@Autowired
	UsuariosRepository usuarioRepository;
	
	@Autowired
	EmailService emailService;
	
	public UsuariosViewVO findById(Integer id) {
		return ConverteEntidadeParaView(usuarioRepository.findById(id).get());
	}
	
	public Usuarios save(UsuariosVO novoUsuarioVO) throws MessagingException, GlobalException {
		Usuarios novoUsuario = ConverteVoParaEntidade(novoUsuarioVO);
		
		novoUsuario.setChaveAtivarEmail(gerarChave());
		usuarioRepository.save(novoUsuario);
		emailService.emailCadastro(novoUsuario);
		return novoUsuario;
	}
	
	public boolean ativaremail(String chave, String email) throws GlobalException {
		
		Usuarios usuarios = usuarioRepository.findByEmail(email);
		
		if(usuarios.getChaveAtivarEmail().equals(chave) && !usuarios.isEmailAtivado()) {
			usuarios.setEmailAtivado(true);
			usuarios.setChaveAtivarEmail(null);
			usuarioRepository.save(usuarios);
			return true;
		}
		else {
			throw new GlobalException("Chave invalida ou e-mail ja ativado");
		}
	}
	
	public UsuariosVO ConverteEntidadeParaVo(Usuarios usuario) {
		UsuariosVO usuariosVO = new UsuariosVO();
		
		return usuariosVO;
	}
	
	public Usuarios ConverteVoParaEntidade(UsuariosVO usuarioVO) {
		Usuarios usuarios = new Usuarios();
		
		usuarios.setNome(usuarioVO.getNome());
		usuarios.setCpf(usuarioVO.getCpf());
		usuarios.setTelefone(usuarioVO.getTelefone());
		usuarios.setEmail(usuarioVO.getEmail());
		usuarios.setSenha(new BCryptPasswordEncoder().encode(usuarioVO.getSenha()));
		usuarios.setTipo(usuarioVO.getTipo());
		
		return usuarios;
	}
	
	public UsuariosViewVO ConverteEntidadeParaView(Usuarios usuario) {
		UsuariosViewVO usuariosViewVO = new UsuariosViewVO();
		
		return usuariosViewVO;
	}
	
    private String gerarChave() {
        char[] vet = new char[10];
        for(int i = 0; i < 10; i++) {
            vet[i] = randomChar(); 
         }    
        return new String(vet);
    }
    
    
    private char randomChar() {
    	 Random rand = new Random();
    	 
        int opcao = rand.nextInt(3); 
        if(opcao == 0) { 
            return(char) (rand.nextInt(10) + 48); 
            
        }
        else if(opcao == 1) { 
            return(char) (rand.nextInt(26) + 65); 
            
        } else {
            return(char) (rand.nextInt(26) + 97); 
            
        }
    }

}
