package com.example.contracts.domain.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.core.contracts.domain.repositories.ProjectionsAndSpecificationJpaRepository;
import com.example.domain.entities.Film;

@RepositoryRestResource(path="peliculas", itemResourceRel="pelicula", collectionResourceRel="peliculas")
public interface PeliculasRepository extends ProjectionsAndSpecificationJpaRepository<Film, Integer> {

}
