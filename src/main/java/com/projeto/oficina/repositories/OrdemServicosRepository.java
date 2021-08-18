package com.projeto.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.oficina.entities.OrdemServicos;

@Repository
public interface OrdemServicosRepository extends JpaRepository<OrdemServicos, Integer> {

}
