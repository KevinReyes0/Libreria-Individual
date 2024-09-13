package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;
import com.kevinreyes.webapp.blibioteca.model.Categoria;

public interface ICategoriaService {

    public List<Categoria> listarCategorias();

    public Categoria buscarCategoriaPorId(Long id);

    public Categoria guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);
}
