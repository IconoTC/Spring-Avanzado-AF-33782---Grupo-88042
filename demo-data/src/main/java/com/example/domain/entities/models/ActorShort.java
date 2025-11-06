package com.example.domain.entities.models;

import org.springframework.beans.factory.annotation.Value;

public interface ActorShort {
	int getId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
}
