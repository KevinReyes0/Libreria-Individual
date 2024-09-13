package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import com.kevinreyes.webapp.blibioteca.model.Cliente;

public interface IClienteService {

    public List<Cliente> listarClientes();

    public Cliente buscarClientes(Long dpi);

    public Cliente guardarClientes(Cliente cliente);

    public void eliminarClientes(Cliente cliente);
}
