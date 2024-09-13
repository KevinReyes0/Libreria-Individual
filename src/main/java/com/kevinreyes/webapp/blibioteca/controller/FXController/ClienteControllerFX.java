package com.kevinreyes.webapp.blibioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevinreyes.webapp.blibioteca.model.Cliente;
import com.kevinreyes.webapp.blibioteca.service.ClienteService;
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
public class ClienteControllerFX implements Initializable{

    @FXML
    TextField tfId, tfNombre, tfApellido, tfTelefono, tfBuscar;

    @FXML
    Button btnGuardar, btnVaciar, btnRegresar, btnEliminar, btnBuscar;

    @FXML
    TableView tblClientes;

    @FXML
    TableColumn colId, colApellido, colTelefono, colNombre;

    @Setter
    private Main stage;

    private Boolean editar = false;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (!editar) {
                agregarCliente();
            } else {
                editarCliente();
            }
        } else if (event.getSource() == btnVaciar) {
            vaciarForm();
        } else if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnEliminar) {
            eliminarCliente();
        } else if (event.getSource() == btnBuscar) {
            tblClientes.getItems().clear();
            if (tfBuscar.getText().isBlank()) {
                cargarDatos();
            } else {
                tblClientes.getItems().add(buscarCliente());
                colId.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombreCliente"));
                colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoCliente"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefonoCliente"));
            }

        }
    }

    public void cargarDatos() {
        tblClientes.setItems(listarClientes());
        colId.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombreCliente"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoCliente"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefonoCliente"));
    }

    public ObservableList<Cliente> listarClientes() {
        return FXCollections.observableList(clienteService.listarClientes());
    }

    public void cargarFormEditar() {
        Cliente cliente = (Cliente) tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            tfId.setText(cliente.getDpi().toString());
            tfNombre.setText(cliente.getNombreCliente());
            tfApellido.setText(cliente.getApellidoCliente());
            tfTelefono.setText(cliente.getTelefonoCliente());
            editar = true;
        }
    }

    public void vaciarForm() {
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
    }

    public void agregarCliente() {
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfId.getText()));
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarClientes(cliente);
        cargarDatos();
    }

    public void editarCliente() {
        Cliente cliente = clienteService.buscarClientes(Long.parseLong(tfId.getText()));
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarClientes(cliente);
        cargarDatos();
    }

    public void eliminarCliente() {
        Cliente cliente = clienteService.buscarClientes(Long.parseLong(tfId.getText()));
        clienteService.eliminarClientes(cliente);
        cargarDatos();
    }

    public Cliente buscarCliente() {
        return clienteService.buscarClientes(Long.parseLong(tfBuscar.getText()));
    }


}
