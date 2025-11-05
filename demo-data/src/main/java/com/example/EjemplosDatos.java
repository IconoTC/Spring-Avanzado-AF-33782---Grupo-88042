package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.contracts.domain.repositories.ActorsRepository;
import com.example.contracts.domain.repositories.CategoriesRepository;
import com.example.domain.entities.Actor;
import com.example.domain.entities.Category;

@Component
public class EjemplosDatos {
	public void run() {
		System.out.println("Hola mundo");
		relaciones();
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
	
	public void consultas() {
		daoActors.findTop5ByFirstNameStartingWithIgnoreCaseOrderByLastNameDesc("p").forEach(System.out::println);
		daoActors.findTop5ByFirstNameStartingWithIgnoreCase("p", Sort.by("firstName").descending()).forEach(System.out::println);
//		daoActors.findByIdGreaterThanEqual(195).forEach(System.out::println);
//		daoActors.findNovedadesJPQL(195).forEach(System.out::println);
		daoActors.findNovedadesSQL(195).forEach(System.out::println);
		daoActors.findAll((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("id"), 195)).forEach(System.out::println);
		daoActors.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("id"), 5)).forEach(System.out::println);
	}
	
	@Transactional
	public void relaciones() {
		daoActors.findNovedadesJPQL(198).forEach(item -> {
			System.out.println(item);
			item.getFilmActors().forEach(p -> System.out.println("\t%d - %s".formatted(p.getFilm().getId(), p.getFilm().getTitle())));
		});
	}
	@Autowired
	CategoriesRepository daoCategories;
	
	
	@Transactional
	public void transaccion() {
		daoActors.save(new Actor("Pepito", "Grillo"));
		daoActors.save(new Actor("Carmelo", "Coton"));
		daoCategories.save(new Category(0, "Serie B"));
		daoActors.deleteById(1);;
	}
}
