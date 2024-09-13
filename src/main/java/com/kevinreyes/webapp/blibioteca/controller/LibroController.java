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

import com.kevinreyes.webapp.blibioteca.model.Libro;
import com.kevinreyes.webapp.blibioteca.service.LibroService;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<Libro> buscarLibros(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibros(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String, Boolean>> guardarLibros(@RequestBody Libro libro){
        Map<String, Boolean> response = new HashMap<>();
        try {
            libroService.guardarLibros(libro);
            response.put("message", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long id, @RequestBody Libro libroNuevo){
        Map <String, String> response = new HashMap<>();
        try {
            Libro LibroAnt = libroService.buscarLibros(id);
            LibroAnt.setIsbn(libroNuevo.getIsbn());
            LibroAnt.setNombre(libroNuevo.getNombre()); 
            LibroAnt.setSinopsis(libroNuevo.getSinopsis());
            LibroAnt.setAutor(libroNuevo.getAutor());
            LibroAnt.setEditorial(libroNuevo.getEditorial());
            LibroAnt.setDisponibilidad(libroNuevo.getDisponibilidad());
            LibroAnt.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            LibroAnt.setCluster(libroNuevo.getCluster());
            LibroAnt.setCategoria(libroNuevo.getCategoria());
            libroService.guardarLibros(LibroAnt);
            response.put("message", "Cliente editada con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El Cliente no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>> eliminarLibros(@RequestParam Long id){
        Map <String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibros(id);
            libroService.eliminarLibros(libro);
            response.put("message", "Cliente eliminda con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El cliente no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }


}
