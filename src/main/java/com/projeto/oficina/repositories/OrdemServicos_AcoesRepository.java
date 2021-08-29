package com.projeto.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.oficina.entities.OrdemServicos_Acoes;

@Repository
public interface OrdemServicos_AcoesRepository extends JpaRepository<OrdemServicos_Acoes, Integer> {

}
