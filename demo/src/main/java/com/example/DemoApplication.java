package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ioc.ClaseNoComponente;
import com.example.ioc.NotificationService;
import com.example.ioc.anotaciones.Remoto;
import com.example.ioc.contratos.ServicioCadenas;
import com.example.ioc.notificaciones.Sender;

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

	@Autowired(required = false)
	NotificationService notify;
	
//	@Bean
	CommandLineRunner ejemplosIoC() {
		return arg -> {
			if(notify == null) {
				System.err.println("Falta la implementación de NotificationService");
				return;
			}
			System.err.println(notify.getClass().getCanonicalName());
			notify.clear();
			notify.add("Hola mundo...");
			System.out.println("ejemplosIoC -------------------------------->");
			notify.getListado().forEach(System.out::println);
			notify.clear();
			System.out.println("<--------------------------------");
		};
	}
	
//	@Bean
	CommandLineRunner cadenaDeDependencias(ServicioCadenas srv ) {
		return arg -> {
//			ServicioCadenas srv = new ServicioCadenasImpl(new RepositorioCadenasImpl(new ConfiguracionImpl(notify), notify), notify);
			srv.get().forEach(notify::add);
			srv.modify("modificado");
			System.out.println("cadenaDeDependencias -------------------------------->");
			notify.getListado().forEach(System.out::println);
			notify.clear();
			System.out.println("<--------------------------------");
		};
	}

	@Autowired(required = false)
	ClaseNoComponente dummy;

//	@Bean
	CommandLineRunner cardinalidad(ServicioCadenas srv ) {
		return arg -> {
			if(dummy == null) {
				System.err.println("Falta la implementación de NotificationService");
				return;
			}
			dummy.saluda();
			System.out.println("cardinalidad -------------------------------->");
			notify.getListado().forEach(System.out::println);
			notify.clear();
			System.out.println("<--------------------------------");
		};
	}
	
//	@Bean
	CommandLineRunner beansPorNombre(Sender correo, Sender fichero, Sender twittea) {
		return arg -> {
			correo.send("Hola mundo");
			fichero.send("Hola mundo");
			twittea.send("Hola mundo");
		};
	}

	@Bean
	CommandLineRunner beansCualificados(@Qualifier("local") Sender local, @Remoto Sender remoto) {
		return arg -> {
			local.send("Hola local");
			remoto.send("Hola remoto");
		};
	}

}
