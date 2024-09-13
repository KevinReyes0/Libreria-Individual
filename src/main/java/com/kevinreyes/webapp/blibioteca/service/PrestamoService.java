package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinreyes.webapp.blibioteca.model.Prestamo;
import com.kevinreyes.webapp.blibioteca.repository.PrestamoRepository;
import com.kevinreyes.webapp.blibioteca.utils.MethodType;

@Service
public class PrestamoService implements IPrestamoService{

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamo(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo, MethodType methodType) {
        if(methodType.equals(MethodType.POST)){
            return prestamoRepository.save(prestamo);
        }else if (methodType.equals(MethodType.PUT)){
            return prestamoRepository.save(prestamo);
        }else {
            return prestamoRepository.save(null);
        }
        
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

}
