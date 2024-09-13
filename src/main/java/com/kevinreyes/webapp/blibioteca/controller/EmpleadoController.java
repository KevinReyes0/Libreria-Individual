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

import com.kevinreyes.webapp.blibioteca.model.Empleado;

import com.kevinreyes.webapp.blibioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleados(@RequestParam Long id){
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleados(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, Boolean>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String, Boolean> response = new HashMap<>();
        try {
            if(empleadoService.guardarEmpleados(empleado)){
                response.put("message", Boolean.TRUE);
                return ResponseEntity.ok(response);
            }else {
                response.put("message", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("err", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNew){
        Map <String, String> response = new HashMap<>();
        try {
            Empleado empleadoAnt = empleadoService.buscarEmpleados(id);
            empleadoAnt.setNombre(empleadoNew.getNombre());
            empleadoAnt.setApellido(empleadoNew.getApellido()); 
            empleadoAnt.setTelefono(empleadoNew.getTelefono());
            empleadoAnt.setDireccion(empleadoNew.getDireccion());
            empleadoAnt.setDpi(empleadoNew.getDpi());
            if(empleadoService.guardarEmpleados(empleadoAnt)){
                response.put("message", "Cliente editada con exito");
                return ResponseEntity.ok(response);
            }else {
                response.put("message", "El Cliente no se pudo editar");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("message", "El Cliente no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpleados(@RequestParam Long id){
        Map <String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleados(id);
            empleadoService.eliminarEmpleados(empleado);
            response.put("message", "Cliente eliminda con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El cliente no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
