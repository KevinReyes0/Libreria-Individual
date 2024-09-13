package com.kevinreyes.webapp.blibioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevinreyes.webapp.blibioteca.model.Cliente;
import com.kevinreyes.webapp.blibioteca.model.Empleado;
import com.kevinreyes.webapp.blibioteca.service.EmpleadoService;
import com.kevinreyes.webapp.blibioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class EmpleadoControllerFX implements Initializable{

    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfApellido, tfDireccion, tfTelefono, tfDPI, tfBuscar;
    @FXML
    Button btnGuardar, btnVaciar, btnEliminar, btnBuscar;
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn collId, colNombre, colApellido, colTelefono, colDireccion, colDpi;

    @Autowired
    EmpleadoService empladoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            if (tfId.getText().isBlank()) {
                agregarEmpleado();
            }else{
                editarEmpleado();  
            }
        }else if(event.getSource() == btnVaciar){
            limpiarFormEditar();
        }else if(event.getSource() == btnEliminar){
            eliminarEmpleado();
        }
    }

    public void cargarDatos(){
        tblEmpleados.getItems().clear();
        tblEmpleados.setItems(listaEmpleados());
        collId.setCellValueFactory(new PropertyValueFactory<Empleado,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("direccion"));
        colDpi.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dpi"));
    }

 
    public ObservableList<Empleado> listaEmpleados(){
        return FXCollections.observableList(empladoService.listarEmpleados());
    }

    public void cargarFormEditar(){
        Empleado empleado =  (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
        if(empleado != null){
            tfId.setText(Long.toString(empleado.getId()));
            tfNombre.setText(empleado.getNombre());
            tfApellido.setText(empleado.getApellido());
            tfTelefono.setText(empleado.getTelefono());
            tfDireccion.setText(empleado.getDireccion());
            tfDPI.setText(empleado.getDpi());

        }
    }

    public void limpiarFormEditar(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
        tfDireccion.clear();
        tfDPI.clear();
    }

    public void agregarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDpi(tfDPI.getText());
        empladoService.guardarEmpleados(empleado);
        cargarDatos();
    }

    public void editarEmpleado(){
        Empleado empleado = empladoService.buscarEmpleados(Long.parseLong(tfId.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDpi(tfDPI.getText());
        empladoService.guardarEmpleados(empleado);
        cargarDatos();
    }

    public void eliminarEmpleado(){
        Empleado empleado = empladoService.buscarEmpleados(Long.parseLong(tfId.getText()));
        empladoService.eliminarEmpleados(empleado);
        cargarDatos();
    }    

    


}
