package com.example.ioc.notificaciones;

import java.beans.ConstructorProperties;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.ioc.NotificationService;

@Component
public class ConstructorConValores {
	private final NotificationService notify;
	
	@ConstructorProperties({"version", "otroAutor"})
	public ConstructorConValores(int version, String autor, NotificationService notify) {
		this.notify = notify;
		notify.add(getClass().getSimpleName() + " - Version: " + version + " Autor: " + autor);
	}
	
	public void titulo(String tratamiento, String autor) {
		System.err.println(tratamiento.toUpperCase() + " " + autor.toUpperCase());
	}
	public void titulo(String autor) {
		System.err.println(autor.toUpperCase());
	}

//	public String dameValor(String autor) {
//		return Optional.empty();		
//	}

	public Optional<String> dameValor(String autor) {
		return Optional.empty();		
	}
}
