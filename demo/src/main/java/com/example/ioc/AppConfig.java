package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.ioc.contratos.Configuracion;
import com.example.ioc.notificaciones.EMailSender;
import com.example.ioc.notificaciones.FileSender;
import com.example.ioc.notificaciones.Sender;
import com.example.ioc.notificaciones.TwitterSender;

@Configuration
@ComponentScan("com.example.ioc")
public class AppConfig {
//	@Bean
//	ClaseNoComponente claseNoComponente() {
//		return new ClaseNoComponente(new ConfiguracionImpl());
//	}
	@Bean
	ClaseNoComponente claseNoComponente(Configuracion config) {
		return new ClaseNoComponente(config);
	}
	
	@Bean
	Sender correo(EMailSender item) {
		return item;
	}
	
	@Bean
	@Qualifier("local")
	Sender fichero(FileSender item) {
		return item;
	}
	
	@Bean
	@Qualifier("remoto")
	Sender twittea(TwitterSender item) {
		return item;
	}
	
	@Bean
	@Primary
	Sender sender(EMailSender correo) {
		return correo;
	}
	
	@Bean int version() { 
		return 22; 
	}
	@Bean() String autor() { 
		return "Yo mismo"; 
	}
	
	@Bean() String otroAutor(@Value("${info.app.author:(Anonimo)}") String nombre) { 
		return nombre; 
	}

}
