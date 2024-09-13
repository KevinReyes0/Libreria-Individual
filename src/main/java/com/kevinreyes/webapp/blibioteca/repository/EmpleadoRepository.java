package com.kevinreyes.webapp.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinreyes.webapp.blibioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
}
