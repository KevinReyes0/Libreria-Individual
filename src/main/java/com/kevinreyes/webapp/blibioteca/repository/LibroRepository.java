package com.kevinreyes.webapp.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinreyes.webapp.blibioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
