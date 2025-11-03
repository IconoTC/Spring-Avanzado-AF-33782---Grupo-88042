package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// ...
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada ...");
	}

	@Bean
	CommandLineRunner ejemplos() {
		return arg -> {
			System.out.println("Hola mundo...");
		};
	}
}
