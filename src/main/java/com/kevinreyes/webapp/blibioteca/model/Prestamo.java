package com.kevinreyes.webapp.blibioteca.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "Prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Boolean vigencia;
    @ManyToOne(fetch = FetchType.EAGER)
    private Empleado empleado;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prestamos_libros",
    joinColumns = @JoinColumn(name = "prestamos_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "libros_id", referencedColumnName = "id"))
    private List<Libro> libros ;
}
