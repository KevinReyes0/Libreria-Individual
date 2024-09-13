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

import com.kevinreyes.webapp.blibioteca.model.Prestamo;
import com.kevinreyes.webapp.blibioteca.service.PrestamoService;
import com.kevinreyes.webapp.blibioteca.utils.MethodType;

@Controller
@RestController
@RequestMapping("")
public class PrestamoController {
    
    @Autowired
    PrestamoService prestamoService;

    @GetMapping ("/prestamos")
    public ResponseEntity<List<Prestamo>> listarPrestamo(){
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamo(@RequestParam Long id){
        try {
            return ResponseEntity.ok(prestamoService.buscarPrestamo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping ("/prestamo")
    public ResponseEntity <Map<String, String>> agregarPrestamo(@RequestBody Prestamo prestamo){
        Map<String, String> response  = new HashMap<>();
        try {
            prestamoService.guardarPrestamo(prestamo, MethodType.POST);
            response.put("message", "Prestamo creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un erro al agregar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>> editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNew){
        Map<String, String> response  = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamo(id);
            prestamo.setCliente(prestamoNew.getCliente());
            prestamo.setEmpleado(prestamoNew.getEmpleado());
            prestamo.setFechaDevolucion(prestamoNew.getFechaDevolucion());
            prestamo.setFechaPrestamo(prestamoNew.getFechaPrestamo());
            prestamo.setLibros(prestamoNew.getLibros());
            prestamo.setVigencia(prestamoNew.getVigencia());
            prestamoService.guardarPrestamo(prestamo, MethodType.PUT);
            response.put("message", "prestamo modificado conexito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un erro al modificar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map <String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamo(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("message", "Prestamo eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El prestamo no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
