package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import com.kevinreyes.webapp.blibioteca.model.Prestamo;

public interface IPrestamoService {
    public List<Prestamo> listarPrestamos();
    
    public Prestamo buscarPrestamo(Long id);

    public Prestamo guardarPrestamo(Prestamo  prestamo);

    public void eliminarPrestamo(Prestamo prestamo);
}
