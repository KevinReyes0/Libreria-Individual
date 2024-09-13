package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinreyes.webapp.blibioteca.model.Cliente;
import com.kevinreyes.webapp.blibioteca.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @Override 
    public Cliente buscarClientes(Long dpi){
        return clienteRepository.findById(dpi).orElse(null);
    }

    @Override
    public Cliente guardarClientes(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override 
    public void eliminarClientes(Cliente cliente){
        clienteRepository.delete(cliente);
    }

}
