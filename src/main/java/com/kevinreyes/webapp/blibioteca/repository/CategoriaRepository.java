package com.kevinreyes.webapp.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinreyes.webapp.blibioteca.model.Categoria;;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
