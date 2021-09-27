package com.projeto.oficina.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.Carros;
import com.projeto.oficina.entities.OrdemServicos;
import com.projeto.oficina.entities.Usuarios;
import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.repositories.UsuariosRepository;
import com.projeto.oficina.vo.CarrosViewVO;
import com.projeto.oficina.vo.OrdemServicosViewVO;
import com.projeto.oficina.vo.UsuariosVO;
import com.projeto.oficina.vo.UsuariosViewVO;

@Service
public class UsuariosService {

	@Autowired
	UsuariosRepository usuariosRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	CarrosService carrosService;

	@Autowired
	OrdemServicosService ordemServicosService;

	public Usuarios GetUsuarioAutenticado() {
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		String login = (String) authentication.getPrincipal();
		Usuarios usuario = usuariosRepository.findByEmail(login);
		return usuario;
	}
	
	
	public void solicitarTrocaDeSenha(String email) throws GlobalException, MessagingException {
		
		if(usuariosRepository.existsByEmail(email)) {
			Usuarios usuario = usuariosRepository.findByEmail(email);
			
			usuario.setChaveRecuperarSenha(gerarChave(20));
			usuariosRepository.save(usuario);
			emailService.emailNovaSenha(usuario);
			
		}else {
			throw new GlobalException("E-mail inválido!");
		}
		
		
	}
	
	
	public boolean validarChaveTrocaSenha(String chave, String email) throws GlobalException {
		
		if(usuariosRepository.existsByEmail(email)) {
			
			Usuarios usuario = usuariosRepository.findByEmail(email);
			
			if(usuario.getChaveRecuperarSenha() != null && usuario.getChaveRecuperarSenha().equals(chave)) {
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
			//throw new GlobalException("Parece que há algum problema com o seu link, tente acessa-lo novamente ou solicitar a troca de senha de novo");
		}
		
	}
	
	public boolean trocarSenha(String chave, String email, String senha) throws GlobalException {
		
		if(validarChaveTrocaSenha(chave, email)) {
			Usuarios usuario = usuariosRepository.findByEmail(email);
			
			usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
			usuario.setChaveRecuperarSenha(null);
			usuariosRepository.save(usuario);
			return true;
		}else {
			throw new GlobalException("Tente acessar o link do seu email novamente ou solicitar a troca de senha de novo");
		}
		
		
	}
	

	public boolean existEmail(String email) {
		boolean existe = usuariosRepository.existsByEmail(email);
		
		if (existe) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean existCpf(String cpf) {
		boolean existe = usuariosRepository.existsByCpf(desformatarCpf(cpf));
		
		if (existe) {
			return true;
		} else {
			return false;
		}

	}

	public UsuariosViewVO findById(Integer id) {
		return ConverteEntidadeParaView(usuariosRepository.findById(id).get());
	}

	public UsuariosViewVO minhaInfos() {
		return ConverteEntidadeParaView(GetUsuarioAutenticado());
	}

	public List<CarrosViewVO> meusCarros() {
		List<CarrosViewVO> meusCarros = new ArrayList<>();
		Usuarios usuario = GetUsuarioAutenticado();

		if (!usuario.getListaCarros().isEmpty()) {

			for (Carros lCarros : usuario.getListaCarros()) {
				meusCarros.add(carrosService.converteEntidadeParaViewVO(lCarros));
			}

		}

		return meusCarros;
	}

	public List<OrdemServicosViewVO> minhaOS() {
		List<OrdemServicosViewVO> minhasOS = new ArrayList<>();
		Usuarios usuario = GetUsuarioAutenticado();

		if (!usuario.getListaOrdemServicos().isEmpty()) {

			for (OrdemServicos lOrdemServicos : usuario.getListaOrdemServicos()) {
				minhasOS.add(ordemServicosService.converteEntidadeParaView(lOrdemServicos));
			}

		}

		return minhasOS;
	}

	public List<OrdemServicosViewVO> minhaOSAbertas() {
		List<OrdemServicosViewVO> minhasOSAbertas = new ArrayList<>();
		Usuarios usuario = GetUsuarioAutenticado();

		if (!usuario.getListaOrdemServicos().isEmpty()) {

			for (OrdemServicos lOrdemServicos : usuario.getListaOrdemServicos()) {
				if (lOrdemServicos.isOs_aberta()) {
					minhasOSAbertas.add(ordemServicosService.converteEntidadeParaView(lOrdemServicos));
				}
			}

		}

		return minhasOSAbertas;
	}

	public List<OrdemServicosViewVO> minhaOSFechadas() {
		List<OrdemServicosViewVO> minhasOSFechadas = new ArrayList<>();
		Usuarios usuario = GetUsuarioAutenticado();

		if (!usuario.getListaOrdemServicos().isEmpty()) {

			for (OrdemServicos lOrdemServicos : usuario.getListaOrdemServicos()) {
				if (!lOrdemServicos.isOs_aberta()) {
					minhasOSFechadas.add(ordemServicosService.converteEntidadeParaView(lOrdemServicos));
				}
			}

		}

		return minhasOSFechadas;
	}

	public Usuarios save(UsuariosVO novoUsuarioVO) throws MessagingException, GlobalException {
		Usuarios novoUsuario = ConverteVoParaEntidade(novoUsuarioVO);

		novoUsuario.setChaveAtivarEmail(gerarChave(20));
		usuariosRepository.save(novoUsuario);
		emailService.emailCadastro(novoUsuario);
		return novoUsuario;
	}

	public boolean ativaremail(String chave, String email) throws GlobalException {

		if (usuariosRepository.existsByEmail(email)) {
			Usuarios usuarios = usuariosRepository.findByEmail(email);

			if (!usuarios.isEmailAtivado() && usuarios.getChaveAtivarEmail().equals(chave) ) {
				usuarios.setEmailAtivado(true);
				usuarios.setChaveAtivarEmail(null);
				usuariosRepository.save(usuarios);
				return true;
			} else {
				throw new GlobalException("Chave invalida ou conta ja ativado");
			}
		} else {
			throw new GlobalException("Tente acessar link enviado para seu e-mail novamente");
		}

	}

	public UsuariosVO ConverteEntidadeParaVo(Usuarios usuario) {
		UsuariosVO usuariosVO = new UsuariosVO();

		return usuariosVO;
	}

	public Usuarios ConverteVoParaEntidade(UsuariosVO usuarioVO) {
		Usuarios usuarios = new Usuarios();

		usuarios.setNome(usuarioVO.getNome());
		usuarios.setCpf(desformatarCpf(usuarioVO.getCpf()));
		usuarios.setTelefone(desformatarTelefone(usuarioVO.getTelefone()));
		usuarios.setEmail(usuarioVO.getEmail());
		usuarios.setSenha(new BCryptPasswordEncoder().encode(usuarioVO.getSenha()));
		usuarios.setTipo("ROLE_USER");

		return usuarios;
	}

	public UsuariosViewVO ConverteEntidadeParaView(Usuarios usuario) {
		UsuariosViewVO usuariosViewVO = new UsuariosViewVO();

		usuariosViewVO.setId(usuario.getId());
		usuariosViewVO.setNome(usuario.getNome());
		usuariosViewVO.setCpf(usuario.getCpf());
		usuariosViewVO.setTelefone(usuario.getTelefone());
		usuariosViewVO.setEmail(usuario.getEmail());

		if (!usuario.getListaCarros().isEmpty()) {
			List<CarrosViewVO> listaCarrosView = new ArrayList<>();

			for (Carros lCarros : usuario.getListaCarros()) {
				listaCarrosView.add(carrosService.converteEntidadeParaViewVO(lCarros));
			}
			usuariosViewVO.setListaCarros(listaCarrosView);
		}

		if (!usuario.getListaOrdemServicos().isEmpty()) {
			List<OrdemServicosViewVO> listaOrdemServicosView = new ArrayList<>();

			for (OrdemServicos lOrdemServicos : usuario.getListaOrdemServicos()) {
				listaOrdemServicosView.add(ordemServicosService.converteEntidadeParaView(lOrdemServicos));
			}
			usuariosViewVO.setListaOrdemServicos(listaOrdemServicosView);
		}

		return usuariosViewVO;
	}

	public String desformatarCpf(String cpf) {

		if (cpf.length() > 11) {
			String cpfDesformatado = cpf.replace(".", "");
			cpfDesformatado = cpfDesformatado.replace("-", "");

			return cpfDesformatado;

		} else {
			return cpf;
		}

	}

	public String desformatarTelefone(String telefone) {

		String telefoneDesformatado = telefone.replace(" ", "");
		telefoneDesformatado = telefoneDesformatado.replace("-", "");

		return telefoneDesformatado;

	}

	private String gerarChave(int tamanho) {
		char[] vet = new char[tamanho];
		for (int i = 0; i < tamanho; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		Random rand = new Random();

		int opcao = rand.nextInt(3);
		if (opcao == 0) {
			return (char) (rand.nextInt(10) + 48);

		} else if (opcao == 1) {
			return (char) (rand.nextInt(26) + 65);

		} else {
			return (char) (rand.nextInt(26) + 97);

		}
	}

}
