package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.contracts.domain.repositories.ActorsRepository;
import com.example.domain.entities.Actor;

@Component
public class EjemplosDatos {
	public void run() {
		System.out.println("Hola mundo");
	}
	
	@Autowired
	ActorsRepository daoActors;
	
	public void actores() {
		System.out.println(">>> Create");
		var id = daoActors.save(new Actor("Pepito", "Grillo")).getId();
		daoActors.findAll().forEach(System.out::println);
		var item = daoActors.findById(id);
		if(item.isEmpty()) {
			System.err.println("No encontrado");
		} else {
			var actor = item.get();
			System.out.println("Leido: " + actor);
			actor.setFirstName(actor.getFirstName().toUpperCase());
			daoActors.save(actor);
		}
		System.out.println(">>> Update");
		daoActors.findAll().forEach(System.out::println);
		daoActors.deleteById(id);
		System.out.println(">>> Delete");
		daoActors.findAll().forEach(System.out::println);
		
	}
}
