package com.kevinreyes.webapp.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinreyes.webapp.blibioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
