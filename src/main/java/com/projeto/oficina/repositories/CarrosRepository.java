package com.projeto.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.oficina.entities.Carros;

@Repository
public interface CarrosRepository extends JpaRepository<Carros, Integer> {
	public boolean existsByPlaca(String placa);
}
