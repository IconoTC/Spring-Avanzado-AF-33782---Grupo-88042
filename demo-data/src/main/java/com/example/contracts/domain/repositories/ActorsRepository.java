package com.example.contracts.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.entities.Actor;

public interface ActorsRepository extends JpaRepository<Actor, Integer> {

}
