package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import com.kevinreyes.webapp.blibioteca.model.Empleado;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleados(Long id);

    public Boolean guardarEmpleados(Empleado empleado);

    public void eliminarEmpleados(Empleado empleado);

    public Boolean verificarDpiDuplicado(Empleado empleadoNew);
}