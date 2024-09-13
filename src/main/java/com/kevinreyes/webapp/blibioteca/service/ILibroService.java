package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;


import com.kevinreyes.webapp.blibioteca.model.Libro;

public interface ILibroService {

     public List<Libro> listarLibros();

    public Libro buscarLibros(Long id);

    public Libro guardarLibros(Libro libro);

    public void eliminarLibros(Libro libro);
}
