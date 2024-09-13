package com.kevinreyes.webapp.blibioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevinreyes.webapp.blibioteca.model.Categoria;
import com.kevinreyes.webapp.blibioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria> listaCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> guardarCategoria(@RequestBody Categoria categoria){
        Map<String, String> response = new HashMap<>();
        try {
            if(categoriaService.guardarCategoria(categoria)){
                response.put("message", "Categoria agregada con exito");
                return ResponseEntity.ok(response);
            }else {
                response.put("err", "La categoria esta duplicada");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("err", "La categoria no se pudo agregar");
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map <String, String> response = new HashMap<>();
        try {
            Categoria categoriaAnt = categoriaService.buscarCategoriaPorId(id);
            categoriaAnt.setNombreCategoria(categoriaNueva.getNombreCategoria());
            if(categoriaService.guardarCategoria(categoriaAnt)){
                response.put("message", "Categoria editada con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "La categoria no se pudo editar");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("message", "La categoria no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map <String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Categoria eliminda con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "La categoria no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
