package com.kevinreyes.webapp.blibioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kevinreyes.webapp.blibioteca.model.Cliente;
import com.kevinreyes.webapp.blibioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping(value = "")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarClientes(@RequestParam Long dpi){
        try {
            return ResponseEntity.ok(clienteService.buscarClientes(dpi));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, Boolean>> guardarClientes(@RequestBody Cliente cliente){
        Map<String, Boolean> response = new HashMap<>();
        try {
            clienteService.guardarClientes(cliente);
            response.put("message", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long dpi, @RequestBody Cliente clienteNuevo){
        Map <String, String> response = new HashMap<>();
        try {
            Cliente clienteAnt = clienteService.buscarClientes(dpi);
            clienteAnt.setNombreCliente(clienteNuevo.getNombreCliente());
            clienteAnt.setApellidoCliente(clienteNuevo.getApellidoCliente());
            clienteAnt.setTelefonoCliente(clienteNuevo.getTelefonoCliente());
            clienteService.guardarClientes(clienteAnt);
            response.put("message", "Cliente editada con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El Cliente no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarClientes(@RequestParam Long dpi){
        Map <String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientes(dpi);
            clienteService.eliminarClientes(cliente);
            response.put("message", "Cliente eliminda con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "El cliente no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }


}
