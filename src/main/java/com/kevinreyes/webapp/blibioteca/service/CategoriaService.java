package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinreyes.webapp.blibioteca.model.Categoria;
import com.kevinreyes.webapp.blibioteca.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id){
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarCategoria(Categoria categoria){
        if(!verificarCategoriaDuplicada(categoria)){
            categoriaRepository.save(categoria);
            return true;
        }else{
            return false;
        }
    }

    @Override 
    public void eliminarCategoria(Categoria categoria){
        categoriaRepository.delete(categoria);
    }

    @Override
    public Boolean verificarCategoriaDuplicada(Categoria categoriaNew) {
        List<Categoria> categorias = listarCategorias();
        Boolean flag = false;

        for (Categoria categoria : categorias) {
            if(categoriaNew.getNombreCategoria().equalsIgnoreCase(categoria.getNombreCategoria()) && !categoriaNew.getId().equals(categoria.getId())){
                return true;
            }
        }
        return flag;
    }

    
}

