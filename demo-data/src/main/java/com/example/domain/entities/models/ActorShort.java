package com.example.domain.entities.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.domain.entities.Actor;

@Projection(name = "nombres", types = { Actor.class })
public interface ActorShort {
	int getId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
}
