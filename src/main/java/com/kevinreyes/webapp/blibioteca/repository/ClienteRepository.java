package com.kevinreyes.webapp.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinreyes.webapp.blibioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
