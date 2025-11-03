package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ioc.contratos.Configuracion;

import jakarta.annotation.PostConstruct;

public class ClaseNoComponente {
	@Autowired
	private NotificationService notify;

	private final Configuracion configuracion;
	
	public ClaseNoComponente(Configuracion configuracion) {
		this.configuracion = configuracion;
	}
	@PostConstruct
	private void despuesDelConstructor() {
		notify.add(getClass().getSimpleName() + " Constructor");
	}
	
	public void saluda() {
		notify.add("Hola mundo");
	}
}
