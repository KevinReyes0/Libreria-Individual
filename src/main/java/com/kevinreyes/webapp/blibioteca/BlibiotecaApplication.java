package com.kevinreyes.webapp.blibioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kevinreyes.webapp.blibioteca.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class BlibiotecaApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
		SpringApplication.run(BlibiotecaApplication.class, args);
	}

}
