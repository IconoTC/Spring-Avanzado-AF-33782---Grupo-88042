package com.example.contracts.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.domain.entities.Actor;
import com.example.domain.entities.models.ActorDTO;
import com.example.domain.entities.models.ActorShort;

@RepositoryRestResource(path="actores", itemResourceRel="actor", collectionResourceRel="actores")
public interface ActorsRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor>/*, RepositoryWithProjections*/ {
	List<Actor> findTop5ByFirstNameStartingWithIgnoreCaseOrderByLastNameDesc(String prefijo);
	List<Actor> findTop5ByFirstNameStartingWithIgnoreCase(String prefijo, Sort orderBy);
	
	@Meta(comment = "DSL")
	@EntityGraph(attributePaths = {"filmActors.film"})
	@RestResource(path = "novedades")
	List<Actor> findByIdGreaterThanEqual(int primero);
	@Meta(comment = "JPQL")
	@Query("from Actor a where a.id >= ?1")
	List<Actor> findNovedadesJPQL(int primero);
	@Meta(comment = "SQL")
	@NativeQuery("select * from actor a where a.actor_id >= :primero")
	List<Actor> findNovedadesSQL(int primero);
	
	List<ActorDTO> readByIdGreaterThanEqual(int primero);
	List<ActorShort> queryByIdGreaterThanEqual(int primero);
	
	@RestResource(exported = false)
	<T> List<T> findByIdGreaterThanEqual(int primero, Class<T> type);
}
