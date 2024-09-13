package com.kevinreyes.webapp.blibioteca.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.kevinreyes.webapp.blibioteca.BlibiotecaApplication;
import com.kevinreyes.webapp.blibioteca.controller.FXController.CategoriaControllerFX;
import com.kevinreyes.webapp.blibioteca.controller.FXController.ClienteControllerFX;
import com.kevinreyes.webapp.blibioteca.controller.FXController.EmpleadoControllerFX;
import com.kevinreyes.webapp.blibioteca.controller.FXController.IndexController;
import com.kevinreyes.webapp.blibioteca.controller.FXController.LibroControllerFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;


    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(BlibiotecaApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Biblioteca Spring");
        indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int whidt, int height) throws IOException {
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName ));

        scene = new Scene((AnchorPane) loader.load(archivo), whidt, height);
        stage.setScene(scene);
        stage.sizeToScene();

        resultado = (Initializable)loader.getController();

        return resultado;
    }


    public void indexView(){
        try {
            IndexController indexView = (IndexController)switchScene("index.fxml", 1000, 600);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void categoriaView(){
        try {
            CategoriaControllerFX categoriaView = (CategoriaControllerFX)switchScene("categoriaView.fxml", 1000, 600);
            categoriaView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void empleadoView(){
        try {
            EmpleadoControllerFX empleadoView = (EmpleadoControllerFX)switchScene("empleadosView.fxml", 1000, 600);
            empleadoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clienteView(){
        try {
            ClienteControllerFX clienteView = (ClienteControllerFX)switchScene("clienteView.fxml", 1000, 600);
            clienteView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void librosView(){
        try {
            LibroControllerFX libroView = (LibroControllerFX)switchScene("libroView.fxml", 1000, 600);
            libroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
