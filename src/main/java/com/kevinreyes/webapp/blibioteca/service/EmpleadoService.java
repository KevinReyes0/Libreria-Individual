package com.kevinreyes.webapp.blibioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinreyes.webapp.blibioteca.model.Empleado;
import com.kevinreyes.webapp.blibioteca.repository.EmpleadoRepository;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    @Override 
    public Empleado buscarEmpleados(Long id){
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarEmpleados(Empleado empleado){
       if(!verificarDpiDuplicado(empleado)){//DPI no duplicado
        empleadoRepository.save(empleado);
        return true;
        }else {//DPI si duplicado
            return false;
        }
    }

    @Override 
    public void eliminarEmpleados(Empleado empleado){
        empleadoRepository.delete(empleado);
    }

    @Override
    public Boolean verificarDpiDuplicado(Empleado empleadoNew) {
        List<Empleado> empleados = listarEmpleados();
        Boolean flag = false;
        for (Empleado empleado : empleados) {
            if(empleado.getDpi().equals(empleadoNew.getDpi()) && !empleado.getId().equals(empleadoNew.getId())){
                flag = true; //Si existe un dpi duplicado
            }
        }
        return flag;
    }
}
