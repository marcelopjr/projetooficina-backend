package com.projeto.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.oficina.entities.OrdemServicos_Status;

@Repository
public interface OrdemServicos_StatusRepository extends JpaRepository<OrdemServicos_Status, Integer> {

}
