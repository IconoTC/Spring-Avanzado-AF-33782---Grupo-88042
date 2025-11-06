package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada ...");
	}
	
	@Bean
	CommandLineRunner ejemplos(EjemplosDatos demos) {
		return _ -> {
//			demos.run();
//			demos.actores();
//			demos.consultas();
//			demos.relaciones();
//			try {
//				demos.transaccion();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			demos.validaciones();
//			demos.proyecciones();
			demos.serializacion();
		};
	}

}
