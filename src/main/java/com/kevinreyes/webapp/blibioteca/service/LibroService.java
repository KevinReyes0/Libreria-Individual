package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinreyes.webapp.blibioteca.model.Libro;
import com.kevinreyes.webapp.blibioteca.repository.LibroRepository;

@Service
public class LibroService implements ILibroService{

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarLibros(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public Libro guardarLibros(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarLibros(Libro libro) {
        libroRepository.delete(libro);
    }

}
