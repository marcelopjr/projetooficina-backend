package com.projeto.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.oficina.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
