package com.projeto.oficina.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.oficina.entities.OrdemServicos;
import com.projeto.oficina.entities.OrdemServicos_Acoes;
import com.projeto.oficina.entities.OrdemServicos_Status;
import com.projeto.oficina.entities.pk.OrdemServicos_Acoes_Pk;
import com.projeto.oficina.entities.pk.OrdemServicos_Status_Pk;
import com.projeto.oficina.exception.GlobalException;
import com.projeto.oficina.repositories.OrdemServicosRepository;
import com.projeto.oficina.repositories.OrdemServicos_AcoesRepository;
import com.projeto.oficina.repositories.OrdemServicos_StatusRepository;
import com.projeto.oficina.vo.AcoesViewVO;
import com.projeto.oficina.vo.OrdemServicosVO;
import com.projeto.oficina.vo.OrdemServicosViewVO;
import com.projeto.oficina.vo.StatusViewVO;

@Service
public class OrdemServicosService {

	@Autowired
	OrdemServicosRepository ordemServicosRepository;

	@Autowired
	OrdemServicos_StatusRepository ordemServicos_StatusRepository;

	@Autowired
	OrdemServicos_AcoesRepository ordemServicos_AcoesRepository;

	@Autowired
	CarrosService carrosService;

	@Autowired
	UsuariosService usuariosService;

	@Autowired
	StatusService statusService;

	@Autowired
	AcoesService acoesService;

	public OrdemServicosViewVO findById(Integer id) {
		return converteEntidadeParaView(ordemServicosRepository.findById(id).get());
	}

	public OrdemServicos save(OrdemServicosVO ordemServicosVO) throws GlobalException {

		boolean aptoCriarNovaOS = true;

		if (!usuariosService.GetUsuarioAutenticado().getListaOrdemServicos().isEmpty()) {
			for (OrdemServicos lOrdemServicos : usuariosService.GetUsuarioAutenticado().getListaOrdemServicos()) {
				if (lOrdemServicos.isOs_aberta()) {
					aptoCriarNovaOS = false;
				}
			}
		}

		if (aptoCriarNovaOS) {

			OrdemServicos novaOS = converteVoParaEntidade(ordemServicosVO);

			novaOS.setUsuario(usuariosService.GetUsuarioAutenticado());
			novaOS.setData_abertura(LocalDate.now());
			novaOS.setOs_aberta(true);

			OrdemServicos novaOSBanco = ordemServicosRepository.save(novaOS);

			alterarStatus(1, novaOSBanco.getId());

			return ordemServicosRepository.save(novaOSBanco);
		}

		else {
			throw new GlobalException("Voce ja possue uma OS em Aberto");
		}

	}

	public OrdemServicos_Status alterarStatus(Integer idStatus, Integer idOs) {

		OrdemServicos_Status_Pk novoStatusPk = new OrdemServicos_Status_Pk();
		OrdemServicos_Status novoStatus = new OrdemServicos_Status();
		OrdemServicos ordemServicosAtt = ordemServicosRepository.findById(idOs).get();

		novoStatusPk.setOrdemservicos(ordemServicosAtt);
		novoStatusPk.setStatus(statusService.findById(idStatus));

		novoStatus.setId(novoStatusPk);
		novoStatus.setData(LocalDate.now());

		ordemServicos_StatusRepository.save(novoStatus);

		ordemServicosAtt.setStatus_atual(novoStatus.getId().getStatus());

		if (idStatus == 6 || idStatus == 7) {
			ordemServicosAtt.setOs_aberta(false);
			ordemServicosAtt.setData_finalizada(LocalDate.now());
		}

		ordemServicosRepository.save(ordemServicosAtt);

		return novoStatus;
	}

	public OrdemServicos_Status alterarStatus(Integer idStatus, Integer idOs, String mensagem) {

		OrdemServicos_Status_Pk novoStatusPk = new OrdemServicos_Status_Pk();
		OrdemServicos_Status novoStatus = new OrdemServicos_Status();
		OrdemServicos ordemServicosAtt = ordemServicosRepository.findById(idOs).get();

		novoStatusPk.setOrdemservicos(ordemServicosAtt);
		novoStatusPk.setStatus(statusService.findById(idStatus));

		novoStatus.setId(novoStatusPk);
		novoStatus.setMensagem(mensagem);
		novoStatus.setData(LocalDate.now());

		ordemServicos_StatusRepository.save(novoStatus);

		ordemServicosAtt.setStatus_atual(novoStatus.getId().getStatus());

		if (idStatus == 6 || idStatus == 7) {
			ordemServicosAtt.setOs_aberta(false);
			ordemServicosAtt.setData_finalizada(LocalDate.now());
		}

		ordemServicosRepository.save(ordemServicosAtt);

		return novoStatus;
	}

//	public OrdemServicos_Acoes adicionarAcoes(Integer idAcoes, Integer idOs) {
//		
//		OrdemServicos_Acoes_Pk novaAcaoPk = new OrdemServicos_Acoes_Pk();
//		OrdemServicos_Acoes novaAcao = new OrdemServicos_Acoes();
//		OrdemServicos ordemServicosAtt = ordemServicosRepository.findById(idOs).get();
//			
//		novaAcaoPk.setOrdemservicos(ordemServicosAtt);
//		novaAcaoPk.setAcoes(acoesService.findById(idAcoes));
//		
//		novaAcao.setId(novaAcaoPk);
//		novaAcao.setData(LocalDate.now());
//		
//		return ordemServicos_AcoesRepository.save(novaAcao);
//	}

	public OrdemServicos_Acoes adicionarAcoes(Integer idAcoes, Integer idOs, String nome_peca) throws GlobalException {

		OrdemServicos ordemServicosAtt = ordemServicosRepository.findById(idOs).get();

		if (ordemServicosAtt.getStatus_atual().getId() == 4) {

			OrdemServicos_Acoes_Pk novaAcaoPk = new OrdemServicos_Acoes_Pk();
			OrdemServicos_Acoes novaAcao = new OrdemServicos_Acoes();

			novaAcaoPk.setOrdemservicos(ordemServicosAtt);
			novaAcaoPk.setAcoes(acoesService.findById(idAcoes));

			novaAcao.setId(novaAcaoPk);
			novaAcao.setNome_peca(nome_peca);
			novaAcao.setData(LocalDate.now());

			return ordemServicos_AcoesRepository.save(novaAcao);

		} else {
			throw new GlobalException("OS informada não está em reparos, verifique o status!");
		}
	}

	public OrdemServicos converteVoParaEntidade(OrdemServicosVO ordemServicosVO) {
		OrdemServicos ordemServicos = new OrdemServicos();

		ordemServicos.setData_entrega_carro(LocalDate.parse(ordemServicosVO.getData_entrega_carro()));
		ordemServicos.setCarro(carrosService.findById(ordemServicosVO.getCarro_id()));

		return ordemServicos;
	}

	public OrdemServicosVO converteEntidadeParaVO() {
		return null;
	}

	public OrdemServicosViewVO converteEntidadeParaView(OrdemServicos ordemServicos) {
		OrdemServicosViewVO ordemServicosViewVO = new OrdemServicosViewVO();

		ordemServicosViewVO.setId(ordemServicos.getId());
		ordemServicosViewVO.setValor(ordemServicos.getValor() != null ? ordemServicos.getValor() : null);
		ordemServicosViewVO.setCarro(carrosService.converteEntidadeParaViewVO(ordemServicos.getCarro()));
		ordemServicosViewVO.setStatus_atual(ordemServicos.getStatus_atual().getNome());
		ordemServicosViewVO.setData_abertura(ordemServicos.getData_abertura());
		ordemServicosViewVO.setData_entrega_carro(ordemServicos.getData_entrega_carro());
		ordemServicosViewVO.setData_finalizada(
				ordemServicos.getData_finalizada() != null ? ordemServicos.getData_finalizada() : null);
		ordemServicosViewVO.setOs_aberta(ordemServicos.isOs_aberta());

		if (!ordemServicos.getListaStatus().isEmpty()) {

			List<StatusViewVO> listaStatus = new ArrayList<>();

			for (OrdemServicos_Status lOrdemServicos_Status : ordemServicos.getListaStatus()) {
				listaStatus.add(statusService.converteEntidadeParaViewVO(lOrdemServicos_Status));
			}

			ordemServicosViewVO.setListaStatus(listaStatus);

		}

		if (!ordemServicos.getListaAcoes().isEmpty()) {

			List<AcoesViewVO> listaAcoes = new ArrayList<>();

			for (OrdemServicos_Acoes lOrdemServicos_Acoes : ordemServicos.getListaAcoes()) {
				listaAcoes.add(acoesService.converteEntidadeParaViewVO(lOrdemServicos_Acoes));
			}

			ordemServicosViewVO.setListaAcoes(listaAcoes);

		}

		return ordemServicosViewVO;
	}
}
